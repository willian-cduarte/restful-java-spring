package com.will.udemy.webapi.restfulwebservices.application.controllers;

import com.will.udemy.webapi.restfulwebservices.application.domain.Post;
import com.will.udemy.webapi.restfulwebservices.interfaces.web.controllers.PostWebController;
import com.will.udemy.webapi.restfulwebservices.interfaces.web.models.response.PaginationResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public class PostController implements PostWebController {

    @Override
    public PaginationResponse<Post> retrieveAllPosts() {
        return null;
    }

    @Override
    public List<Post> retrievePostsOfUser() {
        return null;
    }

    @Override
    public EntityModel<Post> retrievePost(UUID id) {
        return null;
    }

    @Override
    public void deletePost(UUID id) {

    }

    @Override
    public ResponseEntity<Object> createPost(@Valid Post post) {
        return null;
    }
}
