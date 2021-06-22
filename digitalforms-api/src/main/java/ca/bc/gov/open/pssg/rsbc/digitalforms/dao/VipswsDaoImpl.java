package ca.bc.gov.open.pssg.rsbc.digitalforms.dao;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.*;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.VipswsConstants;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

/**
 * 
 * VIPS WS DAO Implementation Class.
 * 
 * Provides connectivity to the VIPS API RESTful services.
 * 
 * 
 * @author zacpez
 * TODO: 2021-06-11 update user, guid, etc
 */
@Service
public class VipswsDaoImpl implements VipswsDao {

    @Value("${vips.api.endpoint:MISSING}")
    private String uri;
    
    @Value("${vips.api.user.universalid:MISSING}")
    private String universalId;

    @Value("${vips.api.user.userdisplayname:MISSING}")
    private String userDisplayName;

    @Value("${vips.api.user.guid:MISSING}")
    private String guid;

    private final RestTemplate restTemplate;

    private final Logger logger;

    @Autowired
    public VipswsDaoImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.logger = LoggerFactory.getLogger(VipswsDaoImpl.class);
    }

    /**
     * Pass-through API call for POST Impoundment
     *
     * @param impoundment in VIPS
     * @return CreateImpoundmentServiceResponse
     */
    @Override
    public CreateImpoundmentServiceResponse createImpoundment(CreateImpoundment impoundment) {
        logger.info("Invoking createImpoundment impoundmentNoticeNo = " + impoundment.getVipsImpoundmentCreate().getImpoundmentNoticeNo());

        RestTemplate restTemplate = new RestTemplate();

        String finalEndpoint = ensureTrailingSlash(uri) + "cases/impoundments";
        logger.info("POSTing data to VIPS API create impoundment endpoint: " + finalEndpoint);

        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
            impoundment,
            createVipsHeaders(
                userDisplayName,
                guid,
                universalId,
                "", // content type
                false, // basic auth.
                null, // user
                null
            ) // passwd
        );

        ResponseEntity<CreateImpoundmentServiceResponse> resp = null;

        try {

            resp = restTemplate.exchange(finalEndpoint, HttpMethod.POST, httpEntity,
                    CreateImpoundmentServiceResponse.class);

            logger.info("RespCd = " + (resp.getBody()).getRespCd()
                    + ", RespMsg = " + (resp.getBody()).getRespMsg());

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to createImpoundment at VIPS API from Digital Forms resulted in unexpected http status code, "
                    + ex.getRawStatusCode();
            logger.error(msg);
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to createImpoundment at VIPS API from Digital Forms resulted in unexpected http status code, 500";
            logger.error(msg);
            ex.printStackTrace();
        }

        return resp.getBody();
    }

    /**
     * Pass-through API call for GET Impoundment
     *
     * @param impoundmentId in VIPS
     * @return ResponseEntity<GetImpoundmentServiceResponse>
     */
    public ResponseEntity<GetImpoundmentServiceResponse> getImpoundment(Long impoundmentId) {
        RestTemplate restTemplate = new RestTemplate();
        String finalEndpoint = ensureTrailingSlash(uri) + "cases/impoundments/" + impoundmentId;

        HttpEntity<?> httpEntity = new HttpEntity<Object>(null,
            createVipsHeaders(
                userDisplayName,
                guid,
                universalId,
                "", // content type
                false, // basic auth.
                null, // user
                null));// passwd

        ResponseEntity<GetImpoundmentServiceResponse> resp = null;
        try {

            resp = restTemplate.exchange(finalEndpoint, HttpMethod.GET, httpEntity, GetImpoundmentServiceResponse.class);

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, " + ex.getRawStatusCode();
            logger.error(msg);
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, 500";
            logger.error(msg);
            ex.printStackTrace();
        }

        return resp;
    }

    @Override
    public PrevalidateImpoundmentServiceResponse prevalidateImpoundment(CreateImpoundment updateImpoundment) {
        return new PrevalidateImpoundmentServiceResponse(0,"", 0l);
    }

    /**
     * Pass-through API call for POST prohibition
     *
     * @param prohibition in VIPS
     * @return
     */
    @Override
    public CreateProhibitionServiceResponse createProhibition(CreateProhibition prohibition) {
        logger.info("Invoking createProhibition prohibitionNoticeNo = " +
            prohibition.getVipsProhibitionCreate().getProhibitionNoticeNo());

        String finalEndpoint = ensureTrailingSlash(uri) + "cases/prohibitions";
        logger.info("POSTing data to VIPS API create Prohibition endpoint: " + finalEndpoint);

        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
            prohibition,
            createVipsHeaders(
                userDisplayName,
                guid,
                universalId,
                "", // content type
                false, // basic auth.
                null, // user
                null // password
            )
        );

        ResponseEntity<CreateProhibitionServiceResponse> resp = null;

        try {

            resp = restTemplate.exchange(finalEndpoint, HttpMethod.POST, httpEntity,
                    CreateProhibitionServiceResponse.class);

            logger.info("RespCd = " + (resp.getBody()).getRespCd()
                    + ", RespMsg = " + (resp.getBody()).getRespMsg());

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to createProhibition at VIPS API from Digital Forms resulted in unexpected http status code, "
                    + ex.getRawStatusCode();
            logger.error(msg);
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to createProhibition at VIPS API from Digital Forms resulted in unexpected http status code, 500";
            logger.error(msg);
            ex.printStackTrace();
        }

        return resp.getBody();
    }

    /**
     * Pass-through API call for GET Prohibition
     *
     * @param prohibitionId in VIPS
     * @return
     */
    public ResponseEntity<GetProhibitionServiceResponse> getProhibition(Long prohibitionId) {
        String finalEndpoint = ensureTrailingSlash(uri) + "cases/prohibitions/" + prohibitionId;

        HttpEntity<?> httpEntity = new HttpEntity<Object>(null,
            createVipsHeaders(
                userDisplayName,
                guid,
                universalId,
                "", // content type
                false, // basic auth.
                null, // user
                null));// passwd

        ResponseEntity<GetProhibitionServiceResponse> resp = null;
        try {

            resp = restTemplate.exchange(finalEndpoint, HttpMethod.GET, httpEntity, GetProhibitionServiceResponse.class);

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, " + ex.getRawStatusCode();
            logger.error(msg);
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, 500";
            logger.error(msg);
            ex.printStackTrace();
        }

        return resp;
    }

    @Override
    public PrevalidateProhibitionServiceResponse prevalidateProhibition(CreateProhibition updateProhibition) {
        return new PrevalidateProhibitionServiceResponse(0,"", 0l);
    }

    @Override
    public ResponseEntity<UpdateDocumentAssocServiceResponse> createDocumentAssociation(VipsDocAssocUpdateObj docAssocUpdateObj) {
        String finalEndpoint = ensureTrailingSlash(uri) + "document-association/notice";

        HttpEntity<?> httpEntity = new HttpEntity<Object>(
            docAssocUpdateObj,
            createVipsHeaders(
                userDisplayName,
                guid,
                universalId,
                "", // content type
                false, // basic auth.
                null, // user
                null));// passwd

        ResponseEntity<UpdateDocumentAssocServiceResponse> resp = null;
        try {

            resp = restTemplate.exchange(finalEndpoint, HttpMethod.PUT, httpEntity, UpdateDocumentAssocServiceResponse.class);

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, " + ex.getRawStatusCode();
            logger.error(msg);
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, 500";
            logger.error(msg);
            ex.printStackTrace();
        }

        return resp;
    }

    /**
     *
     * @param documentId of case document
     * @param documentParams `b64` get in base 64 encoding
     * @param documentParams `url` get as URL
     * @return
     */
    public ResponseEntity<String> getDocument(Long documentId, GetDocument documentParams) {
        String finalEndpoint = ensureTrailingSlash(uri) + "documents/" + documentId;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(finalEndpoint)
                .queryParam("b64", documentParams.getDocumentInB64())
                .queryParam("url", documentParams.getDocumentAsUrl());

        HttpEntity<?> httpEntity = new HttpEntity<Object>(
                null,
            createVipsHeaders(
                userDisplayName,
                guid,
                universalId,
                "", // content type
                false, // basic auth.
                null, // user
                null));// passwd

        ResponseEntity<String> resp = null;
        try {

            resp = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, httpEntity, String.class);

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, " + ex.getRawStatusCode();
            logger.error(msg);
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, 500";
            logger.error(msg);
            ex.printStackTrace();
        }

        return resp;
    }

    @Override
    public PrevalidateDocumentServiceResponse prevalidateDocument(VipsDocAssocUpdateObj updateDocument) {
        return new PrevalidateDocumentServiceResponse(0,"", 0l);
    }

    @Override
    public PingServiceResponse getPing() {
        logger.info("Invoking getPing (VIPS connectivity test");
        String finalEndpoint = ensureTrailingSlash(uri) + "ping";

        PingServiceResponse resp = null;

        try {

            resp = restTemplate.getForObject(finalEndpoint, PingServiceResponse.class);

            logger.info("RespCd = " + resp.getRespCd() + ", RespMsg = " + resp.getRespMsg());

            // 40n type errors
        } catch (HttpClientErrorException ex) {
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, " + ex.getRawStatusCode();
            logger.error(msg);
            resp.setRespCd(VipswsConstants.VIPSWS_GENERAL_FAILURE_CD);
            resp.setRespMsg("Failure");
            ex.printStackTrace();

            // 500 Internal server error
        } catch (HttpServerErrorException ex) { // 500
            String msg = "Call to VIPS GET Ping resulted in unexpected http status code, 500";
            logger.error(msg);
            resp.setRespCd(VipswsConstants.VIPSWS_GENERAL_FAILURE_CD);
            resp.setRespMsg("Failure");
            ex.printStackTrace();
        }

        return resp;

    }

    /**
     * Creates the headers required for call to VIPS.
     *
     * @param smUserDisplayName siteminder display name
     * @param smUserGuid siteminder GUID
     * @param smUniversalId siteminder UUID
     * @param contentType value to set to set content type, blank is application/json by default
     * @param isBasicAuth true to set basic auth header
     * @param user siteminder basic auth username
     * @param password siteminder basic auth password
     * @return
     */
    private static HttpHeaders createVipsHeaders(final String smUserDisplayName, final String smUserGuid,
            final String smUniversalId, String contentType, boolean isBasicAuth, String user, String password) {

        return new HttpHeaders() {
            private static final long serialVersionUID = -7877408538374560793L;
            {
                set("sm_universalid", smUniversalId);
                set("smgov_userdisplayname", smUserDisplayName);
                set("smgov_userguid", smUserGuid);

                if (contentType != "") {
                    set("Content-Type", contentType);
                } else {
                    set("Content-Type", "application/json");
                }

                if (isBasicAuth) {
                    String auth = user + ":" + password;
                    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                    String authHeader = "Basic " + new String(encodedAuth);
                    set("Authorization", authHeader);
                }
            }
        };

    }

    /**
     * Helper method ensuring trailing slash before appending more path.
     * 
     */
    private String ensureTrailingSlash(String uri) {
        return uri.endsWith("/") ? uri : uri + "/";
    }

}