package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type_code", "notice_type_code", "notice_subject_code", "file_object" })
public class StoreVIPSDocument {

    @NotNull (message = "type_code is required")
    @Size(max = 10)
    @JsonProperty("type_code")
    private String typeCode;

    @NotNull (message = "notice_type_code is required")
    @Size(max = 3)
    @JsonProperty("notice_type_code")
    private String noticeTypeCode;

    @NotNull (message = "notice_subject_code is required")
    @Size(max = 4)
    @JsonProperty("notice_subject_code")
    private String noticeSubjectCode;
    
    @NotNull (message = "file_object is required")
    @JsonProperty("file_object")
    private byte[] fileObject; // base64 will map to byte[]

    
    // Getters and Setters
    @JsonProperty("type_code")
    public String getTypeCode() {
        return typeCode;
    }

    @JsonProperty("type_code")
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public byte[] getFileObject() {
        return fileObject;
    }

    public void setFileObject(byte[] fileObject) {
        this.fileObject = fileObject;
    }

    public String getNoticeTypeCode() {
        return noticeTypeCode;
    }

    public void setNoticeTypeCode(String noticeTypeCode) {
        this.noticeTypeCode = noticeTypeCode;
    }

    public String getNoticeSubjectCode() {
        return noticeSubjectCode;
    }

    public void setNoticeSubjectCode(String noticeSubjectCode) {
        this.noticeSubjectCode = noticeSubjectCode;
    }

}
