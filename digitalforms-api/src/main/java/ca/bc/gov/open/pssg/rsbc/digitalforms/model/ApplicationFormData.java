package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationInfo;

/**
 * 
 * Review Form Request object
 * 
 * @author sivakaruna
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "formType", "formVersion", "fields", "formData" })
public class ApplicationFormData {

	@JsonProperty("formType")
	private String formType;
	@JsonProperty("formVersion")
	private String formVersion;
	@JsonProperty("fields")
	private List<ApplicationInfo> fields = null;
	@JsonProperty("formData")
	private String formData;

	@JsonProperty("formType")
	public String getFormType() {
		return formType;
	}

	@JsonProperty("formType")
	public void setFormType(String formType) {
		this.formType = formType;
	}

	@JsonProperty("formVersion")
	public String getFormVersion() {
		return formVersion;
	}

	@JsonProperty("formVersion")
	public void setFormVersion(String formVersion) {
		this.formVersion = formVersion;
	}

	@JsonProperty("fields")
	public List<ApplicationInfo> getFields() {
		return fields;
	}

	@JsonProperty("fields")
	public void setFields(List<ApplicationInfo> fields) {
		this.fields = fields;
	}

	@JsonProperty("formData")
	public String getFormData() {
		return formData;
	}

	@JsonProperty("formData")
	public void setFormData(String formData) {
		this.formData = formData;
	}
}
