package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewFormRequest.Field;

/**
 * 
 * IRP Review Form Request Object Tests
 * 
 * @author sivakaruna
 *
 */
public class ReviewFormRequestTests {

	@Test
	public void testObj() {
		ReviewFormRequest formRequest = new ReviewFormRequest();

		Field fields = new Field();
		fields.setFirstGivenNm("firstGivenNm");
		fields.setEmail("email");
		fields.setFaxNo("faxNo");
		fields.setManualEntryYN("manualEntryYN");
		fields.setNoticeSubjectCd("noticeSubjectCd");
		fields.setNoticeTypeCd("noticeTypeCd");
		fields.setPhoneNo("phoneNo");
		fields.setPresentationTypeCd("presentationTypeCd");
		fields.setProhibitionNoticeNo("prohibitionNoticeNo");
		fields.setReviewApplnTypeCd("reviewApplnTypeCd");
		fields.setReviewRoleTypeCd("reviewRoleTypeCd");
		fields.setSecondGivenNm("secondGivenNm");
		fields.setSurnameNm("surnameNm");

		List<Field> array = new ArrayList<Field>();
		array.add(fields);

		formRequest.setFormData("formData");
		formRequest.setFormType("formType");
		formRequest.setFormVersion("formVersion");
		formRequest.setFields(array);

		Assertions.assertEquals("formData", formRequest.getFormData());
		Assertions.assertEquals("formType", formRequest.getFormType());
		Assertions.assertEquals("formVersion", formRequest.getFormVersion());

		Assertions.assertEquals("firstGivenNm", formRequest.getFields().get(0).getFirstGivenNm());
		Assertions.assertEquals("email", formRequest.getFields().get(0).getEmail());
		Assertions.assertEquals("faxNo", formRequest.getFields().get(0).getFaxNo());
		Assertions.assertEquals("manualEntryYN", formRequest.getFields().get(0).getManualEntryYN());
		Assertions.assertEquals("noticeSubjectCd", formRequest.getFields().get(0).getNoticeSubjectCd());
		Assertions.assertEquals("noticeTypeCd", formRequest.getFields().get(0).getNoticeTypeCd());
		Assertions.assertEquals("phoneNo", formRequest.getFields().get(0).getPhoneNo());
		Assertions.assertEquals("presentationTypeCd", formRequest.getFields().get(0).getPresentationTypeCd());
		Assertions.assertEquals("prohibitionNoticeNo", formRequest.getFields().get(0).getProhibitionNoticeNo());
		Assertions.assertEquals("reviewApplnTypeCd", formRequest.getFields().get(0).getReviewApplnTypeCd());
		Assertions.assertEquals("reviewRoleTypeCd", formRequest.getFields().get(0).getReviewRoleTypeCd());
		Assertions.assertEquals("secondGivenNm", formRequest.getFields().get(0).getSecondGivenNm());
		Assertions.assertEquals("surnameNm", formRequest.getFields().get(0).getSurnameNm());
	}
}
