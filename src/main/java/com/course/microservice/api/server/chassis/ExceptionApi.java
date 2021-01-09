package com.course.microservice.api.server.chassis;

import com.course.microservice.api.response.PlainMessage;
import com.course.microservice.exception.BadInputRequestException22;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;

@RestController
@RequestMapping("/api/chassis/exception")
@Tag(name = "Exception API", description = "Microservice chassis sample exception API.")
public class ExceptionApi {

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);


	@GetMapping(value = "/global", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Sample exception API", description = "<p>This sample exception API will throw exception if the input parameter is invalid.</p>")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Show parameter values-"),

	@ApiResponse(responseCode = "400", description = "If <code>param_one</code> or <code>param_two</code> is invalid.", content = {
	 	@Content(examples = { @ExampleObject(value = "{\r\n" + "\" message \":\"string\",\r\n" + "  \"recommendation------\": \"string\"\r\n" + "}") }, mediaType = "application/json") }),

	@ApiResponse(responseCode = "500", description = "If <code>use_file</code> is <code>true</code>.", content = {
		@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message\": \"string\",\r\n" + "    \"recommendation......\": \"string\"\r\n" + "}") }, mediaType = "application/json") })
	 })
	public ResponseEntity<PlainMessage> demoExceptionGlobal(
		@RequestParam(name = "param_one") @Parameter(description = "Must be numeric.", example = "1526", required = false) String paramOne22,
		@RequestParam(name = "param_two") @Parameter(description = "Maximum length is 5 characters.", example = "A5b9c", required = false) String paramTwo22,
		@RequestParam(name = "use_file", defaultValue = "false") @Parameter(description = "If <code>true</code> will throw exception (simulating access to non exist file).") boolean useFile22)
	                                                                                                                                                    		throws Exception {

		// will throw NumberFormatException if string contains non-numeric
		int number = Integer.parseInt(paramOne22);

		// max length is 5 chars
		if (paramTwo22.length() > 5) {
			LOG.warn("Wrong input on param_two");
			throw new BadInputRequestException22("param_two is too long");
		}

		// checked exception
		if (useFile22) {
			var file = new FileReader("a-not-exists-file.txt");
			LOG.info("File is {}", file);
		}

		// build response (no exception thrown)
		var message = String.join(", ", "param_one (converted to number) : " + number, "param_two : " + paramTwo22);
		var response = new PlainMessage(message);

		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Sample exception API", description = "<p>This sample exception API will throw exception if the input parameter is invalid.</p>")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Show parameter values"),

 	@ApiResponse(responseCode = "400", description = "If <code>param_one</code> is invalid----.", content = {
		@Content(examples = { @ExampleObject(value = "{\r\n" + "    \"message------\": \"string\",\r\n  \"recommendation-----\": \"string\"\r\n" + "}") }, mediaType = "application/json") })
	})
	public ResponseEntity<PlainMessage> demoExceptionResponse( @RequestParam(name = "param_one")
																   @Parameter(description = "Valid when value is started with <code>microservice</code>######.",
			                                                                              example = "microserviceXYZ", required = true) String paramOne) {

		// need specific prefix
		if (!paramOne.startsWith("microservice")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param_one must be started with 'microservice'");
		}

		var response = new PlainMessage("param_one : " + paramOne);

		return ResponseEntity.ok().body(response);
	}
}
/*
(1)
@ApiResponse(responseCode = "200",
@ApiResponse(responseCode = "400",   <-- these both responses we have defined only for documentation, even we have defined only 200,400  this response can send also other
                                         exceptions if we create situation that throw other exceptions, main point this  200,400 is only for documentaion



 */
