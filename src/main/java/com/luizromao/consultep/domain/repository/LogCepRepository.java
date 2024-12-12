package com.luizromao.consultep.domain.repository;

import com.luizromao.consultep.domain.model.LogCep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogCepRepository extends JpaRepository<LogCep, String> {

  Page<LogCep> findByUserCepId(String userCepId, Pageable pageable);

  Page<LogCep> findByCep(String cep, Pageable pageable);
}
