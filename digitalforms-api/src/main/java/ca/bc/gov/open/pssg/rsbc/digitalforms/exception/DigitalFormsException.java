package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom Exception for Digital forms API
 * 
 * @author sivakaruna
 */
public class DigitalFormsException extends Exception {

	private static final long serialVersionUID = 5873442413088571528L;

	private final HttpStatus httpStatus;

	public DigitalFormsException(String message, HttpStatus status) {
		super(message);
		this.httpStatus = status;
	}

	public DigitalFormsException(String message, HttpStatus status, Throwable cause) {
		super(message, cause);
		this.httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
