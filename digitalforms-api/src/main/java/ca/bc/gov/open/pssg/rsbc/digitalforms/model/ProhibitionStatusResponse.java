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
		
		if ( ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD ) {
			
			status = new Status();
			status.setNoticeServedDt(ordsResp.getStatus().getNoticeServedDt());
			status.setNoticeTypeCd(ordsResp.getStatus().getNoticeTypeCd());
			status.setOriginalCause(ordsResp.getStatus().getOriginalCause());
			status.setReviewCreatedYn(ordsResp.getStatus().getReviewCreatedYn());
			status.setReviewFormSubmittedYn(ordsResp.getStatus().getReviewFormSubmittedYn());
			status.setSurnameNm(ordsResp.getStatus().getSurnameNm());
			status.setDriverLicenceSeizedYn(ordsResp.getStatus().getDriverLicenceSeized());
			
			for ( int i = 0; i < ordsResp.getStatus().getDisclosure().size(); i++ ) {
			    status.getDisclosure().add(
			    		new DocumentDisclosureInfo(
			    				ordsResp.getStatus().getDisclosure().get(i).getDocumentId(),
			    				ordsResp.getStatus().getDisclosure().get(i).getDisclosedDtm()
			    				));
			}
		
			for ( int i = 0; i < ordsResp.getStatus().getReviews().size(); i++) {
				status.getReviews().add(
						new ReviewInfo(
								ordsResp.getStatus().getReviews().get(i).getApplicationId(),
								ordsResp.getStatus().getReviews().get(i).getStatus(),
								ordsResp.getStatus().getReviews().get(i).getReviewStartDtm(),
								ordsResp.getStatus().getReviews().get(i).getReviewEndDtm(),
								ordsResp.getStatus().getReviews().get(i).getReceiptNumberTxt(),
								ordsResp.getStatus().getReviews().get(i).getReviewId()
								));
			}
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
