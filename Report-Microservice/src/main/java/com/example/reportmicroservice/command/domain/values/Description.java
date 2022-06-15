package com.example.reportmicroservice.command.domain.values;

import com.example.reportmicroservice.common.application.Notification;
import com.example.reportmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Description {
    private String value;
    private final static int MAX_LENGTH = 200;
    private final static int MIN_LENGTH = 20;

    public Description(String description) { value = description; }

    protected Description() {
        this.value = "";
    }

    public static Result<Description, Notification> create(String description) {
        Notification notification = new Notification();
        description = description == null ? "" : description.trim();

        if(description.isEmpty()){
            notification.addError("description is required", null);
            return Result.failure(notification);
        }

        if(description.length() > MAX_LENGTH){
            notification.addError("The maximum length of an description is " + MAX_LENGTH + " characters including spaces", null);
        }

        if(description.length() < MIN_LENGTH){
            notification.addError("The minimum length of an description is " + MIN_LENGTH + " characters including spaces", null);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new Description(description));
    }
}
