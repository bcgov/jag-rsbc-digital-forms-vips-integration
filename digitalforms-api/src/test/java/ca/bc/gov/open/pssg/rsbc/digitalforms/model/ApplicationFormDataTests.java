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
		formRequest.setPhoneNo("phoneNo");
		formRequest.setPresentationTypeCd("presentationTypeCd");
		formRequest.setReviewRoleTypeCd("reviewRoleTypeCd");
		formRequest.setSecondGivenNm("secondGivenNm");
		formRequest.setSurnameNm("surnameNm");

		formRequest.setFormData("formData");

		Assertions.assertEquals("formData", formRequest.getFormData());

		Assertions.assertEquals("firstGivenNm", formRequest.getFirstGivenNm());
		Assertions.assertEquals("email", formRequest.getEmail());
		Assertions.assertEquals("faxNo", formRequest.getFaxNo());
		Assertions.assertEquals("manualEntryYN", formRequest.getManualEntryYN());
		Assertions.assertEquals("noticeSubjectCd", formRequest.getNoticeSubjectCd());
		Assertions.assertEquals("phoneNo", formRequest.getPhoneNo());
		Assertions.assertEquals("presentationTypeCd", formRequest.getPresentationTypeCd());
		Assertions.assertEquals("reviewRoleTypeCd", formRequest.getReviewRoleTypeCd());
		Assertions.assertEquals("secondGivenNm", formRequest.getSecondGivenNm());
		Assertions.assertEquals("surnameNm", formRequest.getSurnameNm());
	}
}
