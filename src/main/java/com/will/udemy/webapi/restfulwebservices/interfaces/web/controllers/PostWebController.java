package com.will.udemy.webapi.restfulwebservices.interfaces.web.controllers;

import com.will.udemy.webapi.restfulwebservices.application.domain.Post;
import com.will.udemy.webapi.restfulwebservices.interfaces.web.models.response.PaginationResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("posts")
public interface PostWebController {

    @GetMapping("/all-posts")
    public PaginationResponse<Post> retrieveAllPosts();

    @GetMapping("/by-user/{id}")
    public List<Post> retrievePostsOfUser();

    @GetMapping("/{id}")
    public EntityModel<Post> retrievePost(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable UUID id);

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody Post post);

}
