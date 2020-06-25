package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * IRP Status Info Response Object
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "IRPInfo" })
public class IRPStatusInfoResponse {

	@JsonProperty("IRPInfo")
	private IRPInfo iRPInfo;
	
	public IRPStatusInfoResponse(IRPInfo irpInfo) {
		this.iRPInfo = irpInfo;
	}
	
	@JsonProperty("IRPInfo")
	public IRPInfo getIRPInfo() {
		return iRPInfo;
	}

	@JsonProperty("IRPInfo")
	public void setIRPInfo(IRPInfo iRPInfo) {
		this.iRPInfo = iRPInfo;
	}

}