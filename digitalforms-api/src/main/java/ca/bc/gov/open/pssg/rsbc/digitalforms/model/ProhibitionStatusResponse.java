package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

/**
 * 
 * @author shaunmillargov
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status" })
public class ProhibitionStatusResponse {

	@JsonProperty("status")
	private Status status;

	public ProhibitionStatusResponse(VipsProhibitionStatusResponse ordsResp) {

		// TODO - DF-918: remove commented out code once complete. 
// flagged for deletion		
//		if ( ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD ) {
//			status = new Status();
//			status.setNoticeServedDt(ordsResp.getStatus().getNoticeServedDt());
//			status.setNoticeTypeCd(ordsResp.getStatus().getNoticeTypeCd());
//			status.setOriginalCause(ordsResp.getStatus().getOriginalCause());
//			status.setReceiptNumberTxt(ordsResp.getStatus().getReceiptNumberTxt());
//			status.setReviewCreatedYn(ordsResp.getStatus().getReviewCreatedYn());
//			status.setReviewEndDtm(ordsResp.getStatus().getReviewEndDtm());
//			status.setReviewFormSubmittedYn(ordsResp.getStatus().getReviewFormSubmittedYn());
//			status.setReviewStartDtm(ordsResp.getStatus().getReviewStartDtm());
//			status.setSurnameNm(ordsResp.getStatus().getSurnameNm());
//			status.setDisclosure(ordsResp.getStatus().getDisclosure());
//			status.setDriverLicenceSeizedYn(ordsResp.getStatus().getDriverLicenceSeized());
//			status.setApplicationId(ordsResp.getStatus().getApplicationId());
//		}
		
		if ( ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD ) {
			
			// TODO - DF-918: complete this once ORDS has been updates to supply the new review blocks
			
//			status = new Status();
//			status.setNoticeServedDt(ordsResp.getStatus().getNoticeServedDt());
//			status.setNoticeTypeCd(ordsResp.getStatus().getNoticeTypeCd());
//			status.setOriginalCause(ordsResp.getStatus().getOriginalCause());
//			status.setReviewCreatedYn(ordsResp.getStatus().getReviewCreatedYn());
//			status.setReviewFormSubmittedYn(ordsResp.getStatus().getReviewFormSubmittedYn());
//			status.setSurnameNm(ordsResp.getStatus().getSurnameNm());
//			status.setDisclosure(ordsResp.getStatus().getDisclosure());
//			status.setDriverLicenceSeizedYn(ordsResp.getStatus().getDriverLicenceSeized());
			
			// DF-918 iterate review blocks from ORDS into response object. 
			// NOTE: These should be in descending order of date.  
			
			status = new Status();
			status.setNoticeServedDt(ordsResp.getStatus().getNoticeServedDt());
			status.setNoticeTypeCd("UL");
			status.setOriginalCause("IRP3");
			status.setReviewCreatedYn("Y");
			status.setReviewFormSubmittedYn("Y");
			status.setSurnameNm("Gordon");
			status.setDisclosure(null);
			status.setDriverLicenceSeizedYn("N");
			
			ReviewInfo  revInfo1 = new ReviewInfo();
			revInfo1.setApplicationId("bb71037c-f87b-0444-e054-00144ff95452");
			revInfo1.setStatus("in_progress");
			revInfo1.setReviewStartDtm("2021-03-10 09:30:00 -07:00");
			revInfo1.setReviewEndDtm("2021-03-10 10:00:00 -07:00");
			revInfo1.setReceiptNumberTxt("1234");
			revInfo1.setReviewId("5676767");
			
			ReviewInfo  revInfo2 = new ReviewInfo();
			revInfo2.setApplicationId("bb71037c-f87b-0444-e054-00144ff95453");
			revInfo2.setStatus("complete_failed");
			revInfo2.setReviewStartDtm("2020-11-10 09:30:00 -07:00");
			revInfo2.setReviewEndDtm("2020-11-10 10:00:00 -07:00");
			revInfo2.setReceiptNumberTxt("678");
			revInfo2.setReviewId("5425255");
			
			status.getReviews().add(revInfo1);
			status.getReviews().add(revInfo2);
			
		}
	}

	@JsonProperty("status")
	public Status getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Status status) {
		this.status = status;
	}

}
