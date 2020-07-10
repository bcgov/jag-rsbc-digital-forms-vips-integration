package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;


/**
 * Collection of services for applications.
 *
 * @author sivakaruna
 */
public interface ApplicationService {

	ApplicationResponse getApplication(Long noticeNo);

	ApplicationResponse postApplication(ApplicationInfo applicationInfo);

	ApplicationResponse patchApplication(Long noticeNo, ApplicationInfo applicationInfo);

}
