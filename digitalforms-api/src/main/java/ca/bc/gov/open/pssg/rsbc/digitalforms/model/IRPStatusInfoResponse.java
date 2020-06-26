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
@JsonPropertyOrder({ "iRPInfo" })
public class IRPStatusInfoResponse {

	@JsonProperty("iRPInfo")
	private IRPInfo iRPInfo;
	
	public IRPStatusInfoResponse(IRPInfo irpInfo) {
		this.iRPInfo = irpInfo;
	}
	
	@JsonProperty("iRPInfo")
	public IRPInfo getIRPInfo() {
		return iRPInfo;
	}

	@JsonProperty("iRPInfo")
	public void setIRPInfo(IRPInfo iRPInfo) {
		this.iRPInfo = iRPInfo;
	}

}