package com.perustars.reportmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportViewRepository extends JpaRepository<ReportView, String> {
}
