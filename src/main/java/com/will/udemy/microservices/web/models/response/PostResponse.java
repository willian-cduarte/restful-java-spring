package com.will.udemy.microservices.web.models.response;

import com.will.udemy.microservices.domain.models.Post;
import com.will.udemy.microservices.infrastructure.formatter.DatePatternFormatting;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class PostResponse {

    @Getter @Setter private UUID id;
    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private UUID userId;
    @Getter @Setter private String createdAt;

    private DatePatternFormatting formater = new DatePatternFormatting();

    public PostResponse(Post post) {
        super();
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.userId = post.getUserId();
        this.createdAt = post.getCreatedAt();
    }

}
