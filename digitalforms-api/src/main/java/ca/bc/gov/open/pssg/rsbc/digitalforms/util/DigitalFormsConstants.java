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
	
	// DFAPI ISO 8601 time/date format regex
	public static final String DF_ISO8601_DATE_TIME_REGEX = "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|\\s([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$";
	
	// user id for ORDS request
	public static final String ORDS_USER_ID = "DigitalFormsApi";
	
	public enum FORM_TYPE {
		IRP, ADP, UL
	}
	
	public enum NOTICE_SUBJECT_CD {
		PERS
	}

	public enum PRESENTATION_TYPE_CD {
		ORAL, WRIT, CANCELLED, NONE
	}

	public enum REVIEW_ROLE_TYPE_CD {
		APPNT, LWYR, AUTHPERS
	}

	public enum MANUAL_ENTRY_CD {
		Y, N
	}
	
	// rest service error messages
	public static final String INVALID_ATTRIBUTE_ERROR = "%s is invalid";
	
	public static final String NOT_FOUND_ERROR = "Requested data not found";
	
	public static final String UNKNOWN_ERROR = "Unexpected error occured";
	
	public static final String NOT_PROCESSED_ERROR = "Request cannot be processed";
	
	public static final String UNAUTHORIZED_ERROR = "Unauthorized entry, please authenticate";
	
	public static final String NO_HANDLER_ERROR = "Request URL does not exist";
	
	public static final String MISSING_PARAMS_ERROR = "Missing request params";
	
	public static final String MISSING_REQUEST_BODY_ERROR = "Required data not found in the request body";
	
	public static final String PAYMENT_FORMAT_ERROR = "Invalid payment amount format";
	
	public static final String FORM_TYPE_TEXT = "Form type";
	
	public static final String NOTICE_SUBJECT_TEXT = "Notice subject code";
	
	public static final String PRESENTATION_TYPE_TEXT = "Presentation type code";
	
	public static final String REVIEW_ROLE_TYPE_TEXT = "Review role type code";
	
	public static final String MANUAL_ENTRY_TEXT = "Manual entry code";
	
	public static final String TIME_DATE_FORMAT_ERROR = "Invalid time date format";
	
	// rest response media type
	public static final String JSON_CONTENT = "application/json";
   
	// MDC constants
	public static final String REQUEST_CORRELATION_ID = "request.correlationid";
    public static final String REQUEST_ENDPOINT = "request.endpoint";
    public static final String REQUEST_FORMTYPE = "request.formtype";
}
