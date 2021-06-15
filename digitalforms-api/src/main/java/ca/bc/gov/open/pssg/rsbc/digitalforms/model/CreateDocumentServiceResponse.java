package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * CreateDocumentServiceResponse
 * 
 * 
 * @author smillar
 *
 */
public final class CreateDocumentServiceResponse extends VipswsBasicResponse {

    //private List<DocumentServiceResult> result;
    
    public CreateDocumentServiceResponse(){};
    
    public CreateDocumentServiceResponse(int respCd, String respMsg){
        this.respCd = respCd;
        this.respMsg = respMsg;
    }

}