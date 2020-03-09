package com.will.udemy.webapi.restfulwebservices.application.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Size(min=2 ,max = 255, message="Name should have atleast 2 characters")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthDate;

	protected User() {

	}

	public User(UUID id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public User(String name, Date birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/*@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}*/
}
