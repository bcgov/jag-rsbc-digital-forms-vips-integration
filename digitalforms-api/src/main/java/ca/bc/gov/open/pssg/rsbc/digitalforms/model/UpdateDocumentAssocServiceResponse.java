package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * UpdateDocumentAssocServiceResponse
 * 
 * 
 * @author smillar
 *
 */
public final class UpdateDocumentAssocServiceResponse extends VipswsBasicResponse {

    public UpdateDocumentAssocServiceResponse(){};

    public UpdateDocumentAssocServiceResponse(int respCd, String respMsg){
        this.respCd = respCd;
        this.respMsg = respMsg;
    }

}