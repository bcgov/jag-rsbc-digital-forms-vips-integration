package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.Date;

import ca.bc.gov.open.pssg.rsbc.digitalforms.util.TimeDateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * VipsDocumentObj - mimics 'vips_document_obj'
 * 
 * @author smillar
 *
 */
public class VipsDocumentObj {
	
	@JsonProperty("documentId") //15
	private Long documentId = null;

	@JsonProperty("documentTypeCd") //10
	private String documentTypeCd = null;
	
	@JsonProperty("documentTypeDsc") //100
	private String documentTypeDsc = null;
	
	@JsonProperty("prohibitionId") // 15 
	private Long prohibitionId = null;
	
	@JsonProperty("impoundmentId") // 15 
	private Long impoundmentId = null;
	
	@JsonProperty("disclosureDocumentYN") // 1 
	private String disclosureDocumentYN = null;
	
	@JsonProperty("pageCount") // 1 
	private Long pageCount = null;

	@JsonProperty("addToFileDtm") 
	private Date addToFileDtm;
	
	@JsonProperty("receivedDtm") 
	private Date receivedDtm;
	
	@JsonProperty("disclosedToClientDtm") 
	private Date disclosedToClientDtm;
	
	
	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentTypeCd() {
		return documentTypeCd;
	}

	public void setDocumentTypeCd(String documentTypeCd) {
		this.documentTypeCd = documentTypeCd;
	}
	
	public String getDocumentTypeDsc() {
		return documentTypeDsc;
	}

	public void setDocumentTypeDsc(String documentTypeDsc) {
		this.documentTypeDsc = documentTypeDsc;
	}

	public Long getProhibitionId() {
		return prohibitionId;
	}

	public void setProhibitionId(Long prohibitionId) {
		this.prohibitionId = prohibitionId;
	}

	public Long getImpoundmentId() {
		return impoundmentId;
	}

	public void setImpoundmentId(Long impoundmentId) {
		this.impoundmentId = impoundmentId;
	}

	public String getDisclosureDocumentYN() {
		return disclosureDocumentYN;
	}
	
	public void setDisclosureDocumentYN(String disclosureDocumentYN) {
		this.disclosureDocumentYN = disclosureDocumentYN;
	}

	/**
	 * @return the addToFileDt
	 */
	public String getAddToFileDtm() {
		if ( null != addToFileDtm) {
			return TimeDateUtils.getISO8601FromDate(addToFileDtm);
		} else {
			return null;
		}
	}

	/**
	 * @param addToFileDt the addToFileDt to set
	 */
	public void setAddToFileDtm(Date addToFileDtm) {
		this.addToFileDtm = addToFileDtm;
	}

	/**
	 * @return the receivedDt
	 */
	public String getReceivedDtm() {
		if ( null != receivedDtm) {
			return TimeDateUtils.getISO8601FromDate(receivedDtm);
		} else {
			return null;
		}
	}

	/**
	 * @param receivedDt the receivedDt to set
	 */
	public void setReceivedDtm(Date receivedDtm) {
		this.receivedDtm = receivedDtm;
	}

	/**
	 * @return the disclosedToClientDt
	 */
	public String getDisclosedToClientDtm() {
		if ( null != disclosedToClientDtm) {
			return TimeDateUtils.getISO8601FromDate(disclosedToClientDtm);
		} else {
			return null;
		}
	}

	/**
	 * @param disclosedToClientDt the disclosedToClientDt to set
	 */
	public void setDisclosedToClientDtm(Date disclosedToClientDtm) {
		this.disclosedToClientDtm = disclosedToClientDtm;
	}

	/**
	 * @return the pageCount
	 */
	public Long getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

}
