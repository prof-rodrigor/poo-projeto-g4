package br.ufpb.dcx.rodrigor.projetos.form.controller;

import br.ufpb.dcx.rodrigor.projetos.Keys;
import br.ufpb.dcx.rodrigor.projetos.form.model.Formulario;
import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.services.FormService;
import br.ufpb.dcx.rodrigor.projetos.participante.model.CategoriaParticipante;
import br.ufpb.dcx.rodrigor.projetos.participante.model.Participante;
import br.ufpb.dcx.rodrigor.projetos.participante.services.ParticipanteService;
import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class FormController {

    private static final Logger logger = LogManager.getLogger(FormController.class);

    public void abrirFormulario(Context ctx) {
        FormService formService = ctx.appData(Keys.FORM_SERVICE.key());
        Formulario form = formService.getFormulario("participante");
        ctx.attribute("form", form);
        ctx.attribute("campos", form.getCampos());
        ctx.render("/forms/formulario.html");
    }

    public void validarFormulario(@NotNull Context context) {
        FormService formService = context.appData(Keys.FORM_SERVICE.key());
        String formId = context.pathParam("formId"); // Certifique-se de que está recebendo o formId corretamente
        logger.debug("formId: {}", formId);
        Formulario form = formService.getFormulario(formId);
        logger.debug("form: {}", form);
        if (form == null) {
            context.status(404).result("Formulário não encontrado");
            return;
        }

        Map<String, ResultadoValidacao> erros = new LinkedHashMap<>();
        Participante participante = new Participante();

        form.getCampos().forEach(campo -> {
            String valor = context.formParam(campo.getId());
            campo.setValor(valor); // Definindo o valor antes de validar
            ResultadoValidacao resultado = campo.validar();
            if (!resultado.isOk()) {
                erros.put(campo.getId(), resultado);
            } else {

                switch (campo.getId()) {
                    case "nome":
                        participante.setNome(valor);
                        break;
                    case "sobrenome":
                        participante.setSobrenome(valor);
                        break;
                    case "email":
                        participante.setEmail(valor);
                        break;
                    case "telefone":
                        participante.setTelefone(valor);
                        break;
                    case "categoria":
                        participante.setCategoria(CategoriaParticipante.valueOf(valor));
                        break;
                    case "matricula":
                        participante.setMatricula(valor);
                        break;
                    case "curso":
                        participante.setCurso(valor);
                        break;
                    case "periodoEntrada":
                        participante.setPeriodoDeEntrada(valor);
                        break;
                    case "cpf":
                        participante.setCpf(valor);
                        break;
                    case "rg":
                        participante.setRg(valor);
                        break;
                    case "orgaoExpedidor":
                        participante.setOrgaoExpedidor(valor);
                        break;
                    case "sexo":
                        participante.setSexo(valor);
                        break;
                    case "estado":
                        participante.setEstado(valor);
                        break;
                    case "cidade":
                        participante.setCidade(valor);
                        break;
                    case "cep":
                        participante.setCEP(valor);
                        break;
                    case "bairro":
                        participante.setBairro(valor);
                        break;
                    case "numero":
                        participante.setNumero(valor);
                        break;
                    case "pais":
                        participante.setPais(valor);
                        break;
                    case "rua":
                        participante.setRua(valor);
                        break;
                    case "linkedin":
                        if (valor != null || !valor.isEmpty()) {
                            participante.setLinkedIn(valor);
                        }
                        break;
                    case "instagram":
                        if (valor != null || !valor.isEmpty()) {
                            participante.setInstagram(valor);
                        }
                        break;
                    case "github":
                        if (valor != null || !valor.isEmpty()) {
                            participante.setGithub(valor);
                        }
                        break;

                }
            }
        });

        if (erros.isEmpty()) {
            formService.adicionarParticipanteAoFormulario(participante);
            context.render("/forms/sucesso.html");
        } else {
            context.attribute("form", form);
            context.attribute("erros", erros);
            context.render("/forms/formulario.html");
        }
    }
}