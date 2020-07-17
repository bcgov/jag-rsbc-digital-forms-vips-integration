package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 * Global Exception handler for rest controllers
 * 
 * @author sivakaruna
 */
@ControllerAdvice
public class DigitalFormsControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DigitalFormsException.class)
	public ResponseEntity<JSONResponse<String>> handleDigitalFormsException(DigitalFormsException e,
			WebRequest request) {
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(e.getMessage(), e.getHttpStatus().value()),
				e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<JSONResponse<String>> handleDefaultException(Exception e, WebRequest request) {
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.UNKNOWN_ERROR, 500),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
