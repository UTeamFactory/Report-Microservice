package com.example.reportmicroservice.command.domain.entities;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.example.reportmicroservice.command.domain.values.*;
import com.example.reportmicroservice.contracts.commands.EditReport;
import com.example.reportmicroservice.contracts.commands.RegisterReport;
import com.example.reportmicroservice.contracts.events.ReportEdited;
import com.example.reportmicroservice.contracts.events.ReportRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.Instant;

@Aggregate
public class Report {

    @AggregateIdentifier
    private String reportId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "hobbyist_id", nullable = false))
    })
    private HobbyistId hobbyistId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "artist_id", nullable = false))
    })
    private ArtistId artistId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "description", length = 200, nullable = false))
    })
    private Description description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "response", length = 100, nullable = false))
    })
    private Response response;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "state", nullable = false))
    })
    private State state;

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
        artistId = new ArtistId(event.getArtistId());
        hobbyistId = new HobbyistId(event.getHobbyistId());
        response = new Response(event.getResponse());
        description = new Description(event.getDescription());
        state = new State(event.getState());
    }

    @EventSourcingHandler
    protected void on(ReportEdited event){
        artistId = new ArtistId(event.getArtistId());
        hobbyistId = new HobbyistId(event.getHobbyistId());
        response = new Response(event.getResponse());
        description = new Description(event.getDescription());
        state = new State(event.getState());
    }

}
