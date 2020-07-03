package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import java.io.File;

/**
 * Collection of services for applications.
 *
 * @author sivakaruna
 */
public interface ApplicationService {

     ApplicationResponse vipsApplication(String typeCode, String metadata, String mimeType, String mimeSubType,
                                       String authGuid, File body);

}
