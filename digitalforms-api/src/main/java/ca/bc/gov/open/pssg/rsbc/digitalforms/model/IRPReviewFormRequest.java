package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * IRP Review Form Request object
 * 
 * @author sivakaruna
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IRPReviewFormRequest {

	// TODO Update dummy value
	@JsonProperty("data")
	private String data;

}
