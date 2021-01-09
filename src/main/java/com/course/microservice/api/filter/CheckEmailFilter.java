package com.course.microservice.api.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckEmailFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	//	System.out.println("################");
		var httpRequest = (HttpServletRequest) request;
		var httpResponse = (HttpServletResponse) response;
		var developerEmail = httpRequest.getHeader("X-Developer-Email");

	//	System.out.println(developerEmail);          // developerEmail = abc@gmail.com

		if (StringUtils.isBlank(developerEmail) || !EmailValidator.getInstance().isValid(developerEmail)) {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

			PrintWriter writer = response.getWriter();
			writer.print("{\"error--\":\"Invalid email+++++\"}");

			return;
		}

		chain.doFilter(request, response);
	}
}
/*
print("{\"error--\":\"Invalid email+++++\"}")     <--  "\"  by using backslash we can seperate every  "{"key" ":" "value"}"



*/
