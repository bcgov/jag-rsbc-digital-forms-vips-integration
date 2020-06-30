package ca.bc.gov.open.jag.digitalforms.ordsclient.health;

import ca.bc.gov.open.jag.digitalforms.ordsclient.api.handler.ApiException;

/**
 * Health Service
 *
 * @author sivakaruna
 *
 */
public interface HealthService {

	HealthResponse health() throws ApiException, ApiException;
}
