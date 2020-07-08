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
   
}
