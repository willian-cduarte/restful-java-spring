package com.will.udemy.microservices.service;

import com.will.udemy.microservices.web.models.request.UserRequest;
import com.will.udemy.microservices.web.models.response.UserResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.UUID;

@Service
public interface UserService {

    public ResponsePage<UserResponse> retrieveAllUsers(int page, int size);

    public EntityModel<UserResponse> retrieveUser(UUID id);

    public void deleteUser(UUID id);

    public ResponseEntity<Object> createUser(@Valid UserRequest user);

}
