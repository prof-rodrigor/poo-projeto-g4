package br.ufpb.dcx.rodrigor.projetos.participante.controllers;

import br.ufpb.dcx.rodrigor.projetos.Keys;
import br.ufpb.dcx.rodrigor.projetos.participante.model.CategoriaParticipante;
import br.ufpb.dcx.rodrigor.projetos.participante.model.Participante;
import br.ufpb.dcx.rodrigor.projetos.participante.services.ParticipanteService;
import io.javalin.http.Context;
import org.bson.types.ObjectId;
import io.javalin.http.BadRequestResponse;

public class ParticipanteController {

    public ParticipanteController() {
    }

    public void listarParticipantes(Context ctx) {
        ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
        ctx.attribute("participantes", participanteService.listarParticipantes());
        ctx.render("/participantes/lista_participantes.html");
    }

    public void mostrarFormularioCadastro(Context ctx) {
        ctx.attribute("categorias", CategoriaParticipante.values());
        ctx.render("/participantes/formulario_participante.html");
    }

    public class ParticipanteController {

        public ParticipanteController() {
        }

        public void listarParticipantes(Context ctx) {
            ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
            ctx.attribute("participantes", participanteService.listarParticipantes());
            ctx.render("/participantes/lista_participantes.html");
        }

        public void mostrarFormularioCadastro(Context ctx) {
            ctx.attribute("categorias", CategoriaParticipante.values());
            ctx.render("/participantes/formulario_participante.html");
        }

