package ca.bc.gov.open.pssg.rsbc.digitalforms.util;


/**
 * 
 * Digital Forms Constants. 
 * 
 * @author shaunmillargov
 *
 */
public final class DigitalFormsConstants {
	
	private DigitalFormsConstants() {
	      //not called
	}
    
    public static final String JSON_RESPONSE_SUCCESS = "success";
    public static final String JSON_RESPONSE_FAIL = "fail";
    
    // common ORDS response codes. 
	public static final int ORDS_FAILURE_CD = -1;
	public static final int ORDS_SUCCESS_CD = 0;
	
	// specific ORDS response codes. 
	public static final int VIPS_ORDS_IRP_NOT_FOUND = 2;
	
	// user id for ORDS request
	public static final String ORDS_USER_ID = "DigitalFormsApi";
	
	public enum FORM_TYPE {
		IRP, ADP, UL
	}
	
	// rest service error messages
	public static final String INVALID_FORM_TYPE_ERROR = "Form type is invalid";
	
	public static final String NOT_FOUND = "Requested data not found";
	
	public static final String UNKNOWN_ERROR = "Unexpected error occured";
	
	public static final String NOT_PROCESSED = "Request cannot be processed";
	
	public static final String UNAUTHORIZED = "Unauthorized entry, please authenticate";
	
	public static final String NO_HANDLER = "Request URL does not exist";
	
	public static final String MISSING_PARAMS = "Missing request params";
	
	// rest response media type
	public static final String JSON_CONTENT = "application/json";
   
	// MDC constants
	public static final String REQUEST_CORRELATION_ID = "request.correlationid";
    public static final String REQUEST_ENDPOINT = "request.endpoint";
    public static final String REQUEST_FORMTYPE = "request.formtype";
}
