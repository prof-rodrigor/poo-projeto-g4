package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

public class ValidadorLinkedin implements ValidadorCampo {

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("O campo não pode estar vazio.", false);
        }

        // Regex para validar link do LinkedIn
        String regexLinkedin = "^(https?://)?(www\\.)?linkedin\\.com/in/[a-zA-Z0-9_-]+/?$";
        if (!valor.matches(regexLinkedin)) {
            return new ResultadoValidacao("O link do LinkedIn é inválido.", false);
        }

        return new ResultadoValidacao("Link do LinkedIn válido.", true);
    }
}
