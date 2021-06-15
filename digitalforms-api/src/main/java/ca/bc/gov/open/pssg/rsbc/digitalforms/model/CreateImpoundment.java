package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * CreateImpoundment type - Passed in POST message body and used to create a Impoundment
 * 
 * Aggregate object based on store procedure requirements
 *
 *
 * @author smillar
 *
 */
public class CreateImpoundment {

	@JsonProperty("vipsImpoundCreate") // -R
	private VipsImpoundmentCreate vipsImpoundmentCreate = new VipsImpoundmentCreate();
	
	@JsonProperty("vipsRegistrationCreateArray") // -R
	private List<VipsRegistrationCreate> vipsRegistrationCreateArray = new ArrayList<VipsRegistrationCreate>();

	@JsonProperty("vipsVehicleCreate") // -R
	private VipsVehicleCreate vipsVehicleCreate = new VipsVehicleCreate();
	
	@JsonProperty("vipsDocumentIdArray")
	private List<Long> vipsDocumentIdArray = new ArrayList<Long>();
	
	@JsonProperty("vipsImpoundmentArray")
	private List<Long> vipsImpoundmentArray = new ArrayList<Long>();
	

	public CreateImpoundment() {};
	
	public VipsImpoundmentCreate getVipsImpoundmentCreate() {
		return vipsImpoundmentCreate;
	}

	public void setVipsImpoundmentCreate(VipsImpoundmentCreate vipsImpoundmentCreate) {
		this.vipsImpoundmentCreate = vipsImpoundmentCreate;
	}

	public List<VipsRegistrationCreate> getVipsRegistrationCreateArray() {
		return vipsRegistrationCreateArray;
	}

	public void setVipsRegistrationCreateArray(List<VipsRegistrationCreate> vipsRegistrationCreateArray) {
		this.vipsRegistrationCreateArray = vipsRegistrationCreateArray;
	}

	public VipsVehicleCreate getVipsVehicleCreate() {
		return vipsVehicleCreate;
	}

	public void setVipsVehicleCreate(VipsVehicleCreate vipsVehicleCreate) {
		this.vipsVehicleCreate = vipsVehicleCreate;
	}

	public List<Long> getVipsDocumentIdArray() {
		return vipsDocumentIdArray;
	}

	public void setVipsDocumentIdArray(List<Long> vipsDocumentIdArray) {
		this.vipsDocumentIdArray = vipsDocumentIdArray;
	}
	
	public List<Long> getVipsImpoundmentArray() {
		return vipsImpoundmentArray;
	}

	public void setVipsImpoundmentArray(List<Long> vipsImpoundmentArray) {
		this.vipsImpoundmentArray = vipsImpoundmentArray;
	}

}
