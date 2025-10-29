package ca.bc.gov.open.pssg.rsbc.digitalforms.service;


import java.math.BigDecimal;

import ca.bc.gov.open.jagvipsclient.validation.VipsValidExpiryDateResponse;
import ca.bc.gov.open.jagvipsclient.validation.VipsValidTimeframeResponse;

/** 
 * 
 * Collection of services for accessing VIPS validations.
 * 
 * @author jdosil
 *
 */
public interface ValidationService {

	public VipsValidTimeframeResponse withinTimeFrame(String startDate, BigDecimal intervalDays);
	public VipsValidExpiryDateResponse validateExpiryDate(String startDate, BigDecimal intervalDays);
	
}




