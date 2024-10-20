package br.ufpb.dcx.rodrigor.projetos.participante.controllers;

import br.ufpb.dcx.rodrigor.projetos.Keys;
import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS.*;
import br.ufpb.dcx.rodrigor.projetos.participante.model.CategoriaParticipante;
import br.ufpb.dcx.rodrigor.projetos.participante.model.Participante;
import br.ufpb.dcx.rodrigor.projetos.participante.services.ParticipanteService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.bson.types.ObjectId;
import io.javalin.http.BadRequestResponse;

public class ParticipanteController {

    public ParticipanteController() {
    }

    public void listarParticipantes(Context ctx) {
        // não permite que o usuario possa acessar os participantes antes de fazer o login
        if (ctx.sessionAttribute("usuario") == null) {
            ctx.redirect("/login");
        } else {
            ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
            ctx.attribute("participantes", participanteService.listarParticipantes());
            ctx.render("/participantes/lista_participantes.html");
        }
    }

    public void mostrarFormularioCadastro(Context ctx) {
        ctx.attribute("categorias", CategoriaParticipante.values());
        ctx.render("/participantes/formulario_participante.html");
    }


    public void adicionarParticipante(Context ctx) {
        ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
        Participante participante = new Participante();

        // Setando os atributos validados
        String nome = ctx.formParam("nome");
        ResultadoValidacao resultadoNome = new ValidadorTexto().validar(nome);
        if (!resultadoNome.isOk()) {
            throw new BadRequestResponse(resultadoNome.getMensagem());
        }
        participante.setNome(nome);

        // Validação de Sobrenome
        String sobrenome = ctx.formParam("sobrenome");
        ResultadoValidacao resultadoSobrenome = new ValidadorTexto().validar(sobrenome);
        if (!resultadoSobrenome.isOk()) {
            throw new BadRequestResponse(resultadoSobrenome.getMensagem());
        }
        participante.setSobrenome(sobrenome);

        // Validação de Email
        String email = ctx.formParam("email");
        ResultadoValidacao resultadoEmail = new ValidadorEmail().validar(email);
        if (!resultadoEmail.isOk()) {
            throw new BadRequestResponse(resultadoEmail.getMensagem());
        }
        participante.setEmail(email);

        // Validação de Telefone
        String telefone = ctx.formParam("telefone");
        ResultadoValidacao resultadoTelefone = new ValidadorNUMERO().validar(telefone);
        if (!resultadoTelefone.isOk()) {
            throw new BadRequestResponse(resultadoTelefone.getMensagem());
        }
        participante.setTelefone(telefone);

        // Validação de Matrícula
        String matricula = ctx.formParam("matricula");
        if (matricula == null || matricula.trim().isEmpty() || !matricula.matches("\\d{11}")) {
            throw new BadRequestResponse("A matrícula deve conter 11 dígitos e não pode estar vazia.");
        }
        participante.setMatricula(matricula);

        // Validação de CPF
        String cpf = ctx.formParam("cpf");
        ResultadoValidacao resultadoCpf = new ValidadorCPF().validar(cpf);
        if (!resultadoCpf.isOk()) {
            throw new BadRequestResponse(resultadoCpf.getMensagem());
        }
        participante.setCpf(cpf);

        // Validação de RG
        String rg = ctx.formParam("rg");
        ResultadoValidacao resultadoRg = new ValidadorTexto().validar(rg);
        if (!resultadoRg.isOk()) {
            throw new BadRequestResponse(resultadoRg.getMensagem());
        }
        participante.setRg(rg);

        // Validação de Sexo
        String sexo = ctx.formParam("sexo");
        if (sexo == null || sexo.trim().isEmpty()) {
            throw new BadRequestResponse("O sexo deve ser M, F ou Outro e não pode estar vazio.");
        }
        participante.setSexo(sexo);

        // Validação de Curso
        String curso = ctx.formParam("curso");
        if (curso == null || curso.trim().isEmpty()) {
            throw new BadRequestResponse("O curso não pode ser vazio e não deve conter caracteres especiais.");
        }
        participante.setCurso(curso);

        // Validação de Período de Entrada
        String periodoDeEntrada = ctx.formParam("periodoDeEntrada");
        if (periodoDeEntrada == null || periodoDeEntrada.trim().isEmpty()) {
            throw new BadRequestResponse("O período de entrada não pode estar vazio.");
        }
        participante.setPeriodoDeEntrada(periodoDeEntrada);

        // Validação de Órgão Expedidor
        String orgaoExpedidor = ctx.formParam("orgaoExpedidor");
        ResultadoValidacao resultadoOrgaoExpedidor = new ValidadorTexto().validar(orgaoExpedidor);
        if (!resultadoOrgaoExpedidor.isOk()) {
            throw new BadRequestResponse(resultadoOrgaoExpedidor.getMensagem());
        }
        participante.setOrgaoExpedidor(orgaoExpedidor);

        // Validação de Instagram
        String instagram = ctx.formParam("instagram");
        if (instagram != null && !instagram.trim().isEmpty()) {
            ResultadoValidacao resultadoInstagram = new ValidadorTexto().validar(instagram);
            if (!resultadoInstagram.isOk()) {
                throw new BadRequestResponse(resultadoInstagram.getMensagem());
            }
        }
        participante.setInstagram(instagram);

        // Validação de LinkedIn
        String linkedIn = ctx.formParam("linkedIn");
        if (linkedIn != null && !linkedIn.trim().isEmpty()) {
            ResultadoValidacao resultadoLinkedIn = new ValidadorTexto().validar(linkedIn);
            if (!resultadoLinkedIn.isOk()) {
                throw new BadRequestResponse(resultadoLinkedIn.getMensagem());
            }
        }
        participante.setLinkedIn(linkedIn);

        // Validação de GitHub
        String github = ctx.formParam("github");
        if (github != null && !github.trim().isEmpty()) {
            ResultadoValidacao resultadoGithub = new ValidadorGithub().validar(github);
            if (!resultadoGithub.isOk()) {
                throw new BadRequestResponse(resultadoGithub.getMensagem());
            }
        }
        participante.setGithub(github);

        // Validação de País
        String pais = ctx.formParam("pais");
        ResultadoValidacao resultadoPais = new ValidadorTexto().validar(pais);
        if (!resultadoPais.isOk()) {
            throw new BadRequestResponse(resultadoPais.getMensagem());
        }
        participante.setPais(pais);

        // Validação de Estado
        String estado = ctx.formParam("estado");
        ResultadoValidacao resultadoEstado = new ValidadorTexto().validar(estado);
        if (!resultadoEstado.isOk()) {
            throw new BadRequestResponse(resultadoEstado.getMensagem());
        }
        participante.setEstado(estado);

        // Validação de Cidade
        String cidade = ctx.formParam("cidade");
        ResultadoValidacao resultadoCidade = new ValidadorTexto().validar(cidade);
        if (!resultadoCidade.isOk()) {
            throw new BadRequestResponse(resultadoCidade.getMensagem());
        }
        participante.setCidade(cidade);

        // Validação de CEP
        String cep = ctx.formParam("cep");
        if (cep == null || cep.trim().isEmpty() || !cep.matches("\\d{5}-\\d{3}")) {
            throw new BadRequestResponse("O CEP deve seguir o formato XXXXX-XXX e não pode estar vazio.");
        }
        participante.setCEP(cep);

        // Validação de Bairro
        String bairro = ctx.formParam("bairro");
        ResultadoValidacao resultadoBairro = new ValidadorTexto().validar(bairro);
        if (!resultadoBairro.isOk()) {
            throw new BadRequestResponse(resultadoBairro.getMensagem());
        }
        participante.setBairro(bairro);

        // Validação de Rua
        String rua = ctx.formParam("rua");
        ResultadoValidacao resultadoRua = new ValidadorTexto().validar(rua);
        if (!resultadoRua.isOk()) {
            throw new BadRequestResponse(resultadoRua.getMensagem());
        }
        participante.setRua(rua);

        // Validação de Número
        String numero = ctx.formParam("numero");
        if (numero == null || numero.trim().isEmpty() || !numero.matches("\\d+")) {
            throw new BadRequestResponse("O número deve ser numérico e não pode estar vazio.");
        }
        participante.setNumero(numero);

        // Validação da categoria
        String categoriaStr = ctx.formParam("categoria");
        try {
            participante.setCategoria(CategoriaParticipante.valueOf(categoriaStr));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BadRequestResponse("Categoria inválida e não pode ser vazia.");
        }

        participante.setId(new ObjectId()); // Gera um novo ObjectId para o participante
        participanteService.adicionarParticipante(participante);
        ctx.redirect("/participantes");
    }


