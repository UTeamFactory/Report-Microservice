package com.example.reportmicroservice.command.domain.entities;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Report {

    @AggregateIdentifier
    private String id;

    private String hobbyistId;

    private String artistId;

    private String description;

    private String response;

    private Boolean state;

    public Report(){}

}
