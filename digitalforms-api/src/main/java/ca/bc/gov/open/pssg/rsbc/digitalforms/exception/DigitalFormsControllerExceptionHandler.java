package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 * Global Exception handler for rest controllers
 * 
 * @author sivakaruna
 */
@ControllerAdvice
public class DigitalFormsControllerExceptionHandler {

	@ExceptionHandler(DigitalFormsException.class)
	public ResponseEntity<JSONResponse<String>> handleDigitalFormsException(DigitalFormsException e,
			WebRequest request) {
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(e.getMessage(), e.getHttpStatus().value()),
				e.getHttpStatus());
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<JSONResponse<String>> handleMissingPathVariableException(MissingPathVariableException e,
			WebRequest request) {
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.MISSING_PARAMS,
				HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<JSONResponse<String>> handleNoHandlerException(NoHandlerFoundException e,
			WebRequest request) {
		return new ResponseEntity<>(
				DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NO_HANDLER, HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<JSONResponse<String>> handleDefaultException(Exception e, WebRequest request) {
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.UNKNOWN_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
