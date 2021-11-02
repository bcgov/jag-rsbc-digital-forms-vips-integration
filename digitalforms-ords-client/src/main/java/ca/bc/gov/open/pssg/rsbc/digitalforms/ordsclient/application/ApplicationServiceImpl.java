package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.ApplicationApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormCreateResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPatchResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPostRequest;

/**
 * Application Service Implementation using ORDS services.
 *
 * @author sivakaruna
 */
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationApi applicationApi;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ApplicationServiceImpl(ApplicationApi applicationApi) {
		this.applicationApi = applicationApi;
	}

	@Override
	public ApplicationResponse getApplication(String formGuid, String correlationId) {
		try {
			DigitalFormGetResponse response = this.applicationApi.digitalFormGuidGet(formGuid);

			logger.info("Processed Get Application request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return ApplicationResponse.successResponseGet(response, response.getStatusCode(),
					response.getStatusMessage());
		} catch (ApiException ex) {
			logger.error("Application Service Get threw an exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public ApplicationResponse patchApplication(String formGuid, DigitalFormPatchRequest request,
			String correlationId) {
		try {
			DigitalFormPatchResponse response = this.applicationApi.digitalFormGuidPatch(formGuid, request);

			logger.info("Processed Patch Application request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return ApplicationResponse.successResponsePatch(response.getFormObjectGuid(), response.getStatusCode(),
					response.getStatusMessage(), response.getUpdDtm());
		} catch (ApiException ex) {
			logger.error("Application Service Patch threw an exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public ApplicationResponse postApplication(DigitalFormPostRequest request, String correlationId) {
		try {
			DigitalFormCreateResponse response = this.applicationApi.digitalFormPost(request);

			logger.info("Processed Post Application request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return ApplicationResponse.successResponsePost(response.getFormObjectGuid(), response.getStatusCode(),
					response.getStatusMessage(), response.getEntDtm());
		} catch (ApiException ex) {
			logger.error("Application Service Post threw an exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}
}
