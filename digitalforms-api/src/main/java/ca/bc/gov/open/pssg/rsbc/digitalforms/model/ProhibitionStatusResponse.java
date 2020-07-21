package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.jagvipsclient.VipsOrdsClientConstants;
import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;

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
		status = new Status();
		if ( ordsResp.getRespCode() == VipsOrdsClientConstants.SERVICE_SUCCESS_CD ) {
			status.setEffectiveDt(ordsResp.getStatus().getEffectiveDt());
			status.setNoticeTypeCd(ordsResp.getStatus().getNoticeTypeCd());
			status.setOriginalCause(ordsResp.getStatus().getOriginalCause());
			status.setReceiptNumberTxt(ordsResp.getStatus().getReceiptNumberTxt());
			status.setReviewCreatedYn(ordsResp.getStatus().getReviewCreatedYn());
			status.setReviewEndDtm(ordsResp.getStatus().getReviewEndDtm());
			status.setReviewFormSubmittedYn(ordsResp.getStatus().getReviewFormSubmittedYn());
			status.setReviewStartDtm(ordsResp.getStatus().getReviewStartDtm());
			status.setSurnameNm(ordsResp.getStatus().getSurnameNm());
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
