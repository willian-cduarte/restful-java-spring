package com.will.udemy.microservices.web.interfaces;

import com.will.udemy.microservices.web.models.request.PostRequest;
import com.will.udemy.microservices.web.models.response.PostResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("posts")
public interface PostApi {

    @GetMapping("/all-posts")
    public ResponsePage<PostResponse> retrieveAllPosts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size );


    @GetMapping("/posts-by-user/{id}")
    public ResponsePage<PostResponse> retrievePostsOfUser(
            @PathVariable UUID id,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size );


    @GetMapping("/{id}")
    public EntityModel<PostResponse> retrievePost(@PathVariable UUID id);

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@RequestBody PostRequest post);

}
