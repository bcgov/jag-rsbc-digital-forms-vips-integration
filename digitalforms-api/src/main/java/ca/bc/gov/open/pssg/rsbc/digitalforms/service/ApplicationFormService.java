package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormDataPatch;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormDataPost;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;

/**
 * 
 * Application form Service operations Interface
 * 
 * @author sivakaruna
 *
 */
public interface ApplicationFormService {

	public ApplicationResponse getApplicationForm(String applicationId, String correlationId);

	public ApplicationResponse postApplicationForm(String formType, String noticeNo, String correlationId, ApplicationFormDataPost formData);

	public ApplicationResponse patchApplicationForm(String formType, String applicationId, String correlationId, ApplicationFormDataPatch formData);

}
