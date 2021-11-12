package br.com.tt.chat.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usuario {
    private Long id;
    private String nome;
    private String cpf;
    private Sexo sexo;
    private Perfil perfil;
    private String telefone;
    private String email;
    private String endereco;
    private LocalDate nascimento;

    public Usuario(Long id, String nome, String cpf, Sexo sexo, Perfil perfil, String telefone, String email, String endereco, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.perfil = perfil;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.nascimento = nascimento;
    }

    public String getDescricao() {
        String nascimentoFormatado = this.nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("%s - %s - %s - %s - %s - %s - %s", nome, cpf, sexo, perfil,
                telefone, email, nascimentoFormatado);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
}
