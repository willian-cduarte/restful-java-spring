package com.will.udemy.microservices.domain.repositories;

import com.will.udemy.microservices.domain.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface PostRepository extends JpaRepository<Post, UUID>{

    Page<Post> findAllByUserId(UUID userId, Pageable pageable);
}
