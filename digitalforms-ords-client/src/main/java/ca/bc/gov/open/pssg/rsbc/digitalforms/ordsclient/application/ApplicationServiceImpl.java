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
	public ApplicationResponse getApplication(Long noticeNo) {
		try {
			DigitalFormGetResponse response = this.applicationApi.digitalFormGuidGet(Long.toString(noticeNo));
			ApplicationInfo applicationInfo = new ApplicationInfo();
			applicationInfo.setElectronicAddressTxt(response.getElectronicAddressTxt());
			applicationInfo.setFaxNo(response.getFaxNo());
			applicationInfo.setFirstGivenNm(response.getFirstGivenNm());
			applicationInfo.setFormXml(response.getFormXml());
			applicationInfo.setManualEntryYn(response.getManualEntryYn());
			applicationInfo.setNoticeSubjectCd(response.getNoticeSubjectCd());
			applicationInfo.setNoticeTypeCd(response.getNoticeTypeCd());
			applicationInfo.setPhoneNo(response.getPhoneNo());
			applicationInfo.setPresentationFormatCd(response.getPresentationFormatCd());
			applicationInfo.setProhibitionNoticeNo(response.getProhibitionNoticeNo());
			applicationInfo.setReviewApplicationTypeCd(response.getReviewApplicationTypeCd());
			applicationInfo.setReviewRoleTypeCd(response.getReviewRoleTypeCd());
			applicationInfo.setSecondGivenNm(response.getSecondGivenNm());
			applicationInfo.setSurnameNm(response.getSurnameNm());
			applicationInfo.setUserId(response.getUserId());

			return ApplicationResponse.successResponseWithInfo(applicationInfo, response.getStatusCode(),
					response.getStatusMessage());
		} catch (ApiException ex) {
			logger.error("Application Service Get did throw exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public ApplicationResponse postApplication(ApplicationInfo applicationInfo) {
		try {
			DigitalFormPostRequest request = new DigitalFormPostRequest();
			
			request.setElectronicAddressTxt(applicationInfo.getElectronicAddressTxt());
			request.setFaxNo(applicationInfo.getFaxNo());
			request.setFirstGivenNm(applicationInfo.getFirstGivenNm());
			request.setFormXml(applicationInfo.getFormXml());
			request.setManualEntryYn(applicationInfo.getManualEntryYn());
			request.setNoticeSubjectCd(applicationInfo.getNoticeSubjectCd());
			request.setNoticeTypeCd(applicationInfo.getNoticeTypeCd());
			request.setPhoneNo(applicationInfo.getPhoneNo());
			request.setPresentationFormatCd(applicationInfo.getPresentationFormatCd());
			request.setProhibitionNoticeNo(applicationInfo.getProhibitionNoticeNo());
			request.setReviewApplicationTypeCd(applicationInfo.getReviewApplicationTypeCd());
			request.setReviewRoleTypeCd(applicationInfo.getReviewRoleTypeCd());
			request.setSecondGivenNm(applicationInfo.getSecondGivenNm());
			request.setSurnameNm(applicationInfo.getSurnameNm());
			request.setUserId(applicationInfo.getUserId());
			
			request.setApplicationReceivedDt(applicationInfo.getApplicationReceivedDt());
			request.setHearingDt(applicationInfo.getHearingDt());
			request.setHearingEndTm(applicationInfo.getHearingEndTm());
			request.setHearingStartTm(applicationInfo.getHearingStartTm());
			request.setReceiptNumberTxt(applicationInfo.getReceiptNumberTxt());
			
			DigitalFormCreateResponse response = this.applicationApi.digitalFormPost(request);

			return ApplicationResponse.successResponse(response.getFormObjectGuid(), response.getStatusCode(),
					response.getStatusMessage());
		} catch (ApiException ex) {
			logger.error("Application Service Post did throw exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public ApplicationResponse patchApplication(Long noticeNo, ApplicationInfo applicationInfo) {
		try {
			DigitalFormPatchRequest request = new DigitalFormPatchRequest();
			
			request.setElectronicAddressTxt(applicationInfo.getElectronicAddressTxt());
			request.setFaxNo(applicationInfo.getFaxNo());
			request.setFirstGivenNm(applicationInfo.getFirstGivenNm());
			request.setFormXml(applicationInfo.getFormXml());
			request.setManualEntryYn(applicationInfo.getManualEntryYn());
			request.setNoticeSubjectCd(applicationInfo.getNoticeSubjectCd());
			request.setNoticeTypeCd(applicationInfo.getNoticeTypeCd());
			request.setPhoneNo(applicationInfo.getPhoneNo());
			request.setPresentationFormatCd(applicationInfo.getPresentationFormatCd());
			request.setProhibitionNoticeNo(applicationInfo.getProhibitionNoticeNo());
			request.setReviewApplicationTypeCd(applicationInfo.getReviewApplicationTypeCd());
			request.setReviewRoleTypeCd(applicationInfo.getReviewRoleTypeCd());
			request.setSecondGivenNm(applicationInfo.getSecondGivenNm());
			request.setSurnameNm(applicationInfo.getSurnameNm());
			request.setUserId(applicationInfo.getUserId());
			
			request.setApplicationReceivedDt(applicationInfo.getApplicationReceivedDt());
			request.setHearingDt(applicationInfo.getHearingDt());
			request.setHearingEndTm(applicationInfo.getHearingEndTm());
			request.setHearingStartTm(applicationInfo.getHearingStartTm());
			request.setReceiptNumberTxt(applicationInfo.getReceiptNumberTxt());
			
			DigitalFormPatchResponse response = this.applicationApi.digitalFormGuidPatch(Long.toString(noticeNo), request);

			return ApplicationResponse.successResponse(response.getFormObjectGuid(), response.getStatusCode(),
					response.getStatusMessage());
		} catch (ApiException ex) {
			logger.error("Application Service Patch did throw exception: " + ex.getMessage(), ex);
			return ApplicationResponse.errorResponse(ex.getMessage());
		}
	}
}
