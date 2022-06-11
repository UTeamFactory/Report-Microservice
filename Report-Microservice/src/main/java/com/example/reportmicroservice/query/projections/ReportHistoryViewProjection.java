package com.example.reportmicroservice.query.projections;

import com.example.reportmicroservice.contracts.events.ReportEdited;
import com.example.reportmicroservice.contracts.events.ReportRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReportHistoryViewProjection {

    private final ReportHistoryViewRepository reportHistoryViewRepository;

    public ReportHistoryViewProjection(ReportHistoryViewRepository reportHistoryViewRepository) {
        this.reportHistoryViewRepository = reportHistoryViewRepository;
    }

    @EventHandler
    public void on(ReportRegistered event){
        ReportHistoryView reportHistoryView = new ReportHistoryView(
                event.getId(),
                event.getArtistId(),
                event.getHobbyistId(),
                event.getResponse(),
                event.getDescription(),
                event.getState(),
                event.getOccurredOn()
        );
        reportHistoryViewRepository.save(reportHistoryView);
    }

    @EventHandler
    public void on(ReportEdited event){
        Optional<ReportHistoryView> reportHistoryViewOptional = reportHistoryViewRepository.getLastByReportId(event.getId().toString());
        if(reportHistoryViewOptional.isPresent()) {
            ReportHistoryView reportHistoryView = reportHistoryViewOptional.get();
            reportHistoryView = new ReportHistoryView(reportHistoryView);

            reportHistoryView.setArtistId(event.getArtistId());
            reportHistoryView.setHobbyistId(event.getHobbyistId());
            reportHistoryView.setState(event.getState());
            reportHistoryView.setResponse(event.getResponse());
            reportHistoryView.setDescription(event.getDescription());
            reportHistoryView.setCreatedAt(event.getOccurredOn());

            reportHistoryViewRepository.save(reportHistoryView);
        }

    }
}
