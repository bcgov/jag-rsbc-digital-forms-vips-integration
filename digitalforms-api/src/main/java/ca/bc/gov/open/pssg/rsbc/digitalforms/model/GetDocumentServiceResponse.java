package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

/**
 * 
 * GetDocumentServiceResponse
 * 
 * 
 * @author smillar
 *
 */
public final class GetDocumentServiceResponse extends VipswsBasicResponse {

	private byte[] document;
	private byte[] metadata; 
	private boolean b64 = false; 
	
    public GetDocumentServiceResponse(){};

    public GetDocumentServiceResponse(int respCd, String respMsg, byte[] document){
        this.respCd = respCd;
        this.respMsg = respMsg;
        this.document = document; 
    }
	
	/**
	 * @return the document
	 */
	public byte[] getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(byte[] document) {
		this.document = document;
	}

	/**
	 * @return the metadata
	 */
	public byte[] getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return the b64
	 */
	public boolean isB64() {
		return b64;
	}

	/**
	 * @param b64 the b64 to set
	 */
	public void setB64(boolean b64) {
		this.b64 = b64;
	}
	
}