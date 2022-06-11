package com.example.reportmicroservice.command.domain.entities;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.example.reportmicroservice.contracts.commands.EditReport;
import com.example.reportmicroservice.contracts.commands.RegisterReport;
import com.example.reportmicroservice.contracts.events.ReportEdited;
import com.example.reportmicroservice.contracts.events.ReportRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@Aggregate
public class Report {

    @AggregateIdentifier
    private String reportId;

    private String hobbyistId;

    private String artistId;

    private String description;

    private String response;

    private Boolean state;

    public Report(){}

    @CommandHandler
    public Report(RegisterReport command){
        Instant now = Instant.now();
        apply(
                new ReportRegistered(
                        command.getReportId(),
                        command.getArtistId(),
                        command.getHobbyistId(),
                        command.getResponse(),
                        command.getDescription(),
                        command.getState(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditReport command){
        Instant now = Instant.now();
        apply(
                new ReportEdited(
                        command.getReportId(),
                        command.getArtistId(),
                        command.getHobbyistId(),
                        command.getResponse(),
                        command.getDescription(),
                        command.getState(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on(ReportRegistered event){
        reportId = event.getReportId();
        artistId = event.getArtistId();
        hobbyistId = event.getHobbyistId();
        response = event.getResponse();
        description = event.getDescription();
        state = event.getState();
    }

    @EventSourcingHandler
    protected void on(ReportEdited event){
        artistId = event.getArtistId();
        hobbyistId = event.getHobbyistId();
        response = event.getResponse();
        description = event.getDescription();
        state = event.getState();
    }

}
