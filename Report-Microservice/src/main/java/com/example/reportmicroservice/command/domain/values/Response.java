package com.example.reportmicroservice.command.domain.values;

import com.example.reportmicroservice.common.application.Notification;
import com.example.reportmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Response {
    private String value;
    private final static int MAX_LENGTH = 150;
    private final static int MIN_LENGTH = 20;

    public Response(String response) {
        value = response;
    }
    protected Response() {
        this.value = "";
    }

    public static Result<Response, Notification> create(String response) {
        Notification notification = new Notification();
        response = response == null ? "" : response.trim();

        if (response.isEmpty()) {
            notification.addError("description is required", null);
            return Result.failure(notification);
        }

        if (response.length() > MAX_LENGTH) {
            notification.addError("The maximum length of a response is " + MAX_LENGTH + " characters including spaces", null);
        }

        if (response.length() < MIN_LENGTH) {
            notification.addError("The minimum length of a response is " + MIN_LENGTH + " characters including spaces", null);
        }

        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new Response(response));
    }
}
