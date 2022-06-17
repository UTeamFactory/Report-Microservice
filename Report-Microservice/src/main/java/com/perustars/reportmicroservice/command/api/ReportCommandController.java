package com.perustars.reportmicroservice.command.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perustars.common.api.ApiController;
import com.perustars.common.application.Notification;
import com.perustars.common.application.Result;
import com.perustars.reportmicroservice.command.application.dtos.request.CreateReportRequest;
import com.perustars.reportmicroservice.command.application.dtos.request.EditReportRequest;
import com.perustars.reportmicroservice.command.application.dtos.response.CreateReportResponse;
import com.perustars.reportmicroservice.command.application.dtos.response.EditReportResponse;
import com.perustars.reportmicroservice.command.application.services.ReportApplicationService;
import com.perustars.reportmicroservice.command.infrastructure.ReportRegistryRepository;

@RestController
@RequestMapping("/reports")
@Tag(name="Reports")
public class ReportCommandController {

    private final ReportApplicationService reportApplicationService;

    private final CommandGateway commandGateway;

    private final ReportRegistryRepository reportRegistryRepository;

    public ReportCommandController(ReportApplicationService reportApplicationService, CommandGateway commandGateway, ReportRegistryRepository reportRegistryRepository) {
        this.reportApplicationService = reportApplicationService;
        this.commandGateway = commandGateway;
        this.reportRegistryRepository = reportRegistryRepository;
    }

    @PostMapping(path = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateReportRequest createReportRequest){
        try{
            Result<CreateReportResponse, Notification> result = reportApplicationService.create(createReportRequest);
            if(result.isSuccess()){
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e){
            return ApiController.serverError();
        }
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<Object> edit(@PathVariable("reportId") String reportId, @RequestBody EditReportRequest editReportRequest) {

        try{
            editReportRequest.setId(reportId);
            Result<EditReportResponse, Notification> result = reportApplicationService.edit(editReportRequest);
            if (result.isSuccess()){
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch(Exception e){
            return ApiController.serverError();
        }
    }

    }
