package ca.bc.gov.open.pssg.rsbc.digitalforms.dao;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.*;
import org.springframework.http.ResponseEntity;

/**
 * 
 * Provides connectivity to the VIPS API from Digital Forms.
 * 
 * @author zacpez
 *
 */
public interface VipswsDao {

	// Creates Impoundments via VIPS API (remote)
    public CreateImpoundmentServiceResponse createImpoundment(CreateImpoundment impoundment);

    // Gets Impoundment via VIPS API (remote)
    public ResponseEntity<GetImpoundmentServiceResponse> getImpoundment(Long impoundmentId);

    // Prevalidate the Impoundment against the current VIPS impoundments
    public PrevalidateImpoundmentServiceResponse prevalidateImpoundment(CreateImpoundment updateImpoundment);

    // Creates Prohibition via VIPS API (remote)
    public CreateProhibitionServiceResponse createProhibition(CreateProhibition prohibition);

    // Gets Prohibition via VIPS API (remote)
    public ResponseEntity<GetProhibitionServiceResponse> getProhibition(Long prohibitionId);

    // Prevalidate the Impoundment against the current VIPS Prohibition
    public PrevalidateProhibitionServiceResponse prevalidateProhibition(CreateProhibition updateProhibition);

    // Associate a document to a notice
    public ResponseEntity<UpdateDocumentAssocServiceResponse> createDocumentAssociation(VipsDocAssocUpdateObj docAssocUpdateObj);

    // Get a case document
    public ResponseEntity<String> getDocument(Long documentId, GetDocument documentParams);

    // Prevalidate the Impoundment against the current VIPS Prohibition
    public PrevalidateDocumentServiceResponse prevalidateDocument(VipsDocAssocUpdateObj updateDocument);

    // Tests connectivity between this API and the VIPS API in the same environment
    public PingServiceResponse getPing();
    
}