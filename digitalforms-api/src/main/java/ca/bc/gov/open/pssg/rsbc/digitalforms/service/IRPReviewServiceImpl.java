package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPReviewFormRequest;

/**
 * 
 * IRP Review Service Implementation 
 * 
 * @author sivakaruna
 *
 */
@Service
public class IRPReviewServiceImpl implements IRPReviewService {

	@Override
	public String getIRPReviewForm(String id) {
		// TODO Service to be built out here
		return "IRP review get";
	}

	@Override
	public String postIRPReviewForm(IRPReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review post";
	}

	@Override
	public String patchIRPReviewForm(String id, IRPReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review patch";
	}

}




