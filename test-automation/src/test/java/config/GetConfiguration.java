package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfiguration {

    Properties properties = new Properties();
    InputStream inputStream = new FileInputStream("config.properties");

    public GetConfiguration() throws IOException {
        properties.load(inputStream);
    }

    public String getApiUsername()
    {
        return properties.getProperty("apiUsername");
    }
    public String getApiPassword()
    {
        return properties.getProperty("apiPassword");
    }
    public String getDbUsername()
    {
        return properties.getProperty("dbUsername");
    }
    public String getDbPassword()
    {
        return properties.getProperty("dbPassword");
    }
    public String getTestEnvironment()
    {
        return properties.getProperty("environment");
    }
    public String getDigitalFormsUrl()
    {
        return properties.getProperty("digitalFormsBaseUrl");
    }
}
