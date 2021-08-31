package com.ricardorrj.cadastroProcesso.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Campo Nome é obrigatório")
    @Length(min = 3, max = 100, message = "O campo Nome deve conter no entre 3 e 100 caracteres!")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Campo CPF é obrigatório")
    @CPF(message = "Número do CPF deve ser válido, verifique!")
    private String cpf;

    @NotNull(message = "Campo Data nascimento é obrigatório")
    @Past(message = "A data de nascimento deve ser anterior a data atual, verifique!")
    private LocalDate dataNascimento;

    @NotNull(message = "Campo Data Cadastro é obrigatório")
    private LocalDate dataCadastro = LocalDate.now();

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<Processo> processo = new ArrayList<>();


    public Pessoa() {
        super();
    }

    public Pessoa(Long id, String nome, String cpf, LocalDate dataNascimento) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public List<Processo> getProcesso() {
        return processo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
