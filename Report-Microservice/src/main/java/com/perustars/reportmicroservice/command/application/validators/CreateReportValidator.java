package com.perustars.reportmicroservice.command.application.validators;

import com.perustars.reportmicroservice.command.application.dtos.request.CreateReportRequest;
import com.perustars.reportmicroservice.command.infrastructure.ReportRegistryRepository;
import com.perustars.common.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class CreateReportValidator {

    private final ReportRegistryRepository reportRegistryRepository;

    public CreateReportValidator(ReportRegistryRepository reportRegistryRepository) {
        this.reportRegistryRepository = reportRegistryRepository;
    }

    public Notification validate(CreateReportRequest createReportRequest){
        Notification notification = new Notification();

        String artistId = createReportRequest.getArtistId().trim();
        if(artistId.isEmpty()){
            notification.addError("Artist Id is required");
        }

        String hobbyistId = createReportRequest.getHobbyistId().trim();
        if(artistId.isEmpty()){
            notification.addError("Hobbyist Id is required");
        }

        String description = createReportRequest.getDescription().trim();
        if(artistId.isEmpty()){
            notification.addError("Description is required");
        }

        if(notification.hasErrors()) {
            return notification;
        }
        return notification;

    }
}
