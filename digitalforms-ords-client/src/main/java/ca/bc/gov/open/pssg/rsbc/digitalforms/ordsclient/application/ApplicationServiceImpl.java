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
	public ApplicationResponse getApplication(String formGuid) {
		try {
			DigitalFormGetResponse response = this.applicationApi.digitalFormGuidGet(formGuid);

			ApplicationInfo applicationInfo = new ApplicationInfo();
			applicationInfo.setEmail(response.getElectronicAddressTxt());
			applicationInfo.setFaxNo(response.getFaxNo());
			applicationInfo.setFirstGivenNm(response.getFirstGivenNm());
			applicationInfo.setManualEntryYN(response.getManualEntryYn());
			applicationInfo.setNoticeSubjectCd(response.getNoticeSubjectCd());
			applicationInfo.setNoticeTypeCd(response.getNoticeTypeCd());
			applicationInfo.setPhoneNo(response.getPhoneNo());
			applicationInfo.setPresentationTypeCd(response.getPresentationFormatCd());
			applicationInfo.setProhibitionNoticeNo(response.getProhibitionNoticeNo());
			applicationInfo.setReviewApplnTypeCd(response.getReviewApplicationTypeCd());
			applicationInfo.setReviewRoleTypeCd(response.getReviewRoleTypeCd());
			applicationInfo.setSecondGivenNm(response.getSecondGivenNm());
			applicationInfo.setSurnameNm(response.getSurnameNm());

			return ApplicationResponse.successResponseGet(applicationInfo, response.getStatusCode(),
					response.getStatusMessage(), response.getFormXml());
		} catch (ApiException ex) {
			logger.error("Application Service Get did throw exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public ApplicationResponse patchApplication(String formGuid, DigitalFormPatchRequest request) {
		try {

			DigitalFormPatchResponse response = this.applicationApi.digitalFormGuidPatch(formGuid, request);
			return ApplicationResponse.successResponsePatch(response.getFormObjectGuid(), response.getStatusCode(),
					response.getStatusMessage(), response.getUpdDtm());
		} catch (ApiException ex) {
			logger.error("Application Service Patch did throw exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public ApplicationResponse postApplication(DigitalFormPostRequest request) {
		try {

			DigitalFormCreateResponse response = this.applicationApi.digitalFormPost(request);
			return ApplicationResponse.successResponsePost(response.getFormObjectGuid(), response.getStatusCode(),
					response.getStatusMessage(), response.getEntDtm());
		} catch (ApiException ex) {
			logger.error("Application Service Patch did throw exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}
}
