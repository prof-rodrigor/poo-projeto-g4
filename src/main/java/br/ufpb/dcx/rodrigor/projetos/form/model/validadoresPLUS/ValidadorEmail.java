package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

import java.util.regex.Pattern;

public class ValidadorEmail implements ValidadorCampo {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("O e-mail não pode estar vazio.", false);
        }

        if (!EMAIL_PATTERN.matcher(valor).matches()) {
            return new ResultadoValidacao("E-mail inválido.", false);
        }
        return new ResultadoValidacao("E-mail válido.", true);
    }
}
