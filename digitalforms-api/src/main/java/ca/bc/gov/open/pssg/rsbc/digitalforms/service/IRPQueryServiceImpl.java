package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPReviewFormRequest;

/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class IRPQueryServiceImpl implements IRPQueryService {

	@Override
	public String getIRP(Long id) {
		
		// TODO Service to be built out here
		return "IRP result"; 
	}

	@Override
	public String getIRPReview(Long irpNoticeNumber, IRPReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review get";
	}

	@Override
	public String postIRPReview(Long irpNoticeNumber, IRPReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review post";
	}

	@Override
	public String patchIRPReview(Long irpNoticeNumber, Long id, IRPReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review patch";
	}

}




