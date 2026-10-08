package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.bc.gov.open.jagvipsclient.validation.VipsValidExpiryDateResponse;
import ca.bc.gov.open.jagvipsclient.validation.VipsValidTimeframeResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ValidationServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@WithMockUser(authorities = "USER")
class ValidationControllerTests {

    private static final String START_DATE = "2026-10-08";
    private static final BigDecimal INTERVAL_DAYS = new BigDecimal("7");

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ValidationServiceImpl service;

    @AfterEach
    void clearMdc() {
        MDC.clear();
    }

    @ParameterizedTest
    @CsvSource({"true,Y,200", "true,N,200", "false,N,500"})
    void timeframePreservesOrdsStatusAndValidity(boolean success, String valid, int httpStatus) throws Exception {
        VipsValidTimeframeResponse data = mock(VipsValidTimeframeResponse.class);
        when(data.getRespCode()).thenReturn(success ? DigitalFormsConstants.ORDS_SUCCESS_CD
                : DigitalFormsConstants.ORDS_FAILURE_CD);
        when(data.getValid()).thenReturn(valid);
        when(service.withinTimeFrame(START_DATE, INTERVAL_DAYS)).thenReturn(data);
        MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "test");

        mockMvc.perform(get("/api/validation/withinTimeframe")
                .param("startDate", START_DATE).param("intervalDays", "7"))
                .andExpect(status().is(httpStatus)).andExpect(jsonPath("$.valid").value(valid));

        verify(service).withinTimeFrame(START_DATE, INTERVAL_DAYS);
        Assertions.assertNull(MDC.get(DigitalFormsConstants.REQUEST_ENDPOINT));
    }

    @ParameterizedTest
    @CsvSource({"true,200", "false,500"})
    void expiryDatePreservesOrdsStatusAndDate(boolean success, int httpStatus) throws Exception {
        VipsValidExpiryDateResponse data = mock(VipsValidExpiryDateResponse.class);
        when(data.getRespCode()).thenReturn(success ? DigitalFormsConstants.ORDS_SUCCESS_CD
                : DigitalFormsConstants.ORDS_FAILURE_CD);
        when(data.getExpiryDate()).thenReturn("2026-10-15");
        when(service.validateExpiryDate(START_DATE, INTERVAL_DAYS)).thenReturn(data);
        MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "test");

        mockMvc.perform(get("/api/validation/validExpiryDate")
                .param("startDate", START_DATE).param("intervalDays", "7"))
                .andExpect(status().is(httpStatus)).andExpect(jsonPath("$.expiry_date").value("2026-10-15"));

        verify(service).validateExpiryDate(START_DATE, INTERVAL_DAYS);
        Assertions.assertNull(MDC.get(DigitalFormsConstants.REQUEST_ENDPOINT));
    }

    @ParameterizedTest
    @CsvSource({"withinTimeframe,startDate", "withinTimeframe,intervalDays",
            "validExpiryDate,startDate", "validExpiryDate,intervalDays"})
    void missingRequiredParameterReturnsErrorWithoutCallingService(String endpoint, String missingParameter)
            throws Exception {
        String suppliedParameter = missingParameter.equals("startDate") ? "intervalDays" : "startDate";
        String suppliedValue = suppliedParameter.equals("startDate") ? START_DATE : "7";

        mockMvc.perform(get("/api/validation/" + endpoint).param(suppliedParameter, suppliedValue))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.resp").value(DigitalFormsConstants.JSON_RESPONSE_FAIL))
                .andExpect(jsonPath("$.error.httpStatus").value(400))
                .andExpect(jsonPath("$.error.message").value(DigitalFormsConstants.MISSING_PARAMS_ERROR));

        verifyNoInteractions(service);
    }

    @ParameterizedTest
    @ValueSource(strings = {"withinTimeframe", "validExpiryDate"})
    void serviceExceptionReturnsStandardErrorAndClearsMdc(String endpoint) throws Exception {
        when(service.withinTimeFrame(START_DATE, INTERVAL_DAYS)).thenThrow(new IllegalStateException("ORDS unavailable"));
        when(service.validateExpiryDate(START_DATE, INTERVAL_DAYS)).thenThrow(new IllegalStateException("ORDS unavailable"));
        MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "test");

        mockMvc.perform(get("/api/validation/" + endpoint)
                .param("startDate", START_DATE).param("intervalDays", "7"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error.httpStatus").value(500))
                .andExpect(jsonPath("$.error.message").value(DigitalFormsConstants.UNKNOWN_ERROR));

        Assertions.assertNull(MDC.get(DigitalFormsConstants.REQUEST_ENDPOINT));
    }
}