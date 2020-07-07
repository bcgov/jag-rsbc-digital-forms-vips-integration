package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.ApplicationApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ApplicationOrdsResponse;

import java.io.File;


/**
 * Application Service Implementation using ORDS services.
 *
 * @author sivakaruna
 */
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationApi applicationApi;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ApplicationServiceImpl(ApplicationApi applicationApi) {
        this.applicationApi = applicationApi;
    }

    @Override
    public ApplicationResponse getApplication(String typeCode, String metadata, String mimeType, String mimeSubType, String authGuid, File body) {

        try {
        	ApplicationOrdsResponse response = this.applicationApi.applicationNoticeNoGet(typeCode, sanitizeBase64(metadata), mimeType, mimeSubType, authGuid, body);
            return  ApplicationResponse.successResponse(response.getApplicationId(), response.getStatusCode(), response.getStatusMessage());

        } catch (ApiException ex) {

            logger.error("Application Service did throw exception: " + ex.getMessage(), ex);
            return ApplicationResponse.errorResponse(ex.getMessage());
        }
    }
    
    @Override
    public ApplicationResponse postApplication(String typeCode, String metadata, String mimeType, String mimeSubType, String authGuid, File body) {

        try {
        	ApplicationOrdsResponse response = this.applicationApi.applicationNoticeNoPost(typeCode, sanitizeBase64(metadata), mimeType, mimeSubType, authGuid, body);
            return  ApplicationResponse.successResponse(response.getApplicationId(), response.getStatusCode(), response.getStatusMessage());

        } catch (ApiException ex) {

            logger.error("Application Service did throw exception: " + ex.getMessage(), ex);
            return ApplicationResponse.errorResponse(ex.getMessage());
        }
    }
    
    @Override
    public ApplicationResponse patchApplication(String typeCode, String metadata, String mimeType, String mimeSubType, String authGuid, File body) {

        try {
        	ApplicationOrdsResponse response = this.applicationApi.applicationNoticeNoPatch(typeCode, sanitizeBase64(metadata), mimeType, mimeSubType, authGuid, body);
            return  ApplicationResponse.successResponse(response.getApplicationId(), response.getStatusCode(), response.getStatusMessage());

        } catch (ApiException ex) {

            logger.error("Application Service did throw exception: " + ex.getMessage(), ex);
            return ApplicationResponse.errorResponse(ex.getMessage());
        }
    }

    private String sanitizeBase64(String value) {
        return value
                .replace('/', '_')
                .replace('+', '-')
                .replaceAll("\r\n", "");
    }

}
