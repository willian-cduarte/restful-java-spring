package com.will.udemy.webapi.restfulwebservices.application.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.udemy.webapi.restfulwebservices.application.domain.User;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String title;

	private String description;

	private UUID userId;

	@CreatedDate
	private Date createdAt;

	protected Post() {

	}

	public Post(UUID id, String title, String description,  UUID userId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	private Date getCreatedAt() { return createdAt; }

	private void setDataCreate(Date createdAt) { this.createdAt = createdAt; }

	/*@Override
	public String toString() {
		return String.format(
				"Post [id=%s, title=%s description=%s, createdAt=%s, createdBy=%s]",
				id, title, description, createdAt, "user-id: " + userId);
	}*/
	
}
