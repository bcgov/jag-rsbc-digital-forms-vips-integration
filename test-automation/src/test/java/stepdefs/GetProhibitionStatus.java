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

public class GetProhibitionStatus {
    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    String endpointUrl;
    int responseStatusCode;

    public GetProhibitionStatus() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
        jsonData = new HashMap<>();
        data = new HashMap<>();
    }

    @Given("the user has access to GET Prohibition Status endpoint")
    public void theUserHasAccessToGETProhibitionStatusEndpoint() {
    }


    @When("the user submits a Get Prohibition Status request with parameters Notice Number {string} Correlation ID {string}")
    public void theUserSubmitsAGetProhibitionStatusRequestWithParametersNoticeNumberCorrelationID(String noticeNumber, String correlationId) {
        endpointUrl = generateEndpoint.Query(VerbType.GET, noticeNumber, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
    }


    @Then("the user should get a response having attributes NoticeType {string} EffectiveDate {string} ReviewFormSubmitted {string} ReviewCreated {string} OriginalCause {string} Surname {string} DriverLicenceSeized {string} ReceiptNumberText {string} ReviewStartDateTime {string} ReviewEndDateTime {string}")
    public void theUserShouldGetAResponseHavingAttributesNoticeTypeEffectiveDateReviewFormSubmittedReviewCreatedOriginalCauseSurnameDriverLicenceSeizedReceiptNumberTextReviewStartDateTimeReviewEndDateTime(String noticeType, String effectiveDate, String reviewFormSubmitted, String reviewCreated, String originalCause, String surname, String driverLicenceSeized, String receiptNumberText, String reviewStartDt, String reviewEndDt) {
        Assert.assertEquals(responseStatusCode, 200);

        jsonData = apiUtil.getAttributeValuesInNestedHashMap("data");
        data = jsonData.get("status");

        String noticeTypeActual = data.get("noticeTypeCd");
        String effectiveDateActual = data.get("effectiveDt");
        String reviewFormSubmittedActual = data.get("reviewFormSubmittedYn");
        String reviewCreatedActual = data.get("reviewCreatedYn");
        String originalCauseActual = data.get("originalCause");
        String surnameActual = data.get("surnameNm");
        String driverLicenceSeizedActual = data.get("driverLicenceSeizedYn");
        String receiptNumberTextActual = data.get("receiptNumberTxt");
        String reviewStartDateTimeActual = data.get("reviewStartDtm");
        String reviewEndDateTimeActual = data.get("reviewEndDtm");

        softAssert.assertEquals(noticeTypeActual, noticeType, "Notice Type mismatch.");
        softAssert.assertEquals(effectiveDateActual, effectiveDate, "Effective Date mismatch.");
        softAssert.assertEquals(reviewFormSubmittedActual, reviewFormSubmitted, "Review Form Submitted mismatch.");
        softAssert.assertEquals(reviewCreatedActual, reviewCreated, "Review Created mismatch.");
        softAssert.assertEquals(originalCauseActual, originalCause, "Original Cause mismatch.");
        softAssert.assertEquals(surnameActual, surname, "Surname mismatch.");
        softAssert.assertEquals(driverLicenceSeizedActual, driverLicenceSeized, "Driver License Seized mismatch.");
        softAssert.assertEquals(receiptNumberTextActual, isNull(receiptNumberText), "Receipt Number Text mismatch.");
        softAssert.assertEquals(reviewStartDateTimeActual, isNull(reviewStartDt), "Review Start Date time mismatch.");
        softAssert.assertEquals(reviewEndDateTimeActual, isNull(reviewEndDt), "Review End Date time mismatch.");
        softAssert.assertAll();
    }

    private String isNull(String stringValue)
    {
        if(stringValue.toUpperCase().equals("NULL")) return null;
        else return stringValue;
    }
}
