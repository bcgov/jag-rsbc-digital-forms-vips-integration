package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPReviewFormRequest;

/**
 * 
 * IRP Review Service Interface 
 * 
 * @author sivakaruna
 *
 */
public interface IRPReviewService {

	public String getIRPReviewForm(String id);
	
	public String postIRPReviewForm(IRPReviewFormRequest formData);
	
	public String patchIRPReviewForm(String id, IRPReviewFormRequest formData);

}




