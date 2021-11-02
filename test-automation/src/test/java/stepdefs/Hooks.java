package stepdefs;

import config.GetConfiguration;
import config.GlobalVariables;
import io.cucumber.java.Before;

public class Hooks{
    GetConfiguration configuration;

    public Hooks() {
        configuration = new GetConfiguration();
    }

    @Before
    public void beforeScenario() {
        GlobalVariables.apiBaseUrl = configuration.getDigitalFormsUrl();
        GlobalVariables.apiUsername = configuration.getApiUsername();
        GlobalVariables.apiPassword = configuration.getApiPassword();
        GlobalVariables.ordsBaseUrl = configuration.getOrdsBaseUrl();
        GlobalVariables.ordsApiUsername = configuration.getOrdsApiUsername();
        GlobalVariables.ordsApiPassword = configuration.getOrdsApiPassword();
    }


}
