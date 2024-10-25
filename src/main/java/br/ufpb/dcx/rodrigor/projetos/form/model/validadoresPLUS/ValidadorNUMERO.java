package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

public class ValidadorNUMERO implements ValidadorCampo {

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("O campo não pode estar vazio.", false);
        }

        // Verificar se o valor contém apenas dígitos
        if (!valor.matches("\\d+")) {
            return new ResultadoValidacao("O campo deve conter apenas números.", false);
        }

        return new ResultadoValidacao("Valor válido.", true);
    }
}