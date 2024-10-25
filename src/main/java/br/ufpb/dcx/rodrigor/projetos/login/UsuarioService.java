package br.ufpb.dcx.rodrigor.projetos.login;


import br.ufpb.dcx.rodrigor.projetos.db.MongoDBConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.mindrot.jbcrypt.BCrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static java.net.HttpURLConnection.HTTP_OK;

public class UsuarioService {
    private static final Logger logger = LogManager.getLogger();

    private final MongoCollection<Document> repository;

    public UsuarioService(MongoDBConnector mongoDBConnector) {
        // Conectando ao banco de dados MongoDB e selecionando a coleção "usuarios"
        MongoDatabase database = mongoDBConnector.getDatabase("usuarios");
        this.repository = database.getCollection("usuarios");
    }

    // Metodo para cadastrar um novo usuário
    public void cadastrarNovoUsuario(Usuario usuario) {
        // Verificando se o login ou email já existem
        if (getUsuarioByLogin(usuario.getLogin()) != null) {
            throw new IllegalArgumentException("Esse login já existe");
        }

        // Criptografando a senha antes de armazená-la no banco de dados
        String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(senhaHash);

        // Convertendo o objeto Usuario para um documento MongoDB e inserindo no banco
        Document doc = usuarioToDocument(usuario);
        repository.insertOne(doc);
    }

    // Metodo para atualizar um usuário existente
    public void atualizarUsuario(Usuario usuario) {
        // Buscando o usuário no banco de dados
        Usuario usuarioExistente = getUsuarioByLogin(usuario.getLogin());

        if (usuarioExistente == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Atualizando a senha se necessário
        if (!usuarioExistente.getSenha().equals(usuario.getSenha())) {
            String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            usuario.setSenha(senhaHash);
        }

        // Convertendo o objeto atualizado para documento MongoDB e substituindo o existente
        Document userDoc = usuarioToDocument(usuario);
        repository.replaceOne(eq("login", usuario.getLogin()), userDoc);
    }

    // Metodo para remover um usuário pelo login
    public void removerUsuario(String login) {
        repository.deleteOne(eq("login", login));
    }

    // Metodo para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new LinkedList<>();
        for (Document doc : repository.find()) {
            usuarios.add(documentToUsuario(doc));
        }
        return usuarios;
    }

    // Metodo para buscar um usuário pelo login
    public Usuario getUsuarioByLogin(String login) {
        Bson filter = eq("login", login);
        Document doc = repository.find(filter).first();
        if (doc == null) {
            return null;
        }
        return documentToUsuario(doc);
    }

    // Metodo para verificar a senha do usuário
    public boolean verificarSenha(String login, String senha) {
        Usuario usuario = getUsuarioByLogin(login);
        if (usuario == null) {
            return false;
        }
        // Verificando se a senha fornecida corresponde à senha criptografada no banco
        return BCrypt.checkpw(senha, usuario.getSenha());
    }

    // Converte um objeto Usuario para um documento MongoDB
    private Document usuarioToDocument(Usuario usuario) {
        Document doc = new Document();
        doc.put("login", usuario.getLogin());
        doc.put("nome", usuario.getNome());
        doc.put("senha", usuario.getSenha());  // Armazena a senha já criptografada
        return doc;
    }

    // Converte um documento MongoDB para um objeto Usuario
    private Usuario documentToUsuario(Document doc) {
        Usuario usuario = new Usuario();
        usuario.setLogin(doc.getString("login"));
        usuario.setNome(doc.getString("nome"));
        usuario.setSenha(doc.getString("senha"));
        return usuario;
    }
    public Usuario buscarUsuario(String login, String senha){
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "http://localhost:8080/v1/autenticar";
        Map<String, String> dados = new HashMap<>();
        dados.put("login", login);
        dados.put("senha", senha);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String loginJson = mapper.writeValueAsString(dados);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(loginJson, StandardCharsets.UTF_8))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HTTP_OK) {
                Usuario usuario = mapper.readValue(response.body(), Usuario.class);
                return usuario;
            }else{
                logger.error(response.body());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error(exception);
        }
        return null;
    }

}
