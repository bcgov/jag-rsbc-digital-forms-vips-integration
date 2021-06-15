package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * CreateProhibition type - Passes in POST message body and used to create a
 * Prohibition
 * 
 * Aggregate object based on store procedure requirements
 *
 * 
 * 
 * @author smillar
 *
 */
public class CreateProhibition {

	@JsonProperty("vipsProhibitionCreate") // -R
	private VipsProhibitionCreate vipsProhibitionCreate = new VipsProhibitionCreate();

	@JsonProperty("vipsRegistrationCreate") // -R
	private VipsRegistrationCreate vipsRegistrationCreate = new VipsRegistrationCreate();

//  Flagged for delete
//	@JsonProperty("vipsLicenceCreate") // -R
//	private VipsLicenceCreate vipsLicenceCreate = new VipsLicenceCreate();
	
	@JsonProperty("vipsDocumentIdArray") // -R
	private List<Long> vipsDocumentIdArray = new ArrayList<Long>();

	public CreateProhibition() {};

	@ApiModelProperty(value = "Vips Prohibition Create obj")
	public final VipsProhibitionCreate getVipsProhibitionCreate() {
		return vipsProhibitionCreate;
	}

	public void setVipsProhibitionCreate(VipsProhibitionCreate vipsProhibitionCreate) {
		this.vipsProhibitionCreate = vipsProhibitionCreate;
	}

	@ApiModelProperty(value = "Vips Registration Create obj")
	public VipsRegistrationCreate getVipsRegistrationCreate() {
		return vipsRegistrationCreate;
	}

	public void setVipsRegistrationCreate(VipsRegistrationCreate vipsRegistrationCreate) {
		this.vipsRegistrationCreate = vipsRegistrationCreate;
	}

	public List<Long> getVipsDocumentIdArray() {
		return vipsDocumentIdArray;
	}

	public void setVipsDocumentIdArray(List<Long> vipsDocumentIdArray) {
		this.vipsDocumentIdArray = vipsDocumentIdArray;
	}

	/**
	 * Testing purposes only
	 * 
	 * @param args
	 * @throws JsonProcessingException
	 */
	public static void main(String[] args) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		CreateProhibition obj = new CreateProhibition();

		// Object to JSON in String
		String jsonInString = mapper.writeValueAsString(obj);

		System.out.println(jsonInString);
	}

}
