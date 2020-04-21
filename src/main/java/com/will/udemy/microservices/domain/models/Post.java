package com.will.udemy.microservices.domain.models;

import com.will.udemy.microservices.infrastructure.formatter.DatePatternFormatting;
import com.will.udemy.microservices.web.models.request.PostRequest;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "not have be null")
    @NotBlank(message = "must not be blank")
    @NotEmpty(message = "must not be empty")
    @Size(min = 3, max = 255, message = "must be between 3 and 255 characters")
    private String title;

    //@Lob @Type(type = "org.hibernate.type.TextType") //2147483646
    @NotNull(message = "not have be null")
    @NotBlank(message = "must not be blank")
    @NotEmpty(message = "must not be empty")
    @Size(min = 5, max = 255, message = "must be between 5 and 255 characters")
    private String description;

    private UUID userId;

    private String createdAt;

    protected Post() {

    }

    public Post(@Valid PostRequest request) {
        super();
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.userId = request.getUserId();
        this.createdAt = new DatePatternFormatting().toStringTimestamp(new Date());

    }


}
