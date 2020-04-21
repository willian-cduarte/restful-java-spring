package com.will.udemy.microservices.web.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {

    @ApiModelProperty(notes="Name should have at least 2 characters")
    private String name;

    @ApiModelProperty(notes="Birth date should be in the past")
    private Date birthDate;

}
