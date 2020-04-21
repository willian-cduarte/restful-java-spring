package com.will.udemy.microservices.application.controllers;

import com.will.udemy.microservices.service.PostService;
import com.will.udemy.microservices.web.interfaces.PostApi;
import com.will.udemy.microservices.web.models.request.PostRequest;
import com.will.udemy.microservices.web.models.response.PostResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PostController implements PostApi {

    @Autowired
    private PostService postService;

    @Override
    public ResponsePage<PostResponse> retrieveAllPosts(int page, int size) {
        return postService.retrieveAllPosts(page, size);
    }

    @Override
    public ResponsePage<PostResponse> retrievePostsOfUser(UUID id, int page, int size) {
        return postService.retrievePostsByUser(id, page, size);
    }

    @Override
    public EntityModel<PostResponse> retrievePost(UUID id) {
        return postService.retrievePost(id);
    }

    @Override
    public ResponseEntity<Object> createPost(PostRequest post) {
        return postService.publish(post);
    }
}
