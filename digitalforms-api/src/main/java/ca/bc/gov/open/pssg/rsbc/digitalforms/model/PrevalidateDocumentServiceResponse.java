package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 *
 * PrevalidateDocumentServiceResponse
 *
 * @author smillar
 *
 */
public final class PrevalidateDocumentServiceResponse extends VipswsBasicResponse {

    private Long documentId;
    private boolean update;

    public PrevalidateDocumentServiceResponse(){};

    public PrevalidateDocumentServiceResponse(int respCd, String respMsg, Long documentId) {
        this.respCd = respCd;
        this.respMsg = respMsg;
        this.documentId = documentId;
        this.update = false;
        if(this.documentId != 0) {
            this.update = true;
        }
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

}