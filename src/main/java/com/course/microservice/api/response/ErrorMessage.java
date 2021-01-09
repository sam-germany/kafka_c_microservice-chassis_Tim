package com.course.microservice.api.response;

public class ErrorMessage {

	private String message;

	private String recommendation;

	public ErrorMessage() {

	}

	public ErrorMessage(String message, String recommendation) {
		super();
		this.message = message;
		this.recommendation = recommendation;
	}

	public String getMessage() {
		return message;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
}
