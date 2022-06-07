package com.example.reportmicroservice.query.projections;

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
            "               FROM report_history_id WHERE report_id = :report_id)",nativeQuery = true)
    Optional<ReportHistoryView> getLastById(String id);

    @Query(value = "SELECT * FROM report_history_view WHERE report_id = :reportId ORDER BY created_at", nativeQuery = true)
    List<ReportHistoryView> getReportHistoryById(String id);


}
