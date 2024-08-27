package br.ufpb.dcx.rodrigor.projetos.participante.model;

import org.bson.types.ObjectId;

public class Participante {

    // Funcionais
    private ObjectId id;
    private String matricula;
    private String periodoDeEntrada;
    private String curso;
    private String cpf;
    private String rg;
    private String orgaoExpedidor;
    private CategoriaParticipante categoria;

    // Contato
    private String nome;
    private String sexo;
    private String sobrenome;
    private String telefone;
    private String email;
    private String instagram;
    private String linkedIn;
    private String GitHub;
    private String endereco;


    // Construtor

    public Participante() {
        this.id = id;
        this.matricula = matricula;
        this.periodoDeEntrada = periodoDeEntrada;
        this.curso = curso;
        this.cpf = cpf;
        this.rg = rg;
        this.orgaoExpedidor = orgaoExpedidor;
        this.categoria = categoria;
        this.nome = nome;
        this.sexo = sexo;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.instagram = instagram;
        this.linkedIn = linkedIn;
        this.GitHub = GitHub;
        this.endereco = endereco;
    }


    // Getters and Setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPeriodoDeEntrada() {
        return periodoDeEntrada;
    }

    public void setPeriodoDeEntrada(String periodoDeEntrada) {
        this.periodoDeEntrada = periodoDeEntrada;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGitHub() {
        return GitHub;
    }

    public void setGithub(String GitHub) {
        this.GitHub = GitHub;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CategoriaParticipante getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaParticipante categoriaParticipante) {
        this.categoria = categoriaParticipante;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "endereco='" + endereco + '\'' +
                ", GitHub='" + GitHub + '\'' +
                ", linkedIn='" + linkedIn + '\'' +
                ", instagram='" + instagram + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", orgaoExpedidor='" + orgaoExpedidor + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", curso='" + curso + '\'' +
                ", periodoDeEntrada='" + periodoDeEntrada + '\'' +
                ", matricula='" + matricula + '\'' +
                ", id=" + id +
                '}';
    }
}