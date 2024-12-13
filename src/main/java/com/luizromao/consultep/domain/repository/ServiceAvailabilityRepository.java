package com.luizromao.consultep.domain.repository;

import com.luizromao.consultep.domain.model.ServiceAvailability;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAvailabilityRepository extends JpaRepository<ServiceAvailability, String> {

  Optional<ServiceAvailability> findByServiceCepIdAndCep(String serviceCepId, String cep);

  Page<ServiceAvailability> findByCep(String cep, Pageable pageable);
}
