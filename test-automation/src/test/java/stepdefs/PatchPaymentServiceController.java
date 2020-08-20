package stepdefs;

import api.GenerateEndpoint;
import api.VerbType;
import base.BaseUtil;
import config.GlobalVariables;
import database.DatabaseConnection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PatchPaymentServiceController {

    GenerateEndpoint generateEndpoint;
    DatabaseConnection databaseConnection;
    BaseUtil baseUtil;
    SoftAssert softAssert;
    String selectQuery, endpointUrl;
    int responseStatusCode;
    String guid;
    String paymentAmount, paymentCardType, paymentDate, receiptNumberTxt;
    String paymentAmount_updated, paymentCardType_updated, paymentDate_updated, receiptNumberTxt_updated;

    public PatchPaymentServiceController(BaseUtil baseUtil) {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        this.baseUtil = baseUtil;
        this.databaseConnection = baseUtil.databaseConnection;
        softAssert = new SoftAssert();
    }

    @Given("the database has a record for FormType {string} GUID {string} CorrelationId {string}")
    public void theDatabaseHasARecordForFormTypeGUIDCorrelationId(String formType, String guid, String correlationId) throws Exception {
        selectQuery = "select * from rsdfrm_digital_forms where notice_type_cd='" + formType + "' and correlation_guid='" + correlationId + "' and form_object_guid='" + guid + "'";

        this.guid = guid;
        int rowCount = databaseConnection.getRowCount(selectQuery);
        Assert.assertEquals(rowCount, 1, "There should be ONE record in the database for SQL Query " + selectQuery);
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
        paymentDate_updated = "2020-01-01";
        receiptNumberTxt_updated = "stepdefs.PatchApplicationFormData.test";

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
    public void theUserShouldGetASuccessfulResponseFromPATCHPaymentRequestAndTheRecordShouldBeUpdatedInTheDatabase() throws Exception {
        Assert.assertEquals(responseStatusCode, 200, "The response status should be OK:200");
        selectQuery = "select * from rsdfrm_digital_forms where form_object_guid='" + guid + "'";

        int rowCount = databaseConnection.getRowCount(selectQuery);
        Assert.assertEquals(rowCount, 1, "There should be ONE record in the database for SQL Query " + selectQuery);

        ResultSet dbResult = databaseConnection.executeSelectQuery(selectQuery);
        dbResult.next();

        softAssert.assertEquals(dbResult.getString("payment_amt"),paymentAmount_updated,"Payment Amount mismatch.");
        softAssert.assertEquals(dbResult.getString("payment_card_type_txt"),paymentCardType_updated,"Payment Card Type mismatch.");
        softAssert.assertEquals(dbResult.getString("receipt_number_txt"),receiptNumberTxt_updated,"Receipt Number Text mismatch.");
        //softAssert.assertEquals(dbResult.getString("payment_dtm"),paymentDate_updated,"Payment Date mismatch.");
        softAssert.assertAll();
    }

    @And("the Payment db record should be reverted to the original state")
    public void thePaymentDbRecordShouldBeRevertedToTheOriginalState() throws SQLException {
        String updateSqlQuery = "update rsdfrm_digital_forms set payment_amt='"+paymentAmount+"', payment_card_type_txt ='"+paymentCardType+"', payment_dtm='"+paymentDate+"', receipt_number_txt='"+receiptNumberTxt+"' where form_object_guid='" + guid + "'";
        int updatedRows = databaseConnection.executeUpdateQuery(updateSqlQuery);
        Assert.assertEquals(updatedRows, 1, "One row should be updated for Query " + updateSqlQuery);
    }
}
