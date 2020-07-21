package ca.bc.gov.open.pssg.rsbc.digitalforms.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;


/**
 * 
 * Application Form Response Object Tests
 * 
 * @author sivakaruna
 *
 */
public class ApplicationFormResponseTests {

	@Test
	public void testInfoObj() {
		DigitalFormGetResponse response = new DigitalFormGetResponse();
		response.setFirstGivenNm("John");
		
		ApplicationInfoResponse applicationInfoResponse = new ApplicationInfoResponse(response);
		
		applicationInfoResponse.setNoticeTypeCd("noticeTypeCd");
		applicationInfoResponse.setProhibitionNoticeNo("prohibitionNoticeNo");
		applicationInfoResponse.setReviewApplnTypeCd("reviewApplnTypeCd");

		Assertions.assertEquals("noticeTypeCd", applicationInfoResponse.getNoticeTypeCd());
		Assertions.assertEquals("prohibitionNoticeNo", applicationInfoResponse.getProhibitionNoticeNo());
		Assertions.assertEquals("reviewApplnTypeCd", applicationInfoResponse.getReviewApplnTypeCd());
		Assertions.assertEquals("John", applicationInfoResponse.getFormData().getFirstGivenNm());
	}
	
	@Test
	public void testIdObj() {
		ApplicationIdResponse applicationIdResponse = new ApplicationIdResponse("applicationId", "createdTime", "updatedTime");

		Assertions.assertEquals("applicationId", applicationIdResponse.getApplicationId());
		Assertions.assertEquals("createdTime", applicationIdResponse.getCreatedTime());
		Assertions.assertEquals("updatedTime", applicationIdResponse.getUpdatedTime());
		
		applicationIdResponse.setApplicationId("id");
		applicationIdResponse.setCreatedTime("monday");
		applicationIdResponse.setUpdatedTime("sunday");
		
		Assertions.assertEquals("id", applicationIdResponse.getApplicationId());
		Assertions.assertEquals("monday", applicationIdResponse.getCreatedTime());
		Assertions.assertEquals("sunday", applicationIdResponse.getUpdatedTime());
	}
}
