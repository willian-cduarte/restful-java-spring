package com.will.udemy.webapi.restfulwebservices.application.controllers;

import com.will.udemy.webapi.restfulwebservices.application.domain.User;
import com.will.udemy.webapi.restfulwebservices.interfaces.web.controllers.UserWebController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController implements UserWebController {

    @Override
    public List<User> retrieveAllUsers() {
        return null;
    }

    @Override
    public EntityModel<User> retrieveUser(int id) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public ResponseEntity<Object> createUser(@Valid User user) {
        return null;
    }

}
