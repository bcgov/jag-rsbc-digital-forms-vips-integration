package stepdefs;

import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;

public class GetAvailableReviewTimeSlots {

    GenerateEndpoint generateEndpoint;
    SoftAssert softAssert;
    Response response;
    HashMap<String, ArrayList<HashMap<String,String>>> responseData;
    ArrayList<HashMap<String,String>> timeSlots;
    HashMap<String,String> timeSlotsData;
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
        response = RestAssured.given().auth().preemptive().basic(GlobalVariables.apiUsername, GlobalVariables.apiPassword).get(endpointUrl);
        responseStatusCode = response.getStatusCode();
    }

    @Then("the user should get a response having attributes ReviewStartDate {string} ReviewEndDate {string}")
    public void theUserShouldGetAResponseHavingAttributesReviewStartDateReviewEndDate(String reviewStartDate, String reviewEndDate) {
        Assert.assertEquals(responseStatusCode, 200);

        responseData = response.jsonPath().get("data");
        timeSlots = responseData.get("timeSlots");
        Assert.assertEquals(timeSlots.size(),1,"Only one timeslot should be available.");

        timeSlotsData = timeSlots.get(0);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), reviewStartDate, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), reviewEndDate, "Review End Datetime mismatch.");
        softAssert.assertAll();
    }

    @Then("the user should get a response having attributes Review StartDate {string} EndDate {string} StartDate {string} EndDate {string} StartDate {string} EndDate {string} StartDate {string} EndDate {string} StartDate {string} EndDate {string}")
    public void theUserShouldGetAResponseHavingAttributesReviewStartDateEndDateStartDateEndDateStartDateEndDateStartDateEndDateStartDateEndDate(String sdate1, String eDate1, String sdate2, String eDate2, String sdate3, String eDate3, String sdate4, String eDate4, String sdate5, String eDate5) {
        Assert.assertEquals(responseStatusCode, 200);

        responseData = response.jsonPath().get("data");
        timeSlots = responseData.get("timeSlots");
        Assert.assertEquals(timeSlots.size(),5,"Five timeslots should be available.");

        timeSlotsData = timeSlots.get(0);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate1, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate1, "Review End Datetime mismatch.");

        timeSlotsData = timeSlots.get(1);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate2, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate2, "Review End Datetime mismatch.");

        timeSlotsData = timeSlots.get(2);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate3, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate3, "Review End Datetime mismatch.");

        timeSlotsData = timeSlots.get(3);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate4, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate4, "Review End Datetime mismatch.");

        timeSlotsData = timeSlots.get(4);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate5, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate5, "Review End Datetime mismatch.");
        softAssert.assertAll();
    }

    @Then("the user should get a response having attributes Review StartDate {string} EndDate {string} StartDate {string} EndDate {string} StartDate {string} EndDate {string}")
    public void theUserShouldGetAResponseHavingAttributesReviewStartDateEndDateStartDateEndDateStartDateEndDate(String sdate1, String eDate1, String sdate2, String eDate2, String sdate3, String eDate3) {
        Assert.assertEquals(responseStatusCode, 200);

        responseData = response.jsonPath().get("data");
        timeSlots = responseData.get("timeSlots");
        Assert.assertEquals(timeSlots.size(),3,"Three timeslots should be available.");

        timeSlotsData = timeSlots.get(0);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate1, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate1, "Review End Datetime mismatch.");

        timeSlotsData = timeSlots.get(1);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate2, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate2, "Review End Datetime mismatch.");

        timeSlotsData = timeSlots.get(2);
        softAssert.assertEquals(timeSlotsData.get("reviewStartDtm"), sdate3, "Review Start Datetime mismatch.");
        softAssert.assertEquals(timeSlotsData.get("reviewEndDtm"), eDate3, "Review End Datetime mismatch.");

        softAssert.assertAll();
    }
}
