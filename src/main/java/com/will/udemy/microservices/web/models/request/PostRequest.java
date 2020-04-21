package com.will.udemy.microservices.web.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PostRequest {

    @ApiModelProperty(name = "title", notes="Title should have atleast 2 characters")
    private String title;

    @ApiModelProperty(name = "description", notes="Title should have atleast 2 characters")
    private String description;

    @ApiModelProperty(name = "userId", notes="Title should have atleast 2 characters")
    private UUID userId;

}
