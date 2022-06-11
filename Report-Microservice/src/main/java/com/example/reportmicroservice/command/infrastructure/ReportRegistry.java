package com.example.reportmicroservice.command.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportRegistry {

    @Id
    @Column
    private String reportId;

    private String artistId;

    private String hobbyistId;

    private String response;

    private String description;

    private Boolean state;

    public ReportRegistry(){}

    public ReportRegistry(String reportId, String artistId, String hobbyistId, String response, String description, Boolean state) {
        this.reportId = reportId;
        this.artistId = artistId;
        this.hobbyistId = hobbyistId;
        this.response = response;
        this.description = description;
        this.state = state;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getHobbyistId() {
        return hobbyistId;
    }

    public void setHobbyistId(String hobbyistId) {
        this.hobbyistId = hobbyistId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
