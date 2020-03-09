package com.will.udemy.webapi.restfulwebservices.interfaces.web.controllers;

import com.will.udemy.webapi.restfulwebservices.application.domain.User;
import com.will.udemy.webapi.restfulwebservices.infrastructure.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequestMapping("users")
public interface UserWebController {

    @GetMapping("/all-users")
    public List<User> retrieveAllUsers();

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id);

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id);

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user);
}
