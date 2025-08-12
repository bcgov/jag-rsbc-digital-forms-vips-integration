package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	private final Logger logger = LoggerFactory.getLogger(DigitalFormsControllerExceptionHandler.class);

	@ExceptionHandler(DigitalFormsException.class)
	public ResponseEntity<JSONResponse<String>> handleDigitalFormsException(DigitalFormsException e,
			WebRequest request) {
		logger.error("Digital Forms Exception occurred", e);
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(e.getMessage(), e.getHttpStatus().value()),
				e.getHttpStatus());
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<JSONResponse<String>> handleMissingPathVariableException(MissingPathVariableException e,
			WebRequest request) {
		logger.error("Missing Path Variable Exception occurred", e);
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.MISSING_PARAMS_ERROR,
				HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<JSONResponse<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
																				   WebRequest request) {
		logger.error("Missing Request Parameter Exception occurred", e);
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.MISSING_PARAMS_ERROR,
				HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<JSONResponse<String>> handleNoHandlerException(NoHandlerFoundException e,
			WebRequest request) {
		logger.error("No Handler Found Exception occurred", e);
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NO_HANDLER_ERROR,
				HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<JSONResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
			WebRequest request) {
		logger.error("Http Message Not Readable Exception occurred", e);
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(
				DigitalFormsConstants.MISSING_REQUEST_BODY_ERROR, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<JSONResponse<String>> handleDefaultException(Exception e, WebRequest request) {
		logger.error("Exception occurred", e);
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.UNKNOWN_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<JSONResponse<String>> handleValidationExceptions(MethodArgumentNotValidException e) {
		logger.error("Validation exception(s) occurred", e);
		
	    Map<String, String> errors = new HashMap<>();
	    e.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    StringBuffer buffer = new StringBuffer();
	    int c = 0; 
	    for (Entry<String, String> entry : errors.entrySet()) {
	    	if (c > 0 ) buffer.append(", ");
	        buffer.append((entry.getKey() + ": " + entry.getValue()));
	        c++;
	    };
		
		MDC.clear();
		return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(buffer.toString(),
				HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
	}

}
