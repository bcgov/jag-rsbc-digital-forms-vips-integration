package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionService;
import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;

/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class QueryServiceImpl implements QueryService {
	
	@Autowired
	private ProhibitionService prohibService;

	@Override
	public VipsProhibitionStatusResponse getProhibitionInfo(Long id) {
		
		return prohibService.getVipsProhibitionStatus(id);
		
	}
	
}




