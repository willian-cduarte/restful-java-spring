package com.will.udemy.webapi.restfulwebservices.application.repositories;

import com.will.udemy.webapi.restfulwebservices.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
