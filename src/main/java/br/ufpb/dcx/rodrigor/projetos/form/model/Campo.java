package br.ufpb.dcx.rodrigor.projetos.form.model;

public class   Campo {

    private String id;
    private String label;
    private String valor;
    private ValidadorCampo validador;
    private boolean obrigatorio;
    private String tipo;

    public Campo(String id, String label, ValidadorCampo validador, boolean obrigatorio){
        this.id = id;
        this.label = label;
        this.validador = validador;
        this.obrigatorio = obrigatorio;
    }
    public Campo(String id, String label, ValidadorCampo validador, boolean obrigatorio, String tipo){
        this.id = id;
        this.label = label;
        this.validador = validador;
        this.obrigatorio = obrigatorio;
        this.tipo = tipo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ResultadoValidacao validar() {
        // Verifica se o campo é obrigatório e está vazio
        if (this.obrigatorio && (this.valor == null || this.valor.isEmpty())) {
            return new ResultadoValidacao("Campo obrigatório.", false);
        }

        // Valida o valor do campo usando o validador
        return validador.validar(this.valor);
    }


    public void setValidador(ValidadorCampo validador) {
        this.validador = validador;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }
}
