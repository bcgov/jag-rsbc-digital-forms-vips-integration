package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.bc.gov.open.jagvipsclient.validation.VipsValidExpiryDateResponse;
import ca.bc.gov.open.jagvipsclient.validation.VipsValidTimeframeResponse;

@ExtendWith(MockitoExtension.class)
class ValidationServiceImplTests {

    private static final String START_DATE = "2026-10-08";
    private static final BigDecimal INTERVAL_DAYS = new BigDecimal("7");

    @Mock
    private ca.bc.gov.open.jagvipsclient.validation.ValidationService validationService;

    @InjectMocks
    private ValidationServiceImpl service;

    @Test
    void timeframeDelegatesArgumentsAndReturnsClientResponse() {
        VipsValidTimeframeResponse response = mock(VipsValidTimeframeResponse.class);
        when(validationService.getWithinTimeframe(START_DATE, INTERVAL_DAYS)).thenReturn(response);

        assertSame(response, service.withinTimeFrame(START_DATE, INTERVAL_DAYS));
        verify(validationService).getWithinTimeframe(START_DATE, INTERVAL_DAYS);
    }

    @Test
    void expiryDateDelegatesArgumentsAndReturnsClientResponse() {
        VipsValidExpiryDateResponse response = mock(VipsValidExpiryDateResponse.class);
        when(validationService.getValidExpiryDate(START_DATE, INTERVAL_DAYS)).thenReturn(response);

        assertSame(response, service.validateExpiryDate(START_DATE, INTERVAL_DAYS));
        verify(validationService).getValidExpiryDate(START_DATE, INTERVAL_DAYS);
    }

    @Test
    void timeframePropagatesClientFailure() {
        IllegalStateException failure = new IllegalStateException("ORDS unavailable");
        when(validationService.getWithinTimeframe(START_DATE, INTERVAL_DAYS)).thenThrow(failure);

        assertSame(failure, assertThrows(IllegalStateException.class,
                () -> service.withinTimeFrame(START_DATE, INTERVAL_DAYS)));
    }

    @Test
    void expiryDatePropagatesClientFailure() {
        IllegalStateException failure = new IllegalStateException("ORDS unavailable");
        when(validationService.getValidExpiryDate(START_DATE, INTERVAL_DAYS)).thenThrow(failure);

        assertSame(failure, assertThrows(IllegalStateException.class,
                () -> service.validateExpiryDate(START_DATE, INTERVAL_DAYS)));
    }
}