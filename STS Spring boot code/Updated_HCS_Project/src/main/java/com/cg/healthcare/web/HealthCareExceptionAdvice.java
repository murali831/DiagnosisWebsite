package com.cg.healthcare.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.DiagnosisTestException;
import com.cg.healthcare.exceptions.ErrorInfo;
import com.cg.healthcare.exceptions.LoginException;
import com.cg.healthcare.exceptions.SearchTestException;
import com.cg.healthcare.exceptions.SlotException;

@RestControllerAdvice
public class HealthCareExceptionAdvice {

	Logger logger = LoggerFactory.getLogger(HealthCareExceptionAdvice.class);
	
	@ExceptionHandler({DiagnosisException.class, AppointmentException.class, SlotException.class,DiagnosisTestException.class})
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorInfo handleException(Exception ex) {
		logger.error(ex.getMessage());
		return new ErrorInfo(ex.getMessage());
	}
	
	@ExceptionHandler({SearchTestException.class})
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public ErrorInfo handleException2(Exception ex) {
		logger.error(ex.getMessage());
		return new ErrorInfo(ex.getMessage());
		
	}
	
	@ExceptionHandler({LoginException.class})
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public ErrorInfo handleException3(Exception ex) {
		logger.error(ex.getMessage());
		return new ErrorInfo(ex.getMessage());
		
	}
}
