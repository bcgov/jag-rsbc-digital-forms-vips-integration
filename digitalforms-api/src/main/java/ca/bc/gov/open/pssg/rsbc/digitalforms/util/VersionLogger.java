package ca.bc.gov.open.pssg.rsbc.digitalforms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VersionLogger implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(VersionLogger.class);

    @Value("${app.version}")
    private String appVersion;

    @Override
    public void run(String... args) {
        logger.info("🚀 VIPS Integration API, Version: {}", appVersion);
    }
}
