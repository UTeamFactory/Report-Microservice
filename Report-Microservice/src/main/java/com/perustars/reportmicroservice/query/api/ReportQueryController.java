package com.perustars.reportmicroservice.query.api;

import com.perustars.reportmicroservice.query.projections.ReportHistoryView;
import com.perustars.reportmicroservice.query.projections.ReportHistoryViewRepository;
import com.perustars.reportmicroservice.query.projections.ReportView;
import com.perustars.reportmicroservice.query.projections.ReportViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
@Tag(name="Reports")
public class ReportQueryController {

    private final ReportViewRepository reportViewRepository;

    private final ReportHistoryViewRepository reportHistoryViewRepository;

    public ReportQueryController(ReportViewRepository reportViewRepository, ReportHistoryViewRepository reportHistoryViewRepository) {
        this.reportViewRepository = reportViewRepository;
        this.reportHistoryViewRepository = reportHistoryViewRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get all reports")
    public ResponseEntity<List<ReportView>> getAllReports(){
        try{
            return new ResponseEntity<List<ReportView>>(reportViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Report by id")
    public ResponseEntity<ReportView> getById(@PathVariable("id") String id){
        try{
            Optional<ReportView> reportViewOptional = reportViewRepository.findById(id);
            if(reportViewOptional.isPresent()){
                return new ResponseEntity<ReportView>(reportViewOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND",HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/history/{id}")
    @Operation(summary = "Get report history")
    public ResponseEntity<List<ReportHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<ReportHistoryView> reports = reportHistoryViewRepository.getHistoryByReportId(id);
            return new ResponseEntity<List<ReportHistoryView>>(reports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
