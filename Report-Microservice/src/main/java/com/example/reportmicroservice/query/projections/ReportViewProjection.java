package com.example.reportmicroservice.query.projections;

import com.example.reportmicroservice.contracts.events.ReportEdited;
import com.example.reportmicroservice.contracts.events.ReportRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class ReportViewProjection {

    private final ReportViewRepository reportViewRepository;
    private ReportRegistered event;

    public ReportViewProjection(ReportViewRepository reportViewRepository) {
        this.reportViewRepository = reportViewRepository;
    }

    @EventHandler
    public void on(ReportRegistered event){
        ReportView reportView = new ReportView(
                event.getId(),
                event.getArtistId(),
                event.getHobbyistId(),
                event.getResponse(),
                event.getDescription(),
                event.getState(),
                event.getOccurredOn()
        );
        reportViewRepository.save(reportView);
    }

    @EventHandler
    public void on(ReportEdited event){
        Optional<ReportView> reportViewOptional = reportViewRepository.findById(event.getId().toString());
        if(reportViewOptional.isPresent()){
            ReportView reportView = reportViewOptional.get();
            reportView.setArtistId(event.getArtistId());
            reportView.setDescription(event.getDescription());
            reportView.setHobbyistId(event.getHobbyistId());
            reportView.setResponse(event.getResponse());
            reportView.setState(event.getState());
            reportView.setUpdatedAt(event.getOccurredOn());
            reportViewRepository.save(reportView);
        }
    }
}
