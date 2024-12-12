package com.luizromao.consultep.domain.repository;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.domain.model.UserCep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCepRepository extends JpaRepository<UserCep, String> {

}
