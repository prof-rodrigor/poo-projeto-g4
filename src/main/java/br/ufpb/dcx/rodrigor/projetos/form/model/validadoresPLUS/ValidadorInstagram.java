package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

public class ValidadorInstagram implements ValidadorCampo {

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("O campo não pode estar vazio.", false);
        }

        // Regex para validar link do Instagram
        String regexInstagram = "^(https?://)?(www\\.)?instagram\\.com/[a-zA-Z0-9._]+/?$";
        if (!valor.matches(regexInstagram)) {
            return new ResultadoValidacao("O link do Instagram é inválido.", false);
        }

        return new ResultadoValidacao("Link do Instagram válido.", true);
    }
}
