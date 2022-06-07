package com.example.reportmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class ReportHistoryView {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private  Long reportHistoryId;

    @Column(length = 36) @Getter @Setter
    private String id;

    @Column(length = 36) @Getter @Setter
    private String artistId;

    @Column(length = 36) @Getter @Setter
    private String hobbyistId;

    @Column(length = 250) @Getter @Setter
    private String response;

    @Column(length = 250) @Getter @Setter
    private String description;

    @Column() @Getter @Setter
    private Boolean state;

    @Getter @Setter
    private Instant createdAt;

    public ReportHistoryView(){}

    public ReportHistoryView(String id, String artistId, String hobbyistId, String response, String description, Boolean state, Instant createdAt) {
        this.id = id;
        this.artistId = artistId;
        this.hobbyistId = hobbyistId;
        this.response = response;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
    }

    public ReportHistoryView(ReportHistoryView reportHistoryView) {
        this.id = reportHistoryView.getId();
        this.artistId = reportHistoryView.getArtistId();
        this.hobbyistId = reportHistoryView.getHobbyistId();
        this.response = reportHistoryView.getResponse();
        this.description = reportHistoryView.getDescription();
        this.state = reportHistoryView.getState();
        this.createdAt = reportHistoryView.getCreatedAt();
    }
}
