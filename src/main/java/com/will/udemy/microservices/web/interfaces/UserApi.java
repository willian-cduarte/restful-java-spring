package com.will.udemy.microservices.web.interfaces;

import com.will.udemy.microservices.web.models.request.UserRequest;
import com.will.udemy.microservices.web.models.response.UserResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("users")
public interface UserApi {

    @GetMapping("/all-users")
    public ResponsePage<UserResponse> retrieveAllUsers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size );

    @GetMapping("/{id}")
    public EntityModel<UserResponse> retrieveUser(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id);

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserRequest user);

}
