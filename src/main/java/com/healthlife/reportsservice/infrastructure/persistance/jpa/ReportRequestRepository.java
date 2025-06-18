package com.healthlife.reportsservice.infrastructure.persistance.jpa;

import com.healthlife.reportsservice.domain.model.aggregates.ReportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRequestRepository extends JpaRepository<ReportRequest, Long> {
    List<ReportRequest> findByDni(String dni);
}