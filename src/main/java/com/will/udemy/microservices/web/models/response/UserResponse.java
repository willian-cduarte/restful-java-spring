package com.will.udemy.microservices.web.models.response;

import com.will.udemy.microservices.domain.models.User;
import com.will.udemy.microservices.infrastructure.formatter.DatePatternFormatting;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public class UserResponse {

    @Getter @Setter private UUID id;
    @Getter @Setter private String name;
    @Getter @Setter private String birthDate;
    @Getter @Setter private String createdAt;

    private DatePatternFormatting formater = new DatePatternFormatting();


    public UserResponse(User user) {
        super();
        this.id = user.getId();
        this.name = user.getName();

        this.birthDate = formater.birthDateToString(user.getBirthDate());
        this.createdAt = formater.toStringTimestamp(user.getCreatedAt());

    }

}
