package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * To be removed once updates to VIPS ORDS have been completed to support
 * changes to the Prohibition Status operation.
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prohibitionStatus" })
public class MockProhibitionStatusResponse {

	@JsonProperty("prohibitionStatus")
	private MockProhibitionStatus prohibitionStatus;

	public MockProhibitionStatusResponse(MockProhibitionStatus prohibitionStatus) {
		this.prohibitionStatus = prohibitionStatus;
	}

	@JsonProperty("prohibitionStatus")
	public MockProhibitionStatus getProhibitionStatus() {
		return prohibitionStatus;
	}

	@JsonProperty("prohibitionStatus")
	public void setProhibitionStatus(MockProhibitionStatus prohibitionStatus) {
		this.prohibitionStatus = prohibitionStatus;
	}

}
