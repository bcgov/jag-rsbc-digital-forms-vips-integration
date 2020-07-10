package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewFormRequest;

/**
 * 
 * Form Submission Service Implementation 
 * 
 * @author sivakaruna
 *
 */
@Service
public class FormSubmissionServiceImpl implements FormSubmissionService {

	@Override
	public String getReviewForm(Long irpNoticeNumber) {
		// TODO Service to be built out here
		return "IRP review get";
	}

	@Override
	public String postReviewForm(Long irpNoticeNumber, ReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review post";
	}

	@Override
	public String patchReviewForm(Long noticeNumber, Long id, ReviewFormRequest formData) {
		// TODO Service to be built out here
		return "IRP review patch";
	}

}




