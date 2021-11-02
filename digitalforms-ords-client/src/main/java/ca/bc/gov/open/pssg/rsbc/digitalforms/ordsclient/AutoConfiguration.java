package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.ApplicationApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.AvailableTimeSlotsApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.HealthApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.PaymentApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiClient;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.health.HealthService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.health.HealthServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.ReviewService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.ReviewServiceImpl;

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
	public HealthApi digitalFormsHealthApi(ApiClient apiClient) {
		return new HealthApi(apiClient);
	}

	@Bean
	public HealthService digitalFormsHealthService(HealthApi digitalFormsHealthApi) {
		return new HealthServiceImpl(digitalFormsHealthApi);
	}

	@Bean
	public ApplicationApi digitalFormsApplicationApi(ApiClient apiClient) {
		return new ApplicationApi(apiClient);
	}

	@Bean
	public ApplicationService digitalFormsApplicationService(ApplicationApi digitalFormsApplicationApi) {
		return new ApplicationServiceImpl(digitalFormsApplicationApi);
	}
	
	@Bean
	public PaymentApi digitalFormsPaymentApi(ApiClient apiClient) {
		return new PaymentApi(apiClient);
	}

	@Bean
	public PaymentService digitalFormsPaymentService(PaymentApi digitalFormsPaymentApi) {
		return new PaymentServiceImpl(digitalFormsPaymentApi);
	}
	
	@Bean
	public AvailableTimeSlotsApi digitalFormsReviewServiceApi(ApiClient apiClient) {
		return new AvailableTimeSlotsApi(apiClient);
	}
	
	@Bean
	public ReviewService digitalFormsReviewService(AvailableTimeSlotsApi availableTimeSlotsApi) {
		return new ReviewServiceImpl(availableTimeSlotsApi);
	}

}
