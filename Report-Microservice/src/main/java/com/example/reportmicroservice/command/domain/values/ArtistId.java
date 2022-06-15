package com.example.reportmicroservice.command.domain.values;

import com.example.reportmicroservice.common.application.Notification;
import com.example.reportmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class ArtistId {
    String value;

    public ArtistId(String value) {
        this.value = value;
    }

    protected ArtistId(){
        value = "";
    }

    public static Result<ArtistId, Notification> create(String artistId) {
        Notification notification = new Notification();
        artistId = artistId == null ? "" : artistId.trim();

        if(artistId.isEmpty()){
            notification.addError("ArtistId is required", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new ArtistId(artistId));
    }
}
