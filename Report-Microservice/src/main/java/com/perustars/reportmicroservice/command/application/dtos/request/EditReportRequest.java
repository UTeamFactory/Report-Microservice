package com.perustars.reportmicroservice.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditReportRequest {

    private @Getter @Setter String id;

    private @Getter String artistId;

    private @Getter String hobbyistId;

    private @Getter String response;

    private @Getter String description;

    private @Getter Boolean state;
}
