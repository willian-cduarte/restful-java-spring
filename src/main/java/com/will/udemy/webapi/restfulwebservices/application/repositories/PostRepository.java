package com.will.udemy.webapi.restfulwebservices.application.repositories;

import com.will.udemy.webapi.restfulwebservices.application.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{

}
