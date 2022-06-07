package com.example.reportmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class ReportView {

    @Id
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

    private Instant createdAt;

    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public ReportView(){}

    public ReportView(String id, String artistId, String hobbyistId, String response, String description, Boolean state, Instant createdAt) {
        this.id = id;
        this.artistId = artistId;
        this.hobbyistId = hobbyistId;
        this.response = response;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;

    }
}
