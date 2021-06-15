package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * PrevalidateReviewDecisionServiceResponse
 *  
 * @author smillar
 *
 */
public final class PrevalidateImpoundmentServiceResponse extends VipswsBasicResponse {

    private Long impoundmentId;
    private boolean update;

	public PrevalidateImpoundmentServiceResponse(){};

    public PrevalidateImpoundmentServiceResponse(int respCd, String respMsg, Long impoundmentId) {
        this.respCd = respCd;
        this.respMsg = respMsg;
        this.impoundmentId = impoundmentId;
        this.update = false;
        if(this.impoundmentId != 0) {
            this.update = true;
        }
    }
    
    public Long getImpoundmentId() {
        return this.impoundmentId;
    }
    public void setImpoundmentId(Long impoundmentId) {
        this.impoundmentId = impoundmentId;
    }

    public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}

}