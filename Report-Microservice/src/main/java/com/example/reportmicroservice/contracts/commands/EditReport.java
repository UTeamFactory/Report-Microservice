package com.example.reportmicroservice.contracts.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class EditReport {

    @TargetAggregateIdentifier
    private String reportId;

    private String artistId;

    private String hobbyistId;

    private String response;

    private String description;

    private Boolean state;

    public String getReportId() {
        return reportId;
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

    public EditReport(String reportId, String artistId, String hobbyistId, String response, String description, Boolean state) {
        this.reportId = reportId;
        this.artistId = artistId;
        this.hobbyistId = hobbyistId;
        this.response = response;
        this.description = description;
        this.state = state;
    }
}
