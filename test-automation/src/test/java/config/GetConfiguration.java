package config;

public class GetConfiguration {
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
    public String getOrdsBaseUrl()
    {
        return System.getenv("ordsBaseUrl");
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
