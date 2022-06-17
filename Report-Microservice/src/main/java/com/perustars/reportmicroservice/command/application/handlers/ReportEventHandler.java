package com.perustars.reportmicroservice.command.application.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import com.perustars.reportmicroservice.command.infrastructure.*;
import com.perustars.reportcontracts.events.*;
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
