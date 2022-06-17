package com.perustars.reportmicroservice.command.application.validators;

import com.perustars.reportmicroservice.command.application.dtos.request.EditReportRequest;
import com.perustars.reportmicroservice.command.domain.entities.Report;
import com.perustars.reportmicroservice.command.infrastructure.ReportRegistryRepository;
import com.perustars.common.application.Notification;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.stereotype.Component;
import org.axonframework.modelling.command.Repository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;

@Component
public class EditReportValidator {

    private final ReportRegistryRepository reportRegistryRepository;

    private final Repository<Report> reportRepository;

    public EditReportValidator(ReportRegistryRepository reportRegistryRepository, Repository<Report> reportRepository) {
        this.reportRegistryRepository = reportRegistryRepository;
        this.reportRepository = reportRepository;
    }

    public Notification validate(EditReportRequest editReportRequest){
        Notification notification = new Notification();

        String reportId = editReportRequest.getId().trim();
        if(reportId.isEmpty()){
            notification.addError("Artist Id is required");
        }
        loadReportAggregate(reportId);

        String artistId = editReportRequest.getArtistId().trim();
        if(artistId.isEmpty()){
            notification.addError("Artist Id is required");
        }
        String hobbyistId = editReportRequest.getHobbyistId().trim();
        if(artistId.isEmpty()){
            notification.addError("Hobbyist Id is required");
        }

        String description = editReportRequest.getDescription().trim();
        if(artistId.isEmpty()){
            notification.addError("Description is required");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadReportAggregate(String reportId){
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            reportRepository.load(reportId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }


}
