package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import java.io.File;

/**
 * Collection of services for applications.
 *
 * @author sivakaruna
 */
public interface ApplicationService {

     ApplicationResponse getApplication(String typeCode, String metadata, String mimeType, String mimeSubType,
                                       String authGuid, File body);
     
     ApplicationResponse postApplication(String typeCode, String metadata, String mimeType, String mimeSubType,
             String authGuid, File body);
     
     ApplicationResponse patchApplication(String typeCode, String metadata, String mimeType, String mimeSubType,
             String authGuid, File body);

}
