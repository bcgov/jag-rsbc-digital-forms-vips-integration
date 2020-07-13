package ca.bc.gov.open.pssg.rsbc.digitalforms.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * 
 * Application Form Request Object Tests
 * 
 * @author sivakaruna
 *
 */
public class ApplicationFormDataTests {

	@Test
	public void testObj() {
		ApplicationFormData formRequest = new ApplicationFormData();

		formRequest.setFirstGivenNm("firstGivenNm");
		formRequest.setEmail("email");
		formRequest.setFaxNo("faxNo");
		formRequest.setManualEntryYN("manualEntryYN");
		formRequest.setNoticeSubjectCd("noticeSubjectCd");
		formRequest.setNoticeTypeCd("noticeTypeCd");
		formRequest.setPhoneNo("phoneNo");
		formRequest.setPresentationTypeCd("presentationTypeCd");
		formRequest.setReviewApplnTypeCd("reviewApplnTypeCd");
		formRequest.setReviewRoleTypeCd("reviewRoleTypeCd");
		formRequest.setSecondGivenNm("secondGivenNm");
		formRequest.setSurnameNm("surnameNm");

		formRequest.setFormData("formData");
		formRequest.setFormVersion("formVersion");

		Assertions.assertEquals("formData", formRequest.getFormData());
		Assertions.assertEquals("formVersion", formRequest.getFormVersion());

		Assertions.assertEquals("firstGivenNm", formRequest.getFirstGivenNm());
		Assertions.assertEquals("email", formRequest.getEmail());
		Assertions.assertEquals("faxNo", formRequest.getFaxNo());
		Assertions.assertEquals("manualEntryYN", formRequest.getManualEntryYN());
		Assertions.assertEquals("noticeSubjectCd", formRequest.getNoticeSubjectCd());
		Assertions.assertEquals("noticeTypeCd", formRequest.getNoticeTypeCd());
		Assertions.assertEquals("phoneNo", formRequest.getPhoneNo());
		Assertions.assertEquals("presentationTypeCd", formRequest.getPresentationTypeCd());
		Assertions.assertEquals("reviewApplnTypeCd", formRequest.getReviewApplnTypeCd());
		Assertions.assertEquals("reviewRoleTypeCd", formRequest.getReviewRoleTypeCd());
		Assertions.assertEquals("secondGivenNm", formRequest.getSecondGivenNm());
		Assertions.assertEquals("surnameNm", formRequest.getSurnameNm());
	}
}
