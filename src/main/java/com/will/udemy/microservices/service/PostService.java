package com.will.udemy.microservices.service;

import com.will.udemy.microservices.web.models.request.PostRequest;
import com.will.udemy.microservices.web.models.response.PostResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.UUID;

@Service
public interface PostService {

    public ResponsePage<PostResponse> retrieveAllPosts(int page, int size);

    public ResponsePage<PostResponse> retrievePostsByUser(UUID id, int page, int size);

    public EntityModel<PostResponse> retrievePost(UUID id);

    public ResponseEntity<Object> publish(@Valid PostRequest post);
}
