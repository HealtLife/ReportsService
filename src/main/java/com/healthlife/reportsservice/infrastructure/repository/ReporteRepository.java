package com.healthlife.reportsservice.infrastructure.repository;

import com.healthlife.reportsservice.domain.models.agregates.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reportes,Long> {
    List<Reportes> findByUserDni(String userDni);

}