        public void adicionarParticipante(Context ctx) {
            ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
            Participante participante = new Participante();

            // string pra validar o nao uso dos caracteres espaciais
            String caracteresindesejados = "^[a-zA-Z0-9 ]+$";

            // Validação de Nome
            String nome = ctx.formParam("nome");
            if (nome == null || nome.trim().isEmpty() || !nome.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O nome não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Sobrenome
            String sobrenome = ctx.formParam("sobrenome");
            if (sobrenome == null || sobrenome.trim().isEmpty() || !sobrenome.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O sobrenome não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Email
            String email = ctx.formParam("email");
            if (email == null || email.trim().isEmpty() || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                throw new BadRequestResponse("O e-mail fornecido não é válido.");
            }

            // Validação de Telefone
            String telefone = ctx.formParam("telefone");
            if (telefone == null || telefone.trim().isEmpty() || !telefone.matches("^\\+?\\d{9,15}$")) {
                throw new BadRequestResponse("O telefone fornecido não é válido. Use um número com entre 9 e 15 dígitos.");
            }

            // Validação de Matrícula
            String matricula = ctx.formParam("matricula");
            if (matricula == null || matricula.trim().isEmpty() || !matricula.matches("\\d{11}")) {
                throw new BadRequestResponse("A matrícula deve conter 11 dígitos e não pode estar vazia.");
            }

            // Validação de CPF
            String cpf = ctx.formParam("cpf");
            if (cpf == null || cpf.trim().isEmpty() || !cpf.matches("\\d{11}")) {
                throw new BadRequestResponse("O CPF deve conter 11 dígitos e não pode estar vazio.");
            }

            // Validação de RG
            String rg = ctx.formParam("rg");
            if (rg == null || rg.trim().isEmpty() || !rg.matches("\\d{5,12}")) {
                throw new BadRequestResponse("O RG deve conter entre 5 e 12 dígitos e não pode estar vazio.");
            }

            // Validação de Sexo
            String sexo = ctx.formParam("sexo");
            if (sexo == null || sexo.trim().isEmpty() || !(sexo.equals("Masculino") || sexo.equals("Feminino") || sexo.equals("Outro"))) {
                throw new BadRequestResponse("O sexo deve ser M, F ou Outro e não pode estar vazio.");
            }

            // Validação de Curso
            String curso = ctx.formParam("curso");
            if (curso == null || curso.trim().isEmpty() || !curso.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O curso não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Período de Entrada
            String periodoDeEntrada = ctx.formParam("periodoDeEntrada");
            if (periodoDeEntrada == null || periodoDeEntrada.trim().isEmpty()) {
                throw new BadRequestResponse("O período de entrada não pode estar vazio.");
            }

            // Validação de Orgao Expedidor
            String orgaoExpedidor = ctx.formParam("orgaoExpedidor");
            if (orgaoExpedidor == null || orgaoExpedidor.trim().isEmpty() || !orgaoExpedidor.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O órgão expedidor não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Instagram
            String instagram = ctx.formParam("instagram");
            if (instagram == null || instagram.trim().isEmpty()) {
                throw new BadRequestResponse("O Instagram não pode estar vazio.");
            }

            // Validação de LinkedIn
            String linkedIn = ctx.formParam("linkedIn");
            if (linkedIn == null || linkedIn.trim().isEmpty()) {
                throw new BadRequestResponse("O LinkedIn não pode estar vazio.");
            }

            // Validação de GitHub
            String github = ctx.formParam("github");
            if (github == null || github.trim().isEmpty()) {
                throw new BadRequestResponse("O GitHub não pode estar vazio.");
            }

            // Validação de Pais
            String pais = ctx.formParam("pais");
            if (pais == null || pais.trim().isEmpty() || !pais.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O país não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Estado
            String estado = ctx.formParam("estado");
            if (estado == null || estado.trim().isEmpty() || !estado.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O estado não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Cidade
            String cidade = ctx.formParam("cidade");
            if (cidade == null || cidade.trim().isEmpty() || !cidade.matches(caracteresindesejados)) {
                throw new BadRequestResponse("A cidade não pode ser vazia e não deve conter caracteres especiais.");
            }

            // Validação de CEP
            String cep = ctx.formParam("cep");
            if (cep == null || cep.trim().isEmpty() || !cep.matches("\\d{5}-\\d{3}")) {
                throw new BadRequestResponse("O CEP deve seguir o formato XXXXX-XXX e não pode estar vazio.");
            }

            // Validação de Bairro
            String bairro = ctx.formParam("bairro");
            if (bairro == null || bairro.trim().isEmpty() || !bairro.matches(caracteresindesejados)) {
                throw new BadRequestResponse("O bairro não pode ser vazio e não deve conter caracteres especiais.");
            }

            // Validação de Rua
            String rua = ctx.formParam("rua");
            if (rua == null || rua.trim().isEmpty() || !rua.matches(caracteresindesejados)) {
                throw new BadRequestResponse("A rua não pode ser vazia e não deve conter caracteres especiais.");
            }

            // Validação de Número
            String numero = ctx.formParam("numero");
            if (numero == null || numero.trim().isEmpty() || !numero.matches("\\d+")) {
                throw new BadRequestResponse("O número deve ser numérico e não pode estar vazio.");
            }

            // Setando os atributos validados
            participante.setId(new ObjectId()); // Gera um novo ObjectId para o participante
            participante.setNome(nome);
            participante.setSobrenome(sobrenome);
            participante.setEmail(email);
            participante.setTelefone(telefone);
            participante.setMatricula(matricula);
            participante.setCurso(curso);
            participante.setPeriodoDeEntrada(periodoDeEntrada);
            participante.setCpf(cpf);
            participante.setRg(rg);
            participante.setOrgaoExpedidor(orgaoExpedidor);
            participante.setSexo(sexo);
            participante.setInstagram(instagram);
            participante.setLinkedIn(linkedIn);
            participante.setGithub(github);
            participante.setPais(pais);
            participante.setEstado(estado);
            participante.setCidade(cidade);
            participante.setCEP(cep);
            participante.setBairro(bairro);
            participante.setRua(rua);
            participante.setNumero(numero);

            // Validação da categoria
            String categoriaStr = ctx.formParam("categoria");
            try {
                participante.setCategoria(CategoriaParticipante.valueOf(categoriaStr));
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new BadRequestResponse("Categoria inválida e não pode ser vazia.");
            }

            participanteService.adicionarParticipante(participante);
            ctx.redirect("/participantes");
        }

    public void removerParticipante(Context ctx) {
        ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
        String id = ctx.pathParam("id");
        participanteService.removerParticipante(id);
        ctx.redirect("/participantes");
    }
}
