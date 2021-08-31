package com.ricardorrj.cadastroProcesso.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Processo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Campo NÚMERO é obrigatório")
    private Long numero;

    @Column(nullable = false)
    @NotNull(message = "Campo ANO é obrigatório")
    private Integer ano;

    @NotNull(message = "Campo Data Cadastro é obrigatório")
    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


    public Processo() {
        super();
    }

    public Processo(Long id, Long numero, Integer ano, Pessoa pessoa) {
        super();
        this.id = id;
        this.numero = numero;
        this.ano = ano;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processo processo = (Processo) o;
        return Objects.equals(id, processo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
