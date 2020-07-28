package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;

/**
 * 
 * Application form Service operations Interface
 * 
 * @author sivakaruna
 *
 */
public interface ApplicationFormService {

	public ApplicationResponse getApplicationForm(String formGuid);

	public ApplicationResponse postApplicationForm(String formType, String noticeNo, ApplicationFormData formData);

	public ApplicationResponse patchApplicationForm(String formType, String formGuid, ApplicationFormData formData);

}
