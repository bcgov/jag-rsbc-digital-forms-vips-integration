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

	public String getIRPReviewForm(Long irpNoticeNumber);
	
	public String postIRPReviewForm(Long irpNoticeNumber, IRPReviewFormRequest formData);
	
	public String patchIRPReviewForm(Long irpNoticeNumber, Long id, IRPReviewFormRequest formData);

}




