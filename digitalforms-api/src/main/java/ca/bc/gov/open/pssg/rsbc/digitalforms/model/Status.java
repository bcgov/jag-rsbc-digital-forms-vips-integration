package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author shaunmillargov
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"noticeTypeCd",
"effectiveDt",
"reviewFormSubmittedYn",
"reviewCreatedYn",
"originalCause",
"surnameNm",
"reviewStartDtm",
"reviewEndDtm",
"receiptNumberTxt"
})
public class Status {

@JsonProperty("noticeTypeCd")
private String noticeTypeCd;
@JsonProperty("effectiveDt")
private String effectiveDt;
@JsonProperty("reviewFormSubmittedYn")
private String reviewFormSubmittedYn;
@JsonProperty("reviewCreatedYn")
private String reviewCreatedYn;
@JsonProperty("originalCause")
private String originalCause;
@JsonProperty("surnameNm")
private String surnameNm;
@JsonProperty("reviewStartDtm")
private String reviewStartDtm;
@JsonProperty("reviewEndDtm")
private String reviewEndDtm;
@JsonProperty("receiptNumberTxt")
private String receiptNumberTxt;

@JsonProperty("noticeTypeCd")
public String getNoticeTypeCd() {
return noticeTypeCd;
}

@JsonProperty("noticeTypeCd")
public void setNoticeTypeCd(String noticeTypeCd) {
this.noticeTypeCd = noticeTypeCd;
}

@JsonProperty("effectiveDt")
public String getEffectiveDt() {
return effectiveDt;
}

@JsonProperty("effectiveDt")
public void setEffectiveDt(String effectiveDt) {
this.effectiveDt = effectiveDt;
}

@JsonProperty("reviewFormSubmittedYn")
public String getReviewFormSubmittedYn() {
return reviewFormSubmittedYn;
}

@JsonProperty("reviewFormSubmittedYn")
public void setReviewFormSubmittedYn(String reviewFormSubmittedYn) {
this.reviewFormSubmittedYn = reviewFormSubmittedYn;
}

@JsonProperty("reviewCreatedYn")
public String getReviewCreatedYn() {
return reviewCreatedYn;
}

@JsonProperty("reviewCreatedYn")
public void setReviewCreatedYn(String reviewCreatedYn) {
this.reviewCreatedYn = reviewCreatedYn;
}

@JsonProperty("originalCause")
public String getOriginalCause() {
return originalCause;
}

@JsonProperty("originalCause")
public void setOriginalCause(String originalCause) {
this.originalCause = originalCause;
}

@JsonProperty("surnameNm")
public String getSurnameNm() {
return surnameNm;
}

@JsonProperty("surnameNm")
public void setSurnameNm(String surnameNm) {
this.surnameNm = surnameNm;
}

@JsonProperty("reviewStartDtm")
public String getReviewStartDtm() {
return reviewStartDtm;
}

@JsonProperty("reviewStartDtm")
public void setReviewStartDtm(String reviewStartDtm) {
this.reviewStartDtm = reviewStartDtm;
}

@JsonProperty("reviewEndDtm")
public String getReviewEndDtm() {
return reviewEndDtm;
}

@JsonProperty("reviewEndDtm")
public void setReviewEndDtm(String reviewEndDtm) {
this.reviewEndDtm = reviewEndDtm;
}

@JsonProperty("receiptNumberTxt")
public String getReceiptNumberTxt() {
return receiptNumberTxt;
}

@JsonProperty("receiptNumberTxt")
public void setReceiptNumberTxt(String receiptNumberTxt) {
this.receiptNumberTxt = receiptNumberTxt;
}

}
