package com.will.udemy.microservices.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
	private String timestamp;
	private int status;
	private String error;
	private String message;
	private Map<String, ?> details;
}
