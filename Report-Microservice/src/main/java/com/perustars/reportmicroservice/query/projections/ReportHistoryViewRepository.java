package com.perustars.reportmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportHistoryViewRepository extends JpaRepository<ReportHistoryView,String>{
    @Query(value = "SELECT *" +
            "       FROM report_history_view" +
            "       WHERE report_history_id" +
            "           =(SELECT MAX(report_history_id)" +
            "               FROM report_history_view WHERE report_id = :reportId)",nativeQuery = true)
    Optional<ReportHistoryView> getLastByReportId(String reportId);

    @Query(value = "SELECT * FROM report_history_view WHERE report_id = :reportId ORDER BY created_at", nativeQuery = true)
    List<ReportHistoryView> getHistoryByReportId(String reportId);


}
