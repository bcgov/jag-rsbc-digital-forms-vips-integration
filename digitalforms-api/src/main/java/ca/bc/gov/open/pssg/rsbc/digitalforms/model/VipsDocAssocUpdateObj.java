package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({ "documentId", "prohibitionId", "impoundmentId", "noticeTypeCd", "noticeNo" })
public class VipsDocAssocUpdateObj {
	
	@JsonProperty("documentId")
	private Long documentId;
	
	@JsonProperty("prohibitionId")
	private Long prohibitionId;
	
	@JsonProperty("impoundmentId")
	private Long impoundmentId;
	
	@JsonProperty("noticeTypeCd")
	private String noticeTypeCd;
	
	@JsonProperty("noticeNo")
	private String noticeNo;

	@JsonProperty("documentId")
	public Long getDocumentId() {
		return documentId;
	}

	@JsonProperty("documentId")
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@JsonProperty("prohibitionId")
	public Long getProhibitionId() {
		return prohibitionId;
	}

	@JsonProperty("prohibitionId")
	public void setProhibitionId(Long prohibitionId) {
		this.prohibitionId = prohibitionId;
	}

	@JsonProperty("impoundmentId")
	public Long getImpoundmentId() {
		return impoundmentId;
	}

	@JsonProperty("impoundmentId")
	public void setImpoundmentId(Long impoundmentId) {
		this.impoundmentId = impoundmentId;
	}

	@JsonProperty("noticeTypeCd")
	public String getNoticeTypeCd() {
		return noticeTypeCd;
	}

	@JsonProperty("noticeTypeCd")
	public void setNoticeTypeCd(String noticeTypeCd) {
		this.noticeTypeCd = noticeTypeCd;
	}

	@JsonProperty("noticeNo")
	public String getNoticeNo() {
		return noticeNo;
	}

	@JsonProperty("noticeNo")
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
}
