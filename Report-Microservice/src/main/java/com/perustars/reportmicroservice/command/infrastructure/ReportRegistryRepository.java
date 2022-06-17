package com.perustars.reportmicroservice.command.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRegistryRepository extends JpaRepository<ReportRegistry, String> {
    Optional<ReportRegistry> getByReportId(String reportRegistryId);

}
