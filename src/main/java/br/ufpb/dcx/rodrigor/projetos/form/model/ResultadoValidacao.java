package br.ufpb.dcx.rodrigor.projetos.form.model;

public class ResultadoValidacao {
    private final String mensagem;
    private final boolean ok;

    // Construtor para mensagens personalizadas
    public ResultadoValidacao(String mensagem, boolean ok) {
        this.mensagem = mensagem;
        this.ok = ok;
    }


    public String getMensagem() {
        return mensagem;
    }

    public boolean isOk() {
        return ok;
    }
}