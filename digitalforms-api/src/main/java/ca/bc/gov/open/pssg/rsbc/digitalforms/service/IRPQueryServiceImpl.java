package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPStatusInfoResponse;

/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class IRPQueryServiceImpl implements IRPQueryService {

	@Override
	public IRPStatusInfoResponse getIRP(Long id) {
		
		// TODO Service to be built out here
		return new IRPStatusInfoResponse(new IRPInfo( "01/02/2020", "0123456", "Rothschild:", "Decided", "N")); 
	}
	
}




