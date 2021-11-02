package stepdefs;

import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class SetupTestData {

    GenerateEndpoint generateEndpoint;
    String formType, noticeNumber,correlationId;

    public SetupTestData() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
    }

    @Given("the database setup is required")
    public void theDatabaseSetupIsRequired() {
    }

    @When("the user setup the Application form data with attributes FormType {string} NoticeNumber {string} CorrelationId {string} EMail {string} Fax {string} FirstName {string} FormData {string} ManualEntry {string} NoticeSubject {string} Phone {string} PresentationType {string} ReviewRoleType {string} SecondName {string} Surname {string}")
    public void theUserSetupTheApplicationFormDataWithAttributesFormTypeNoticeNumberCorrelationIdEMailFaxFirstNameFormDataManualEntryNoticeSubjectPhonePresentationTypeReviewRoleTypeSecondNameSurname(String formType, String noticeNumber, String correlationId, String email, String fax, String fName, String formData, String manualEntry, String noticeSubject, String phone, String presentationType, String reviewRoleType, String sName, String surname) throws Exception {
        this.formType = formType;
        this.noticeNumber = noticeNumber;
        this.correlationId = correlationId;
        String endpointUrl = generateEndpoint.ApplicationForm(VerbType.POST,formType,"",noticeNumber,correlationId);

        String jsonBody = "{\n" +
                "    \"applicationInfo\": {\n" +
                "        \"email\": \"" + email + "\",\n" +
                "        \"faxNo\": \"" + fax + "\",\n" +
                "        \"firstGivenNm\": \"" + fName + "\",\n" +
                "        \"formData\": \"" + formData + "\",\n" +
                "        \"manualEntryYN\": \"" + manualEntry + "\",\n" +
                "        \"noticeSubjectCd\": \"" + noticeSubject + "\",\n" +
                "        \"phoneNo\": \"" + phone + "\",\n" +
                "        \"presentationTypeCd\": \"" + presentationType + "\",\n" +
                "        \"reviewRoleTypeCd\": \"" + reviewRoleType + "\",\n" +
                "        \"secondGivenNm\": \"" + sName + "\",\n" +
                "        \"surnameNm\": \"" + surname + "\"\n" +
                "    }\n" +
                "}";

        Response response = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).body(jsonBody).when().post(endpointUrl);
        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode,201,"POST Application form data failed.");

    }


    @And("the user setup the Payment data with attributes PaymentAmount {string} PaymentCardType {string} PaymentDate {string} ReceiptNumberTxt {string}")
    public void theUserSetupThePaymentDataWithAttributesPaymentAmountPaymentCardTypePaymentDateReceiptNumberTxt(String paymentAmount, String paymentCardType, String paymentDate, String receiptNumberTxt) {
        String endpointUrl = generateEndpoint.Payment(VerbType.PATCH,noticeNumber,correlationId);

        String jsonBody = "{\n" +
                "    \"transactionInfo\": {\n" +
                "        \"paymentAmount\": \""+paymentAmount+"\",\n" +
                "        \"paymentCardType\": \""+paymentCardType+"\",\n" +
                "        \"paymentDate\": \""+paymentDate+"\",\n" +
                "        \"receiptNumberTxt\": \""+receiptNumberTxt+"\"\n" +
                "    }\n" +
                "}";

        Response response = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch();
        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode,200, "PATCH Payment failed");
    }

    @And("the user setup the Review Timeslot data with attributes ReviewStartDateTime {string} ReviewEndDateTime {string}")
    public void theUserSetupTheReviewTimeslotDataWithAttributesReviewStartDateTimeReviewEndDateTime(String reviewStartDateTime, String reviewEndDateTime) throws Exception {
        String endpointUrl = generateEndpoint.ReviewScheduling(VerbType.POST,noticeNumber,null,null,null,correlationId);
        String jsonBody = "{\n" +
                "  \"timeSlot\": {\n" +
                "    \"reviewEndDtm\": \""+reviewEndDateTime+"\",\n" +
                "    \"reviewStartDtm\": \""+reviewStartDateTime+"\"\n" +
                "  }\n" +
                "}";

        Response response = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).body(jsonBody).when().post(endpointUrl);
        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode,200,"POST Review Timeslots failed.");
    }
}
