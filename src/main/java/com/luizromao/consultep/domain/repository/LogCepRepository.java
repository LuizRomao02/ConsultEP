package com.luizromao.consultep.domain.repository;

import com.luizromao.consultep.domain.model.LogCep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogCepRepository extends JpaRepository<LogCep, String> {

  List<LogCep> findByUserCepId(String userCepId);
}
