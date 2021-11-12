package br.com.tt.chat.model;

public class Atendente {

    // ATRIBUTOS
    private Long id;
    private String nome;
    private Senioridade senioridade;
    private Nivel nivel;
    private StatusAtendente status;

    public Atendente(String nome){
        //nome = nome; //não funciona pois atribui o param para o param
        this.nome = nome; // this se refere ao ATRIBUTO (ou métodos)
    }

    public Atendente(String nome, Senioridade senioridade, Nivel nivel) {
        this.nome = nome;
        this.senioridade = senioridade;
        this.nivel = nivel;
        this.status = StatusAtendente.ATIVO;
    }

    public Atendente(Long id, String nome, StatusAtendente status, Nivel nivel,
                     Senioridade senioridade) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.nivel = nivel;
        this.senioridade = senioridade;
    }

    public String getNome() {
        return nome;
    }

    public StatusAtendente getStatus() {
        return status;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public Senioridade getSenioridade() {
        return senioridade;
    }

    public String getDescricao(){
        return String.format("Nome: %s - Status: %s - Senioridade: %s - Nível: %s",
                nome, status, senioridade, nivel);
    }

    /*@Override // Anotação de Sobrescrita - Ou seja, substituindo o método da Herança (Object)
    public String toString(){
        return String.format("Nome: %s - Status: %s - Senioridade: %s - Nível: %s",
                nome, status, senioridade, nivel);
    }*/
}
