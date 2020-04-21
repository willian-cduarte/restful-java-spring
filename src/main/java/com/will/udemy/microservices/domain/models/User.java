package com.will.udemy.microservices.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.will.udemy.microservices.infrastructure.formatter.DatePatternFormatting;
import com.will.udemy.microservices.web.models.request.UserRequest;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@ApiModel(description="All details about the user.")
@Entity
@Data
@AllArgsConstructor
@Table(name = "USERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private Date birthDate;

	private Date createdAt;

	protected User() { }

	public User(UUID id, String name, Date birthDate, String timestamp) throws ParseException {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.createdAt = new DatePatternFormatting().stringTimestampToDate(timestamp);
	}

	public User(String name, Date birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.createdAt =  new Date();
	}

	public User(@Valid UserRequest request) {
		super();
		this.name = request.getName();
		this.birthDate = request.getBirthDate();
		this.createdAt = new Date();
	}


}
