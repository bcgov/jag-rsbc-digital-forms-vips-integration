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
	
	public static final int DIGITAL_FORMS_ORDS_SUCCESS_CD = 1;
	
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
   
}
