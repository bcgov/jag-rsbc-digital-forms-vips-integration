package ca.bc.gov.open.pssg.rsbc.digitalforms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationInfo;
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
		return applicationService.postApplication(convertFormData(formData));
	}

	@Override
	public ApplicationResponse patchApplicationForm(String formType, String formGuid, ApplicationFormData formData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ApplicationInfo convertFormData(ApplicationFormData formData) {
		ApplicationInfo applicationInfo = new ApplicationInfo();
		applicationInfo.setApplicationReceivedDt(formData.getFields().get(0).getEmail());
		applicationInfo.setElectronicAddressTxt(formData.getFields().get(0).getEmail());
		applicationInfo.setFaxNo(formData.getFields().get(0).getFaxNo());
		applicationInfo.setFirstGivenNm(formData.getFields().get(0).getFirstGivenNm());
		applicationInfo.setFormXml(formData.getFormData());
		applicationInfo.setManualEntryYn(formData.getFields().get(0).getManualEntryYN());
		applicationInfo.setNoticeSubjectCd(formData.getFields().get(0).getNoticeSubjectCd());
		applicationInfo.setNoticeTypeCd(formData.getFields().get(0).getNoticeTypeCd());
		applicationInfo.setPhoneNo(formData.getFields().get(0).getPhoneNo());
		applicationInfo.setPresentationFormatCd(formData.getFields().get(0).getPresentationTypeCd());
		applicationInfo.setProhibitionNoticeNo(formData.getFields().get(0).getProhibitionNoticeNo());
		applicationInfo.setReviewApplicationTypeCd(formData.getFields().get(0).getReviewApplnTypeCd());
		applicationInfo.setReviewRoleTypeCd(formData.getFields().get(0).getReviewRoleTypeCd());
		applicationInfo.setSecondGivenNm(formData.getFields().get(0).getSecondGivenNm());
		applicationInfo.setSurnameNm(formData.getFields().get(0).getSurnameNm());
		
		return applicationInfo;
	}

}




