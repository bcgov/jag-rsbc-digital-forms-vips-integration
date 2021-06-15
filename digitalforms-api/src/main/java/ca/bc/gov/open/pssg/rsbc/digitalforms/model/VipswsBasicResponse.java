package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * VipswsBasicResponse
 * 
 * Base class for VIPSWS responses.
 * 
 * @author smillar
 *
 */
public class VipswsBasicResponse {
	
    protected int respCd = 99; // not set default - possible JAVA fault., 0 == good, 1 == bad
    protected String respMsg;
    
	public int getRespCd() {
		return respCd;
	}
	public void setRespCd(int respCd) {
		this.respCd = respCd;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
  
}
