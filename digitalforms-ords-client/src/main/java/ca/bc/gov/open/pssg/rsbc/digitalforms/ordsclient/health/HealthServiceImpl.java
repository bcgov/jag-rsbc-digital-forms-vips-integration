package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.health;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.HealthApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.HealthOrdsResponse;

/**
 * Health Service Implementation using ORDS api
 *
 * @author sivakaruna
 *
 */
public class HealthServiceImpl implements HealthService {

	private final HealthApi healthApi;

	public HealthServiceImpl(HealthApi healthApi) {
		this.healthApi = healthApi;
	}

	/**
	 * health check for the endpoint
	 *
	 * @return
	 * @throws ApiException
	 */
	@Override
	public HealthResponse health() throws ApiException {

		HealthOrdsResponse response = healthApi.health();
		return HealthResponse.successResponse(response.getAppid(), response.getMethod(), response.getStatus(),
				response.getHost(), response.getInstance());
	}
}
