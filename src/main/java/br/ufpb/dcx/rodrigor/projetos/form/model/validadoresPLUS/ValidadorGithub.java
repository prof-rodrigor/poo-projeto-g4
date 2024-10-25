package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

public class ValidadorGithub implements ValidadorCampo {

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {

        // Pode ficar vazio, já que é um campo opcional
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("Se deseja, adicione depois!", true);
        }

        // Regex para validar link do GitHub
        String regexGithub = "^(https?://)?(www\\.)?github\\.com/[a-zA-Z0-9._-]+/?$";
        if (!valor.matches(regexGithub)) {
            return new ResultadoValidacao("O link do GitHub é inválido.", false);
        }

        return new ResultadoValidacao("Link do GitHub válido.", true);
    }
}