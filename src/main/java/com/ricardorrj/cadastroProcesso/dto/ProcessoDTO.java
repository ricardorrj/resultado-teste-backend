package com.ricardorrj.cadastroProcesso.dto;

import com.ricardorrj.cadastroProcesso.domain.Processo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ProcessoDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    @NotNull(message = "Campo NÚMERO é obrigatório")
    private Long numero;

    @NotNull(message = "Campo ANO é obrigatório")
    private Integer ano;

    @NotNull(message = "Campo Data Cadastro é obrigatório")
    private LocalDate dataCadastro;

    public ProcessoDTO(Processo obj) {
        this.id = obj.getId();
        this.numero = obj.getNumero();
        this.ano = obj.getAno();
        this.dataCadastro = obj.getDataCadastro();
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

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
