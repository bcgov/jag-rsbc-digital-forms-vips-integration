package ca.bc.gov.open.pssg.rsbc.digitalforms.service;


import ca.bc.gov.open.jagvipsclient.validation.VipsValidTimeframeResponse;

import java.math.BigDecimal;

/**
 * 
 * Query Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface ValidationService {

	public VipsValidTimeframeResponse withinTimeFrame(String startDate, BigDecimal intervalDays);
	
}




