package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

public class ValidadorPeriodoEntrada implements ValidadorCampo {
    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return validar(valor);
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("O campo não pode estar vazio.", false);
        }

        // Verificar se o valor segue o formato YYYY.X
        if (!valor.matches("^(19|20)\\d{2}\\.[1-2]$")) {
            return new ResultadoValidacao("O campo deve estar no formato YYYY.X, onde YYYY é um ano (1900 a 2099) e X é um número de 1 a 9.", false);
        }

        return new ResultadoValidacao("Valor válido.", true);
    }
}
