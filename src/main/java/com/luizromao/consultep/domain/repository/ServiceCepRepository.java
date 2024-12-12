package com.luizromao.consultep.domain.repository;

import com.luizromao.consultep.domain.model.ServiceCep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCepRepository extends JpaRepository<ServiceCep, String> {}
