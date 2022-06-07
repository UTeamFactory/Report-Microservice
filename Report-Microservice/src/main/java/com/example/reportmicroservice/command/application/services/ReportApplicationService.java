package com.example.reportmicroservice.command.application.services;

import com.example.reportmicroservice.command.application.dtos.request.CreateReportRequest;
import com.example.reportmicroservice.command.application.dtos.request.EditReportRequest;
import com.example.reportmicroservice.command.application.dtos.response.CreateReportResponse;
import com.example.reportmicroservice.command.application.dtos.response.EditReportResponse;
import com.example.reportmicroservice.command.application.validators.CreateReportValidator;
import com.example.reportmicroservice.command.application.validators.EditReportValidator;
import com.example.reportmicroservice.command.infrastructure.ReportRegistryRepository;
import com.example.reportmicroservice.common.application.Notification;
import com.example.reportmicroservice.common.application.Result;
import com.example.reportmicroservice.common.application.ResultType;
import com.example.reportmicroservice.contracts.commands.EditReport;
import com.example.reportmicroservice.contracts.commands.RegisterReport;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ReportApplicationService {

    private final CreateReportValidator createReportValidator;

    private final EditReportValidator editReportValidator;

    protected  final CommandGateway commandGateway;

    private final ReportRegistryRepository reportRegistryRepository;

    public ReportApplicationService(CreateReportValidator createReportValidator, EditReportValidator editReportValidator, CommandGateway commandGateway, ReportRegistryRepository reportRegistryRepository) {
        this.createReportValidator = createReportValidator;
        this.editReportValidator = editReportValidator;
        this.commandGateway = commandGateway;
        this.reportRegistryRepository = reportRegistryRepository;
    }

    public Result<CreateReportResponse, Notification> create(CreateReportRequest createReportRequest) throws Exception{
        Notification notification = this.createReportValidator.validate(createReportRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String reportId = UUID.randomUUID().toString();
        RegisterReport createReport = new RegisterReport(
            reportId,
                createReportRequest.getArtistId().trim(),
                createReportRequest.getHobbyistId().trim(),
                createReportRequest.getResponse().trim(),
                createReportRequest.getDescription().trim(),
                createReportRequest.getState()

        );
        CompletableFuture<Object> future = commandGateway.send(createReport);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        CreateReportResponse createReportResponseDto = new CreateReportResponse(
            createReport.getId(),
            createReport.getArtistId(),
            createReport.getHobbyistId(),
            createReport.getResponse(),
            createReport.getDescription(),
            createReport.getState()
        );
        return Result.success(createReportResponseDto);
    }

    public Result<EditReportResponse,Notification> edit(EditReportRequest editReportRequest) throws Exception {
        Notification notification = this.editReportValidator.validate(editReportRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditReport editReport = new EditReport(
                editReportRequest.getId().trim(),
                editReportRequest.getArtistId().trim(),
                editReportRequest.getHobbyistId().trim(),
                editReportRequest.getResponse().trim(),
                editReportRequest.getDescription().trim(),
                editReportRequest.getState()
        );
        CompletableFuture<Object> future = commandGateway.send(editReport);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditReportResponse editReportResponse = new EditReportResponse(
            editReport.getId(),
            editReport.getArtistId(),
            editReport.getHobbyistId(),
            editReport.getResponse(),
            editReport.getDescription(),
            editReport.getState()
        );
        return Result.success(editReportResponse);
    }
}
