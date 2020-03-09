package com.will.udemy.webapi.restfulwebservices.interfaces.web.models.response;

import com.will.udemy.webapi.restfulwebservices.application.domain.Post;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FullDataUserResponse {

    private UUID id;

    @ApiModelProperty(notes="User name")
    private String name;

    @ApiModelProperty(notes="Birth date")
    private Date birthDate;

    @ApiModelProperty(notes="paginated post list of user")
    private List<Post> posts;
}
