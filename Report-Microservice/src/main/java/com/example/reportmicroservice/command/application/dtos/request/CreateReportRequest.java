package com.example.reportmicroservice.command.application.dtos.request;

import lombok.Value;

@Value
public class CreateReportRequest {

    private String artistId;

    private String hobbyistId;

    private String response;

    private String description;

    private Boolean state;
}
