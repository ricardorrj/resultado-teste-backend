package com.ricardorrj.cadastroProcesso.repositories;

import com.ricardorrj.cadastroProcesso.domain.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

}
