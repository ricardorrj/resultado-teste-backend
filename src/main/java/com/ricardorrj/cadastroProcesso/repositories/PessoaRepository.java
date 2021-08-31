package com.ricardorrj.cadastroProcesso.repositories;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "select p from Pessoa p where p.cpf = :cpf")
    Pessoa findByCpf(@Param(value = "cpf") String cpf);

}
