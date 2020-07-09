package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewFormRequest;

/**
 * 
 * Form Submission Service Interface 
 * 
 * @author sivakaruna
 *
 */
public interface FormSubmissionService {

	public String getReviewForm(Long noticeNumber);
	
	public String postReviewForm(Long noticeNumber, ReviewFormRequest formData);
	
	public String patchReviewForm(Long noticeNumber, Long id, ReviewFormRequest formData);

}




