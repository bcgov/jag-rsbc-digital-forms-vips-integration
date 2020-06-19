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
	
	public String getIRPReviewForm(Long irpNoticeNumber, IRPReviewFormRequest formData);
	
	public String postIRPReviewForm(Long irpNoticeNumber, IRPReviewFormRequest formData);
	
	public String patchIRPReviewForm(Long irpNoticeNumber, Long id, IRPReviewFormRequest formData);

}




