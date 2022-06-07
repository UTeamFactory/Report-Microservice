package com.example.reportmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterReport {

    @TargetAggregateIdentifier
    private String id;

    private String artistId;

    private String hobbyistId;

    private String response;

    private String description;

    private Boolean state;

    public String getId() {
        return id;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getHobbyistId() {
        return hobbyistId;
    }

    public String getResponse() {
        return response;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getState() {
        return state;
    }

    public RegisterReport(String id, String artistId, String hobbyistId, String response, String description, Boolean state) {
        this.id = id;
        this.artistId = artistId;
        this.hobbyistId = hobbyistId;
        this.response = response;
        this.description = description;
        this.state = state;
    }
}
