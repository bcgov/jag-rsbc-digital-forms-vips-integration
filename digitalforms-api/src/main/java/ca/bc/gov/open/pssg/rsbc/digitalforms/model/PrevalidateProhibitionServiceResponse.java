package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 *
 * PrevalidateProhibitionServiceResponse
 *
 * @author smillar
 *
 */
public final class PrevalidateProhibitionServiceResponse extends VipswsBasicResponse {

    private Long prohibitionId;
    private boolean update;

    public PrevalidateProhibitionServiceResponse(){};

    public PrevalidateProhibitionServiceResponse(int respCd, String respMsg, Long prohibitionId) {
        this.respCd = respCd;
        this.respMsg = respMsg;
        this.prohibitionId = prohibitionId;
        this.update = false;
        if(this.prohibitionId != 0) {
            this.update = true;
        }
    }

    public Long getProhibitionId() {
        return this.prohibitionId;
    }

    public void setProhibitionId(Long prohibitionId) {
        this.prohibitionId = prohibitionId;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

}