package stepdefs;

import api.ApiUtil;
import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;

public class HealthCheck {
    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    String endpointUrl;
    int responseStatusCode;

    public HealthCheck() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }

    @Given("the user has access to the health check api")
    public void theUserHasAccessToTheHealthCheckApi() {

    }

    @When("the user connects to Digital Forms ping service")
    public void theUserConnectsToDigitalFormsPingService() {
        endpointUrl = generateEndpoint.Utilities(VerbType.GET);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
    }

    @Then("the user should get a successful health status response")
    public void theUserShouldGetASuccessfulHealthStatusResponse() {
        Assert.assertEquals(responseStatusCode, 200);

        HashMap<String, String> jsonData = apiUtil.getAttributeValuesInHashMap("responseMessage");
        String vipsHealthStatus = jsonData.get("VIPS ORDS Health Status");
        String digitalFormsHealthStatus = jsonData.get("DIGITAL FORMS ORDS Health Status");

        softAssert.assertEquals(vipsHealthStatus, "success");
        softAssert.assertEquals(digitalFormsHealthStatus, "success");
        softAssert.assertAll();

    }
}
