package com.stg.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.stg.entity.ResponseError;
import com.stg.exception.UserException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value =  UserException.class )
	ResponseEntity<ResponseError> userError(UserException userException, HttpServletRequest httpServletRequest) {
		ResponseError responseError = new ResponseError(userException.getMessage(), httpServletRequest.getRequestURI());
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { TicketException.class })
	public ResponseEntity<ResponseError> ticketError(TicketException ticketException,
			HttpServletRequest httpServletRequest) {
		ResponseError responseError = new ResponseError(ticketException.getMessage(),
				httpServletRequest.getRequestURI());
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}

}
