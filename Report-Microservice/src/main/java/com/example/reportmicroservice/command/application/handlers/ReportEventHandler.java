package com.example.reportmicroservice.command.application.handlers;

import com.example.reportmicroservice.command.infrastructure.ReportRegistry;
import com.example.reportmicroservice.command.infrastructure.ReportRegistryRepository;
import com.example.reportmicroservice.contracts.events.ReportEdited;
import com.example.reportmicroservice.contracts.events.ReportRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("reportRegistry")
public class ReportEventHandler {

   private final ReportRegistryRepository reportRegistryRepository;

    public ReportEventHandler(ReportRegistryRepository reportRegistryRepository) {
        this.reportRegistryRepository = reportRegistryRepository;
    }

    @EventHandler
    public void on(ReportRegistered event){
        reportRegistryRepository.save(new ReportRegistry(
                event.getReportId(),
                event.getArtistId(),
                event.getHobbyistId(),
                event.getResponse(),
                event.getDescription(),
                event.getState()
        ));
    }

    @EventHandler
    public void on(ReportEdited event) {
        Optional<ReportRegistry> ReportRegistryOptional = reportRegistryRepository.getByReportId(event.getId());
        ReportRegistryOptional.ifPresent(reportRegistryRepository::delete);
        reportRegistryRepository.save(new ReportRegistry(
                event.getId(),
                event.getArtistId(),
                event.getHobbyistId(),
                event.getResponse(),
                event.getDescription(),
                event.getState()
        ));
    }

}
