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

import static api.ErrorMessages.apiErrorMessage;

public class GetPaymentStatus {
    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    String endpointUrl;
    int responseStatusCode;

    public GetPaymentStatus() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }


    @Given("the user has access to the Payment Status GET endpoint")
    public void theUserHasAccessToThePaymentStatusGETEndpoint() {
    }

    @When("the user submits a GET Payment Request for notice number {string} CorrelationId {string}")
    public void theUserSubmitsAGETPaymentRequestForNoticeNumberCorrelationId(String noticeNumber, String correlationId) {
        endpointUrl = generateEndpoint.Payment(VerbType.GET, noticeNumber, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
    }


    @Then("the user should get a response having attributes PaymentCardType {string} PaymentAmount {string} ReceiptNumber {string} PaymentDate {string}")
    public void theUserShouldGetAResponseHavingAttributesPaymentCardTypePaymentAmountReceiptNumberPaymentDate(String paymentCardType, String paymentAmount, String receiptNumber, String paymentDate) {
        Assert.assertEquals(responseStatusCode, 200, apiErrorMessage(responseStatusCode));


        jsonData = apiUtil.getAttributeValuesInNestedHashMap("data");
        data = jsonData.get("transactionInfo");

        String paymentCardTypeActual = data.get("paymentCardType");
        String paymentAmountActual = data.get("paymentAmount");
        String receiptNumberActual = data.get("receiptNumberTxt");
        String paymentDateActual = data.get("paymentDate");

        softAssert.assertEquals(paymentCardTypeActual, paymentCardType, "Payment Card Type mismatch.");
        softAssert.assertEquals(paymentAmountActual, paymentAmount, "Payment Amount mismatch.");
        softAssert.assertEquals(receiptNumberActual, receiptNumber, "Receipt Number mismatch.");
        softAssert.assertEquals(paymentDateActual, paymentDate, "Payment Date mismatch.");

        softAssert.assertAll();
    }
}
