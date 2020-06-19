package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPReviewFormRequest;

/**
 * 
 * IRP Query Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface IRPQueryService {

	public String getIRP(Long id);
	
	public String getIRPReview(Long irpNoticeNumber, IRPReviewFormRequest formData);
	
	public String postIRPReview(Long irpNoticeNumber, IRPReviewFormRequest formData);
	
	public String patchIRPReview(Long irpNoticeNumber, Long id, IRPReviewFormRequest formData);

}




