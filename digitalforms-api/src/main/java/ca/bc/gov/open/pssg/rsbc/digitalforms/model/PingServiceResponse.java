package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * Ping Service Response
 * 
 * 
 * @author smillar
 *
 */
public final class PingServiceResponse extends VipswsBasicResponse {

	private String dbResponse;

	public PingServiceResponse() {};

	public PingServiceResponse(int respCd, String respMsg, String dbResponse) {
		this.respCd = respCd;
		this.respMsg = respMsg;
		this.dbResponse = dbResponse;
	}

	public String getDbResponse() {
		return dbResponse;
	}

	public void setDbResponse(String dbResponse) {
		this.dbResponse = dbResponse;
	}

}