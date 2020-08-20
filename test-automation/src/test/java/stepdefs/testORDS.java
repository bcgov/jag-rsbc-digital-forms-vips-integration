package stepdefs;

import api.ApiUtil;
import config.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class testORDS {

    ApiUtil apiUtil;
    int status;

    @Given("the user can connect to ORDS web health")
    public void theUserCanConnectToORDSWebHealth() {
        apiUtil = new ApiUtil("https://wsgw.dev.jag.gov.bc.ca/rsdfrm/ords/deva/rsdfrmords/web/health");
    }

    @When("the user hits the api")
    public void theUserHitsTheApi() {
        status = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.ordsApiUsername,GlobalVariables.ordsApiPassword);
    }

    @Then("the user gets success")
    public void theUserGetsSuccess() {
        Assert.assertEquals(status,200);
    }
}
