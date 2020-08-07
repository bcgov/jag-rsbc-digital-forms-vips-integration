package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;

/**
 * 
 * Query Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface QueryService {

	public VipsProhibitionStatusResponse getProhibitionStatus(String noticeNumber, String correlationId);
	
}




