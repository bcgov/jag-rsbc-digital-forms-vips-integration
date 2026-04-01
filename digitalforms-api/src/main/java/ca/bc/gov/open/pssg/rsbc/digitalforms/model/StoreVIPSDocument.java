package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type_code", "notice_type_code", "notice_subject_code" })
public class StoreVIPSDocument {

    @NotNull(message = "doc_type_cd is required")
    @Size(max = 10)
    @JsonProperty("doc_type_cd")
    private String docTypeCd;

    @NotNull(message = "notice_type_cd is required")
    @Size(max = 3)
    @JsonProperty("notice_type_cd")
    private String noticeTypeCd;

    @NotNull(message = "notice_subject_cd is required")
    @Size(max = 4)
    @JsonProperty("notice_subject_cd")
    private String noticeSubjectCd;

    @JsonProperty("dpc_type_cd")
    public String getDocTypeCd() {
        return docTypeCd;
    }

    @JsonProperty("dpc_type_cd")
    public void setTypeCode(String docTypeCd) {
        this.docTypeCd = docTypeCd;
    }

    public String getNoticeTypeCd() {
        return noticeTypeCd;
    }

    public void setNoticeTypeCd(String noticeTypeCd) {
        this.noticeTypeCd = noticeTypeCd;
    }

    public String getNoticeSubjectCd() {
        return noticeSubjectCd;
    }

    public void setNoticeSubjectCd(String noticeSubjectCd) {
        this.noticeSubjectCd = noticeSubjectCd;
    }
}