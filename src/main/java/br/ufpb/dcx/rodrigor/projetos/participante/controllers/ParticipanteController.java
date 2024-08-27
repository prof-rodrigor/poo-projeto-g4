package br.ufpb.dcx.rodrigor.projetos.participante.controllers;

import br.ufpb.dcx.rodrigor.projetos.Keys;
import br.ufpb.dcx.rodrigor.projetos.participante.model.CategoriaParticipante;
import br.ufpb.dcx.rodrigor.projetos.participante.model.Participante;
import br.ufpb.dcx.rodrigor.projetos.participante.services.ParticipanteService;
import io.javalin.http.Context;
import org.bson.types.ObjectId;

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

        // Setando os atributos com os valores do formulário
        participante.setId(new ObjectId()); // Gera um novo ObjectId para o participante
        participante.setNome(ctx.formParam("nome"));
        participante.setSobrenome(ctx.formParam("sobrenome"));
        participante.setEmail(ctx.formParam("email"));
        participante.setTelefone(ctx.formParam("telefone"));
        participante.setMatricula(ctx.formParam("matricula"));
        participante.setCurso(ctx.formParam("curso"));
        participante.setPeriodoDeEntrada(ctx.formParam("periodoDeEntrada"));
        participante.setCpf(ctx.formParam("cpf"));
        participante.setRg(ctx.formParam("rg"));
        participante.setOrgaoExpedidor(ctx.formParam("orgaoExpedidor"));
        participante.setSexo(ctx.formParam("sexo"));
        participante.setInstagram(ctx.formParam("instagram"));
        participante.setLinkedIn(ctx.formParam("linkedIn"));
        participante.setGithub(ctx.formParam("github"));
        participante.setEndereco(ctx.formParam("endereco"));

        // Definindo a categoria do participante com base no valor do formulário
        participante.setCategoria(CategoriaParticipante.valueOf(ctx.formParam("categoria")));

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
