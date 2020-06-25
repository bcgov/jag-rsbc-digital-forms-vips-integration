package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPStatusInfoResponse;

/**
 * 
 * IRP Query Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface IRPQueryService {

	public IRPStatusInfoResponse getIRP(Long id);
	
}




