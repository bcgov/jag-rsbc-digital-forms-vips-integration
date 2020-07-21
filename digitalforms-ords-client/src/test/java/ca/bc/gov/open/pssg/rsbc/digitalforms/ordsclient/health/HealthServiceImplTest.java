package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.health;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.HealthApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.HealthOrdsResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.health.HealthResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.health.HealthServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Health service tests
 *
 * @author sivakaruna
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HealthServiceImplTest {

    public static final String API_EXCEPTION = "api exception";
    private HealthServiceImpl service;

    private static final String APP_ID = "VIPS";
    private static final String METHOD = "health_check";
    private static final String STATUS = "success";
    private static final String HOST = "devdb";
    private static final String INSTANCE = "deva";

    @Mock
    private HealthApi healthApiMock;

    @BeforeAll
    public void setup() throws ApiException {
        MockitoAnnotations.initMocks(this);

        service = new HealthServiceImpl(healthApiMock);
    }

    @Test
    public void withHealthReturnValidResponse() throws ApiException {

        HealthOrdsResponse successResponse = new HealthOrdsResponse();
        successResponse.setAppid(APP_ID);
        successResponse.setMethod(METHOD);
        successResponse.setStatus(STATUS);
        successResponse.setHost(HOST);
        successResponse.setInstance(INSTANCE);

        Mockito.when(healthApiMock.health()).thenReturn(successResponse);

        HealthResponse result = service.health();

        Assertions.assertEquals(APP_ID, result.getAppid());
        Assertions.assertEquals(METHOD, result.getMethod());
        Assertions.assertEquals(STATUS, result.getStatus());
        Assertions.assertEquals(HOST, result.getHost());
        Assertions.assertEquals(INSTANCE, result.getInstance());
    }
}
