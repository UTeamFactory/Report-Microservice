package com.example.reportmicroservice.command.application.dtos.response;

import lombok.Value;

@Value
public class CreateReportResponse {
    private String id;

    private String artistId;

    private String hobbyistId;

    private String response;

    private String description;

    private Boolean state;
}
