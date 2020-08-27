package stepdefs;

import api.ApiUtil;
import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PatchPaymentServiceController {

    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    SoftAssert softAssert;
    int responseStatusCode;
    String endpointUrl;
    String noticeNumber, correlationId;
    String paymentAmount, paymentCardType, paymentDate, receiptNumberTxt;
    String paymentAmount_updated, paymentCardType_updated, paymentDate_updated, receiptNumberTxt_updated;

    public PatchPaymentServiceController() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }

    @Given("the database has a record for NoticeNumber {string} and CorrelationId {string}")
    public void theDatabaseHasARecordForNoticeNumberAndCorrelationId(String noticeNumber, String correlationId) {
        endpointUrl = generateEndpoint.Payment(VerbType.GET, noticeNumber, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
        Assert.assertEquals(responseStatusCode, 200, "The record to be updated doesn't exist in database.");
        this.noticeNumber = noticeNumber;
        this.correlationId = correlationId;
    }


    @When("the user submits a PATCH Payment request with parameters NoticeNumber {string} Correlation ID {string}")
    public void theUserSubmitsAPATCHPaymentRequestWithParametersNoticeNumberCorrelationID(String noticeNumber, String correlationId) {
        endpointUrl = generateEndpoint.Payment(VerbType.PATCH,noticeNumber,correlationId);
    }

    @And("PATCH form data having PaymentAmount {string} PaymentCardType {string} PaymentDate {string} ReceiptNumberTxt {string}")
    public void patchFormDataHavingPaymentAmountPaymentCardTypePaymentDateReceiptNumberTxt(String paymentAmount, String paymentCardType, String paymentDate, String receiptNumberTxt) {
        this.paymentAmount = paymentAmount;
        this.paymentCardType = paymentCardType;
        this.paymentDate = paymentDate;
        this.receiptNumberTxt = receiptNumberTxt;

        paymentAmount_updated = "9999.99";
        paymentCardType_updated = "MASTER CARD";
        paymentDate_updated = "2020-01-01 00:00:00 -07:00";
        receiptNumberTxt_updated = receiptNumberTxt + "_updated";

        String jsonBody = "{\n" +
                "    \"transactionInfo\": {\n" +
                "        \"paymentAmount\": \""+paymentAmount_updated+"\",\n" +
                "        \"paymentCardType\": \""+paymentCardType_updated+"\",\n" +
                "        \"paymentDate\": \""+paymentDate_updated+"\",\n" +
                "        \"receiptNumberTxt\": \""+receiptNumberTxt_updated+"\"\n" +
                "    }\n" +
                "}";

        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch().getStatusCode();

    }

    @Then("the user should get a successful response from PATCH Payment request and the record should be updated in the database")
    public void theUserShouldGetASuccessfulResponseFromPATCHPaymentRequestAndTheRecordShouldBeUpdatedInTheDatabase() {
        Assert.assertEquals(responseStatusCode, 200, "The response status should be OK:200");

        endpointUrl = generateEndpoint.Payment(VerbType.GET, noticeNumber, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
        Assert.assertEquals(responseStatusCode, 200, "Failed to update the database record using PATCH request.");

        jsonData = apiUtil.getAttributeValuesInNestedHashMap("data");
        data = jsonData.get("transactionInfo");

        softAssert.assertEquals(data.get("paymentAmount"),paymentAmount_updated,"Payment Amount mismatch.");
        softAssert.assertEquals(data.get("paymentCardType"),paymentCardType_updated,"Payment Card Type mismatch.");
        softAssert.assertEquals(data.get("receiptNumberTxt"),receiptNumberTxt_updated,"Receipt Number Text mismatch.");
//        softAssert.assertEquals(data.get("paymentDate"),paymentDate_updated,"Payment Date mismatch.");
        softAssert.assertAll();
    }

    @And("the Payment db record should be reverted to the original state")
    public void thePaymentDbRecordShouldBeRevertedToTheOriginalState() {
        endpointUrl = generateEndpoint.Payment(VerbType.PATCH,noticeNumber,correlationId);
        String jsonBody = "{\n" +
                "    \"transactionInfo\": {\n" +
                "        \"paymentAmount\": \""+paymentAmount+"\",\n" +
                "        \"paymentCardType\": \""+paymentCardType+"\",\n" +
                "        \"paymentDate\": \""+paymentDate+"\",\n" +
                "        \"receiptNumberTxt\": \""+receiptNumberTxt+"\"\n" +
                "    }\n" +
                "}";

        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch().getStatusCode();
        Assert.assertEquals(responseStatusCode, 200, "Failed to revert the database record using PATCH request");
    }

}
