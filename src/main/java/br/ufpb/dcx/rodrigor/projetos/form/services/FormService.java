package br.ufpb.dcx.rodrigor.projetos.form.services;

import br.ufpb.dcx.rodrigor.projetos.AbstractService;
import br.ufpb.dcx.rodrigor.projetos.db.MongoDBConnector;
import br.ufpb.dcx.rodrigor.projetos.form.model.Campo;
import br.ufpb.dcx.rodrigor.projetos.form.model.Formulario;
import br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS.*;
import br.ufpb.dcx.rodrigor.projetos.participante.model.Participante;
import br.ufpb.dcx.rodrigor.projetos.participante.services.ParticipanteService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class FormService extends AbstractService  {

    private final Map<String, Formulario> formularios = new LinkedHashMap<>();
    private final MongoCollection<Document> collection;
    ParticipanteService participanteService;


    public FormService(MongoDBConnector mongoDBConnector){
        super(mongoDBConnector);
        MongoDatabase database = mongoDBConnector.getDatabase("projetos");
        this.collection = database.getCollection("participantes");
        participanteService = new ParticipanteService(mongoDBConnector);
        iniciarFormularios();
    }

    public Formulario getFormulario(String id){
        return formularios.get(id);
    }

    public void adicionarParticipanteAoFormulario(Participante participante) {
        // Lógica para adicionar participante ao formulário usando participanteService
        participanteService.adicionarParticipante(participante);
    }

    public void iniciarFormularios() {
        Formulario form = new Formulario("usuario", "Cadastro de Usuário");
        form.addCampo(new Campo("nome", "Nome", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        form.addCampo(new Campo("email", "Email", new ValidadorEmail(), true));
        formularios.put(form.getId(), form);

        Formulario participante = new Formulario("participante", "Cadastro de Participantes");
        participante.addCampo(new Campo("nome", "Nome", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("sobrenome", "Sobrenome", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("email", "Email", new ValidadorEmail(), true));
        participante.addCampo(new Campo("telefone", "Telefone", new ValidadorNUMERO(), true)); // Ajustado para usar ValidadorNUMERO
        participante.addCampo(new Campo("categoria", "Categoria", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("matricula", "Matrícula", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("curso", "Curso", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("periodoEntrada", "Período de Entrada", new ValidadorPeriodoEntrada(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("cpf", "CPF", new ValidadorCPF(), true));
        participante.addCampo(new Campo("rg", "RG", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("orgaoExpedidor", "Órgão Expedidor", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("sexo", "Sexo", new ValidadorTexto(), true, "")); // ValidadorTexto sem limites
        participante.addCampo(new Campo("linkedin", "LinkedIn", new ValidadorLinkedin(), false));
        participante.addCampo(new Campo("github", "GitHub", new ValidadorGithub(), false));
        participante.addCampo(new Campo("instagram", "Instagram", new ValidadorInstagram(), false));
        participante.addCampo(new Campo("rua", "Rua", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("pais", "País", new ValidadorTexto(), true));
        participante.addCampo(new Campo("estado", "Estado", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("cidade", "Cidade", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("cep", "CEP", new ValidadorNUMERO(), true)); // Ajustado para usar ValidadorNUMERO
        participante.addCampo(new Campo("bairro", "Bairro", new ValidadorTexto(), true)); // ValidadorTexto sem limites
        participante.addCampo(new Campo("numero", "Número", new ValidadorNUMERO(), true)); // Ajustado para usar ValidadorNUMERO

        formularios.put(participante.getId(), participante);
    }
}
