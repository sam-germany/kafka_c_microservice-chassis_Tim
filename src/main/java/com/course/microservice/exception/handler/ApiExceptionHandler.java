package com.course.microservice.exception.handler;

import com.course.microservice.api.response.ErrorMessage;
import com.course.microservice.exception.BadInputRequestException22;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { BadInputRequestException22.class, NumberFormatException.class })
	public ResponseEntity<ErrorMessage> handleBadInputRequestException(Exception ex) {
		var message = ex.getClass().getSimpleName() + " : " + ex.getMessage();
		var response = new ErrorMessage(message, "Make sure your input value is correct---");

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = { IOException.class })
	public ResponseEntity<ErrorMessage> handleIoException(Exception ex) {
		var message = "Server error, cause " + ex.getClass().getSimpleName() + " : " + ex.getMessage();
		var response = new ErrorMessage(message, "Try again in a few minutes");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
/*
(1)
@RestControllerAdvice
@ExceptionHandler(value = { BadInputRequestException.class, NumberFormatException.class })
public ResponseEntity<ErrorMessage> handleBadInputRequestException(Exception ex) { ...}


-) @RestControllerAdvice       <-- if we use this annotation then any Exception that will not be caught by the method
                                  that throws exception then it will come here as we define here this annothation
-)@ExceptionHandler(value=..)  <-- as shown above we have defined only 2 Exception.class in the first method then only
                                 these 2 Exception will be caught by this method, if any other Exception will thrown
                                 by the application then that will be caught by the Spring itself,

-) @ExceptionHandler(value = { Exception.class })

if we define this  Exception.class as value  then any exception thrown by the application will be handled by this method
But if we define a seperate method  with that exact exception then that method with @ExceptionHandler will be called first


 */
