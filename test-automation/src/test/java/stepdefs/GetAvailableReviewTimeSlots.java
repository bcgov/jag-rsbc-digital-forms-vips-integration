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

public class GetAvailableReviewTimeSlots {

    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    String endpointUrl;
    int responseStatusCode;

    public GetAvailableReviewTimeSlots() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }

    @Given("the user has access to the Review Scheduling GET endpoint")
    public void theUserHasAccessToTheReviewSchedulingGETEndpoint() {
    }

    @When("the user submits a GET Review Scheduling Request for noticeType {string} ReviewType {string} ReviewDate {string} CorrelationId {string}")
    public void theUserSubmitsAGETReviewSchedulingRequestForNoticeTypeReviewTypeReviewDateCorrelationId(String noticeType, String reviewType, String reviewDate, String correlationId) throws Exception {
        endpointUrl = generateEndpoint.ReviewScheduling(VerbType.GET,null,noticeType,reviewType,reviewDate,correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
    }

    @Then("the user should get a response having attributes ReviewStartDate {string} ReviewEndDate {string}")
    public void theUserShouldGetAResponseHavingAttributesReviewStartDateReviewEndDate(String reviewStartDate, String reviewEndDate) {
        Assert.assertEquals(responseStatusCode, 200);
    }
}
