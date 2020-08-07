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

	public ApplicationResponse getApplicationForm(String formGuid, String correlationId);

	public ApplicationResponse postApplicationForm(String formType, String noticeNo, String correlationId, ApplicationFormData formData);

	public ApplicationResponse patchApplicationForm(String formType, String formGuid, String correlationId, ApplicationFormData formData);

}
