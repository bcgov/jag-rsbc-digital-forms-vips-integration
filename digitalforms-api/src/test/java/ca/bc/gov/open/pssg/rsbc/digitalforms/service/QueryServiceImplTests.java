package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionService;
import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionStatus;
import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;

/**
 * 
 * Query Service Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class QueryServiceImplTests {

	@Mock
	private ProhibitionService service;

	@InjectMocks
	private QueryService serviceImpl = new QueryServiceImpl();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - QueryService")
	@Test
	void getProhibitionStatusSuccess() throws DigitalFormsException {
		when(service.getVipsProhibitionStatus(any(), any()))
				.thenReturn(VipsProhibitionStatusResponse.successResponse(new ProhibitionStatus(), "0", "success"));
		VipsProhibitionStatusResponse resp = serviceImpl.getProhibitionStatus("1", "correlationId");
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Get error - QueryService")
	@Test
	void getProhibitionStatusError() throws DigitalFormsException {
		when(service.getVipsProhibitionStatus(any(), any()))
				.thenReturn(VipsProhibitionStatusResponse.errorResponse("Get error"));
		VipsProhibitionStatusResponse resp = serviceImpl.getProhibitionStatus("1", "correlationId");
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Get error", resp.getRespMsg());
	}
}
