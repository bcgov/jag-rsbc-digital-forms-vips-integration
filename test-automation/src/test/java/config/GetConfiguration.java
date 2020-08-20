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
        return properties.getProperty("digitalFormsBaseUrl");
    }
    public String getApiUsername()
    {
        return System.getenv("digitalFormsApi-username");
    }
    public String getApiPassword()
    {
        return System.getenv("digitalFormsApi-password");
    }
    public String getDbUsername()
    {
        return System.getenv("db-dev-username");
    }
    public String getDbPassword()
    {
        return System.getenv("db-dev-password");
    }
    public String getTestEnvironment()
    {
        return System.getenv("automation-env");
    }
    public String getConnectionString(){ return System.getenv("db-dev-connectionString");}
    public String getOrdsApiUsername()
    {
        return System.getenv("OrdsApi-username");
    }
    public String getOrdsApiPassword()
    {
        return System.getenv("OrdsApi-password");
    }

}
