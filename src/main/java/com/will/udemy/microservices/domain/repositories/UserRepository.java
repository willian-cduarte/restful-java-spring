package com.will.udemy.microservices.domain.repositories;

import com.will.udemy.microservices.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface UserRepository extends JpaRepository<User, UUID>{


}
