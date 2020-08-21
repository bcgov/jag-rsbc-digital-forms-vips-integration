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

    public String getDigitalFormsUrl()
    {
        return System.getenv("digitalFormsBaseUrl");
    }
    public String getApiUsername()
    {
        return System.getenv("digitalFormsApiUsername");
    }
    public String getApiPassword()
    {
        return System.getenv("digitalFormsApiPassword");
    }
    public String getDbUsername()
    {
        return System.getenv("dbUsername");
    }
    public String getDbPassword()
    {
        return System.getenv("dbPassword");
    }
    public String getTestEnvironment()
    {
        return System.getenv("automationEnv");
    }
    public String getConnectionString()
    {
        return System.getenv("dbConnectionString");
    }
    public String getOrdsApiUsername()
    {
        return System.getenv("ordsApiUsername");
    }
    public String getOrdsApiPassword()
    {
        return System.getenv("ordsApiPassword");
    }

}
