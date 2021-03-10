package stepdefs;

import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostSelectedReviewTimeslot {

    GenerateEndpoint generateEndpoint;
    Response response;
    String endpointUrl;
    int responseStatusCode;

    public PostSelectedReviewTimeslot() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
    }

    @Given("the user has access to the Review Scheduling POST endpoint")
    public void theUserHasAccessToTheReviewSchedulingPOSTEndpoint() {
    }

    @When("the user submits a POST Review Timeslot Request for NoticeNumber {string} CorrelationId {string} ReviewStartDate {string} ReviewEndDate {string}")
    public void theUserSubmitsAPOSTReviewTimeslotRequestForNoticeNumberCorrelationIdReviewStartDateReviewEndDate(String noticeNumber, String correlationId, String reviewStartDate, String reviewEndDate) throws Exception {
        endpointUrl = generateEndpoint.ReviewScheduling(VerbType.POST, noticeNumber, "", "", "", correlationId);

        String jsonBody = "{\n" +
                "  \"timeSlot\": {\n" +
                "    \"reviewEndDtm\": \""+reviewStartDate+"\",\n" +
                "    \"reviewStartDtm\": \""+reviewEndDate+"\"\n" +
                "  }\n" +
                "}";

        response = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).body(jsonBody).when().post(endpointUrl);
        responseStatusCode = response.getStatusCode();
    }

    @Then("the user should get a successful response from Review Scheduling POST endpoint")
    public void theUserShouldGetASuccessfulResponseFromReviewSchedulingPOSTEndpoint() {
//        Assert.assertEquals(responseStatusCode, 201, "The response status should be Created:201");
    }
}
