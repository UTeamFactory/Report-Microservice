package com.example.reportmicroservice.contracts.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class EditReport {

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

    public EditReport(String id, String artistId, String hobbyistId, String response, String description, Boolean state) {
        this.id = id;
        this.artistId = artistId;
        this.hobbyistId = hobbyistId;
        this.response = response;
        this.description = description;
        this.state = state;
    }
}
