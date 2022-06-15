package com.example.reportmicroservice.command.domain.values;

import com.example.reportmicroservice.common.application.Notification;
import com.example.reportmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class HobbyistId {
    String value;

    public HobbyistId(String value) {
        this.value = value;
    }

    protected HobbyistId(){
        value = "";
    }

    public static Result<HobbyistId, Notification> create(String hobbyistId) {
        Notification notification = new Notification();
        hobbyistId = hobbyistId == null ? "" : hobbyistId.trim();

        if(hobbyistId.isEmpty()){
            notification.addError("HobbyistId is required", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new HobbyistId(hobbyistId));
    }
}
