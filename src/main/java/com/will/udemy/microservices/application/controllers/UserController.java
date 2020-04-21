package com.will.udemy.microservices.application.controllers;

import com.will.udemy.microservices.service.UserService;
import com.will.udemy.microservices.web.interfaces.UserApi;
import com.will.udemy.microservices.web.models.request.UserRequest;
import com.will.udemy.microservices.web.models.response.UserResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponsePage<UserResponse> retrieveAllUsers(int page, int size) {
        return userService.retrieveAllUsers(page, size);
    }

    @Override
    public EntityModel<UserResponse> retrieveUser(UUID id) { return userService.retrieveUser(id); }

    @Override
    public void deleteUser(UUID id) { userService.deleteUser(id);}

    @Override
    public ResponseEntity<Object> createUser(UserRequest user) {
        return userService.createUser(user);
    }

}