    public void removerParticipante(Context ctx) {
        ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
        String id = ctx.pathParam("id");
        participanteService.removerParticipante(id);
        ctx.redirect("/participantes");
    }

    // participantes em json
    public void participantesPorCategoria(Context ctx) {
        ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
        String categoriaParam = ctx.queryParam("categoria");

        if (categoriaParam != null) {
            try {
                CategoriaParticipante categoria = CategoriaParticipante.valueOf(categoriaParam.toUpperCase());
                var participantes = participanteService.listarParticipantesPorCategoria(categoria);
                if (participantes.isEmpty()) {
                    ctx.status(HttpStatus.NOT_FOUND).result("Nenhum participante encontrado para a categoria: " + categoriaParam);
                } else {
                    ctx.json(participantes);
                }
            } catch (IllegalArgumentException e) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Categoria inválida: " + categoriaParam);
            }
        } else {
            ctx.json(participanteService.listarParticipantes());
        }
    }

    // v1/participantes/<id>
    public void participantePorId(Context ctx) {
        ParticipanteService participanteService = ctx.appData(Keys.PARTICIPANTE_SERVICE.key());
        String id = ctx.pathParam("id");
        var participante = participanteService.buscarParticipantePorId(id);
        if (participante.isPresent()) {
            ctx.json(participante.get());
        } else {
            ctx.status(HttpStatus.NOT_FOUND)
                    .contentType("application/json")
                    .result("{\"message\": \"Participante não encontrado para o id: " + id + "\"}");
        }
    }

}