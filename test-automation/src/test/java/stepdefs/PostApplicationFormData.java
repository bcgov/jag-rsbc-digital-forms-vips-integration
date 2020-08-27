package stepdefs;

import api.ErrorMessages;
import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostApplicationFormData {
    GenerateEndpoint generateEndpoint;
    Response response;
    String endpointUrl;
    int responseStatusCode;


    public PostApplicationFormData() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
    }


    @Given("the database doesn't have any record for ProhibitionId {string}")
    public void theDatabaseDoesnTHaveAnyRecordForProhibitionId(String prohibitionId) {
        RestAssured.baseURI = GlobalVariables.ordsBaseUrl;
        Response response = given().auth().preemptive().basic(GlobalVariables.ordsApiUsername,GlobalVariables.ordsApiPassword).header("Content-Type", "application/json").delete("digitalForm/prohibition/" + prohibitionId + "/123");
        responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode,200, ErrorMessages.apiErrorMessage(responseStatusCode));
    }


    @When("the user submits a POST Application Form Data request with parameters FormType {string} NoticeNumber {string} Correlation ID {string}")
    public void theUserSubmitsAPOSTApplicationFormDataRequestWithParametersFormTypeNoticeNumberCorrelationID(String formType, String noticeNumber, String correlationId) throws Exception {
        endpointUrl = generateEndpoint.ApplicationForm(VerbType.POST,formType,"",noticeNumber,correlationId);
    }

    @And("form data having EMail {string} Fax {string} FirstName {string} FormData {string} ManualEntry {string} NoticeSubject {string} Phone {string} PresentationType {string} ReviewRoleType {string} SecondName {string} Surname {string}")
    public void formDataHavingEMailFaxFirstNameFormDataManualEntryNoticeSubjectPhonePresentationTypeReviewRoleTypeSecondNameSurname(String email, String fax, String fName, String formData, String manualEntry, String noticeSubject, String phone, String presentationType, String reviewRoleType, String sName, String surname) {
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

        response = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).body(jsonBody).when().post(endpointUrl);
        responseStatusCode = response.getStatusCode();
    }

    @Then("the user should get a successful response")
    public void theUserShouldGetASuccessfulResponse() {
        Assert.assertEquals(responseStatusCode, 201, "The response status should be Created:201");
    }


}
