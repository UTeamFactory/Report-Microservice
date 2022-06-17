package com.perustars.reportmicroservice.contracts.events;

import lombok.Value;

import java.time.Instant;

@Value
public class ReportEdited {
    private String id;

    private String artistId;

    private String hobbyistId;

    private String response;

    private String description;

    private Boolean state;

    private Instant occurredOn;
}
