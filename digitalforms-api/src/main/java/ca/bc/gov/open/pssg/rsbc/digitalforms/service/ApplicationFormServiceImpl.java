package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPostRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationService;

/**
 * 
 * Application form Service operations Implementation
 * 
 * @author sivakaruna
 *
 */
@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {

	@Autowired
	private ApplicationService applicationService;

	@Override
	public ApplicationResponse getApplicationForm(String formType, String formGuid) {
		return applicationService.getApplication(formGuid);
	}
	
	@Override
	public ApplicationResponse postApplicationForm(String formType, ApplicationFormData formData) {
		DigitalFormPostRequest request = new DigitalFormPostRequest();

		request.setElectronicAddressTxt(formData.getFields().get(0).getEmail());
		request.setFaxNo(formData.getFields().get(0).getFaxNo());
		request.setFirstGivenNm(formData.getFields().get(0).getFirstGivenNm());
		request.setFormXml(formData.getFormData());
		request.setManualEntryYn(formData.getFields().get(0).getManualEntryYN());
		request.setNoticeSubjectCd(formData.getFields().get(0).getNoticeSubjectCd());
		request.setNoticeTypeCd(formData.getFields().get(0).getNoticeTypeCd());
		request.setPhoneNo(formData.getFields().get(0).getPhoneNo());
		request.setPresentationFormatCd(formData.getFields().get(0).getPresentationTypeCd());
		request.setProhibitionNoticeNo(formData.getFields().get(0).getProhibitionNoticeNo());
		request.setReviewApplicationTypeCd(formData.getFields().get(0).getReviewApplnTypeCd());
		request.setReviewRoleTypeCd(formData.getFields().get(0).getReviewRoleTypeCd());
		request.setSecondGivenNm(formData.getFields().get(0).getSecondGivenNm());
		request.setSurnameNm(formData.getFields().get(0).getSurnameNm());
		request.setUserId("DigitalFormsApi");
		
		
		return applicationService.postApplication(request);
	}

	@Override
	public ApplicationResponse patchApplicationForm(String formType, String formGuid, ApplicationFormData formData) {
		DigitalFormPatchRequest request = new DigitalFormPatchRequest();

		request.setElectronicAddressTxt(formData.getFields().get(0).getEmail());
		request.setFaxNo(formData.getFields().get(0).getFaxNo());
		request.setFirstGivenNm(formData.getFields().get(0).getFirstGivenNm());
		request.setFormXml(formData.getFormData());
		request.setManualEntryYn(formData.getFields().get(0).getManualEntryYN());
		request.setNoticeSubjectCd(formData.getFields().get(0).getNoticeSubjectCd());
		request.setNoticeTypeCd(formData.getFields().get(0).getNoticeTypeCd());
		request.setPhoneNo(formData.getFields().get(0).getPhoneNo());
		request.setPresentationFormatCd(formData.getFields().get(0).getPresentationTypeCd());
		request.setProhibitionNoticeNo(formData.getFields().get(0).getProhibitionNoticeNo());
		request.setReviewApplicationTypeCd(formData.getFields().get(0).getReviewApplnTypeCd());
		request.setReviewRoleTypeCd(formData.getFields().get(0).getReviewRoleTypeCd());
		request.setSecondGivenNm(formData.getFields().get(0).getSecondGivenNm());
		request.setSurnameNm(formData.getFields().get(0).getSurnameNm());
		request.setUserId("DigitalFormsApi");
		
		
		return applicationService.patchApplication(formGuid, request);
	}
}
