package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;
import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;

public class ValidadorTexto implements ValidadorCampo {

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return new ResultadoValidacao("O campo não pode ser vazio", false);
        }

        // Regex para aceitar apenas letras, acentos, espaços, sinais de pontuação e "ç"
        String regex = "^[a-zA-Z\\s.,;:!?'\"()\\[\\]{}\\-áéíóúãâêîôûçÇ]*$";

        if (!valor.matches(regex)) {
            return new ResultadoValidacao("O campo deve conter apenas letras.", false);
        }

        return new ResultadoValidacao("Texto válido", true);
    }
}