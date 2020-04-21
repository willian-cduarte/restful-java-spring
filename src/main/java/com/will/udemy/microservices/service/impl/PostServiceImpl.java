package com.will.udemy.microservices.service.impl;

import com.will.udemy.microservices.application.controllers.PostController;
import com.will.udemy.microservices.domain.models.Post;
import com.will.udemy.microservices.domain.repositories.PostRepository;
import com.will.udemy.microservices.domain.repositories.UserRepository;
import com.will.udemy.microservices.infrastructure.exception.RswNotFoundException;
import com.will.udemy.microservices.service.PostService;
import com.will.udemy.microservices.web.models.request.PostRequest;
import com.will.udemy.microservices.web.models.response.PostResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Override
    public ResponsePage<PostResponse> retrieveAllPosts(int page, int size) {

        Page<Post> postsPage = postRepository.findAll(PageRequest.of(page, size));

        List<PostResponse> posts = new ArrayList<>();

        postsPage.forEach((Post post) -> {
            posts.add(new PostResponse(post));
        });

        return new ResponsePage<PostResponse>(postsPage, posts);

    }

    @Override
    public ResponsePage<PostResponse> retrievePostsByUser(UUID id, int page, int size) {
        if (!userRepository.existsById(id))
            throw new RswNotFoundException("User by id " + id + " not found.");
        Page<Post> postsPage = postRepository.findAllByUserId(id, PageRequest.of(page, size));
        List<PostResponse> posts = new ArrayList<>();
        postsPage.forEach((Post post) -> {
            posts.add(new PostResponse(post));
        });
        return new ResponsePage<PostResponse>(postsPage, posts);
    }

    @Override
    public EntityModel<PostResponse> retrievePost(UUID id) {
        if (!postRepository.existsById(id))
            throw new RswNotFoundException("Post by id " + id + " not found.");
        //HATEOAS
        return new EntityModel<PostResponse>(new PostResponse(postRepository.getOne(id)))
                .add(WebMvcLinkBuilder.linkTo(methodOn(PostController.class).retrieveAllPosts(0, 10)).withRel("all-posts"));
    }

    @Override
    public ResponseEntity publish(PostRequest postRequest) {
        logger.info("publish");

        if (!userRepository.existsById(postRequest.getUserId())) {
            throw new RswNotFoundException("User by id " + postRequest.getUserId() + " not found.");
        }

        Post it = new Post(postRequest);
        Set<ConstraintViolation<Post>> violations = validator.validate(it);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed!", violations);
        }

        return ResponseEntity.created(

                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(
                                postRepository.save(it).getId())
                        .toUri()
        ).build();

    }

}
