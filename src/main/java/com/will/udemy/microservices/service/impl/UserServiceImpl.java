package com.will.udemy.microservices.service.impl;

import com.will.udemy.microservices.domain.models.User;
import com.will.udemy.microservices.domain.repositories.UserRepository;
import com.will.udemy.microservices.infrastructure.exception.RswNotFoundException;
import com.will.udemy.microservices.service.UserService;
import com.will.udemy.microservices.web.models.request.UserRequest;
import com.will.udemy.microservices.web.models.response.UserResponse;
import com.will.udemy.microservices.web.models.response.pagination.ResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponsePage<UserResponse> retrieveAllUsers(int page, int size) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        List<UserResponse> users = new java.util.ArrayList<>();
        userPage.getContent().forEach((User user) -> {
            users.add(new UserResponse(user));
        });
        return new ResponsePage(userPage, users);
    }

    @Override
    public EntityModel<UserResponse> retrieveUser(UUID id) {
        if (!userRepository.existsById(id))
            throw new RswNotFoundException("User by id " + id + " not found.");

        User user = userRepository.getOne(id);

        //HATEOAS
        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers

        return new EntityModel<>(new UserResponse(user))
                .add(linkTo(methodOn(this.getClass()).retrieveAllUsers(0, 10)).withRel("all-users"));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> createUser(UserRequest user) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(
                                userRepository.save(new User(user)).getId()
                        )
                        .toUri()
        ).build();
    }

}
