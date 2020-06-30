package ca.bc.gov.open.jag.digitalforms.ordsclient;

import ca.bc.gov.open.jag.digitalforms.ordsclient.api.HealthApi;
import ca.bc.gov.open.jag.digitalforms.ordsclient.api.handler.ApiClient;
import ca.bc.gov.open.jag.digitalforms.ordsclient.health.HealthService;
import ca.bc.gov.open.jag.digitalforms.ordsclient.health.HealthServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Auto configuration properties.
 * 
 * @author sivakaruna
 *
 */
@Configuration
@EnableConfigurationProperties(DigitalFormsClientProperties.class)
public class AutoConfiguration {

	private final DigitalFormsClientProperties digitalFormsClientProperties;

	public AutoConfiguration(DigitalFormsClientProperties digitalFormsClientProperties) {
		this.digitalFormsClientProperties = digitalFormsClientProperties;
	}

	@Bean(name = "digitalFormsApiClient")
	public ApiClient apiClient() {
		ApiClient apiClient = new ApiClient();

		apiClient.setBasePath(digitalFormsClientProperties.getBasePath());

		if (StringUtils.isNotBlank(digitalFormsClientProperties.getUsername()))
			apiClient.setUsername(digitalFormsClientProperties.getUsername());

		if (StringUtils.isNotBlank(digitalFormsClientProperties.getPassword()))
			apiClient.setPassword(digitalFormsClientProperties.getPassword());

		return apiClient;
	}

	@Bean
	public HealthApi healthApi(ApiClient apiClient) {
		return new HealthApi(apiClient);
	}

	@Bean
	public HealthService healthService(HealthApi healthApi) {
		return new HealthServiceImpl(healthApi);
	}

}
