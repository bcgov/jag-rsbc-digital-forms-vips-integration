package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * GetProhibitionServiceResponse
 * 
 * 
 * @author smillar
 *
 */
public final class GetProhibitionServiceResponse extends VipswsBasicResponse {

    private VipsProhibitionObj result;
    
    public GetProhibitionServiceResponse(){};

    public GetProhibitionServiceResponse(int respCd, String respMsg, VipsProhibitionObj result){
        this.respCd = respCd;
        this.respMsg = respMsg;
        this.setResult(result);
    }

	public VipsProhibitionObj getResult() {
		return result;
	}

	public void setResult(VipsProhibitionObj result) {
		this.result = result;
	}

}