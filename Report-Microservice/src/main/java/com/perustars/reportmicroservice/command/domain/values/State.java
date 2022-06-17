package com.perustars.reportmicroservice.command.domain.values;

import com.perustars.common.application.Notification;
import com.perustars.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class State {
    private Boolean value;

    public State(Boolean state) { value = state; }
    protected State(){
        this.value= true;
    }
    public static Result<State, Notification> create(Boolean state) {
        Notification notification = new Notification();

        if(state == null){
            notification.addError("state is required", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new State(state));
    }
}
