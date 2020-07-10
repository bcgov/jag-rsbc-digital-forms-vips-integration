package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;


/**
 * Collection of services for applications.
 *
 * @author sivakaruna
 */
public interface ApplicationService {

	ApplicationResponse getApplication(String formGuid);

	ApplicationResponse postApplication(ApplicationInfo applicationInfo);

	ApplicationResponse patchApplication(String formGuid, ApplicationInfo applicationInfo);

}
