package br.ufpb.dcx.rodrigor.projetos.form.model.validadoresPLUS;

import br.ufpb.dcx.rodrigor.projetos.form.model.ResultadoValidacao;
import br.ufpb.dcx.rodrigor.projetos.form.model.ValidadorCampo;

public class ValidadorCPF implements ValidadorCampo {

    @Override
    public ResultadoValidacao validarCampo(String valor) {
        return null;
    }

    @Override
    public ResultadoValidacao validar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return new ResultadoValidacao("O CPF não pode estar vazio.", false);
        }

        // Removendo caracteres não numéricos
        String cpf = valor.replaceAll("[^0-9]", "");

        // Verificar se o CPF contém apenas numeros
        if (!cpf.matches("\\d+")) {
            return new ResultadoValidacao("O CPF deve conter apenas dígitos.", false);
        }

        // Validação do CPF
        if (cpf.length() != 11 || !VALIDACPF(cpf)) {
            return new ResultadoValidacao("CPF inválido.", false);
        }
        return new ResultadoValidacao("CPF válido.", true);
    }

    private boolean VALIDACPF(String cpf) {
        // CPF com todos os dígitos iguais (ex: 111.111.111-11) é inválido
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        // Calcula os dígitos verificadores
        int primeiroDigito = calcularDigitoVerificador(cpf, 10);
        int segundoDigito = calcularDigitoVerificador(cpf, 11);

        // Compara os dígitos verificadores calculados com os do CPF
        return primeiroDigito == Character.getNumericValue(cpf.charAt(9)) &&
                segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    private int calcularDigitoVerificador(String cpf, int peso) {
        int soma = 0;
        int pesoTemp = peso;  // Variável temporária para decrementar o peso
        for (int i = 0; i < peso - 1; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * pesoTemp;
            pesoTemp--;  // Decrementar o peso temporário, sem alterar o original
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
