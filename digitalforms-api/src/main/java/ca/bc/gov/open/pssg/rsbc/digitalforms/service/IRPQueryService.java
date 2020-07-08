package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;

/**
 * 
 * IRP Query Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface IRPQueryService {

	public VipsProhibitionStatusResponse getIRP(Long id);
	
}




