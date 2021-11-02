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

public class PatchApplicationFormData {

    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    String endpointUrl;
    int responseStatusCode;
    String formType, guid, correlationId;
    String email, fax, fName, formData, manualEntry, noticeSubject, phone, presentationType, reviewRoleType, sName, surname;
    String email_updated, fax_updated, fName_updated, formData_updated, manualEntry_updated, noticeSubject_updated, phone_updated, presentationType_updated, reviewRoleType_updated, sName_updated, surname_updated;

    public PatchApplicationFormData() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }


    @Given("the database has a record for GUID {string} and CorrelationId {string}")
    public void theDatabaseHasARecordForGUIDAndCorrelationId(String guid, String correlationId) throws Exception {
        endpointUrl = generateEndpoint.ApplicationForm(VerbType.GET, null, guid, null, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
        Assert.assertEquals(responseStatusCode, 200, "The record to be updated doesn't exist in database.");
        this.guid = guid;
        this.correlationId = correlationId;
    }


    @When("the user submits a PATCH Application Form Data request with parameters FormType {string} GUID {string} Correlation ID {string}")
    public void theUserSubmitsAPATCHApplicationFormDataRequestWithParametersFormTypeGUIDCorrelationID(String formType, String guid, String correlationId) throws Exception {
        this.formType = formType;
        endpointUrl = generateEndpoint.ApplicationForm(VerbType.PATCH, formType, guid, null, correlationId);
    }


    @And("PATCH form data having EMail {string} Fax {string} FirstName {string} FormData {string} ManualEntry {string} NoticeSubject {string} Phone {string} PresentationType {string} ReviewRoleType {string} SecondName {string} Surname {string}")
    public void patchFormDataHavingEMailFaxFirstNameFormDataManualEntryNoticeSubjectPhonePresentationTypeReviewRoleTypeSecondNameSurname(String email, String fax, String fName, String formData, String manualEntry, String noticeSubject, String phone, String presentationType, String reviewRoleType, String sName, String surname) {
        this.email = email;
        this.fax = fax;
        this.fName = fName;
        this.formData = formData;
        this.manualEntry = manualEntry;
        this.noticeSubject = noticeSubject;
        this.phone = phone;
        this.presentationType = presentationType;
        this.reviewRoleType = reviewRoleType;
        this.sName = sName;
        this.surname = surname;

        email_updated = "updated_" + email;
        fax_updated = "9999999999";
        fName_updated = fName + "_updated";
        formData_updated = formData + "_updated";
        manualEntry_updated = manualEntry;
        noticeSubject_updated = noticeSubject;
        phone_updated = "9999999999";
        presentationType_updated = presentationType;
        reviewRoleType_updated = reviewRoleType;
        sName_updated = sName + "_updated";
        surname_updated = surname + "_updated";

        String jsonBody = "{\n" +
                "    \"applicationInfo\": {\n" +
                "        \"email\": \"" + email_updated + "\",\n" +
                "        \"faxNo\": \"" + fax_updated + "\",\n" +
                "        \"firstGivenNm\": \"" + fName_updated + "\",\n" +
                "        \"formData\": \"" + formData_updated + "\",\n" +
//                "        \"manualEntryYN\": \"" + manualEntry_updated + "\",\n" +
                "        \"noticeSubjectCd\": \"" + noticeSubject_updated + "\",\n" +
                "        \"phoneNo\": \"" + phone_updated + "\",\n" +
                "        \"presentationTypeCd\": \"" + presentationType + "\",\n" +
                "        \"reviewRoleTypeCd\": \"" + reviewRoleType + "\",\n" +
                "        \"secondGivenNm\": \"" + sName_updated + "\",\n" +
                "        \"surnameNm\": \"" + surname_updated + "\"\n" +
                "    }\n" +
                "}";

        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername, GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch().getStatusCode();
    }


    @Then("the user should get a successful response from PATCH request and the record should be updated in the database")
    public void theUserShouldGetASuccessfulResponseFromPATCHRequestAndTheRecordShouldBeUpdatedInTheDatabase() throws Exception {
        Assert.assertEquals(responseStatusCode, 200, "Failed to update the database record using PATCH request");

        endpointUrl = generateEndpoint.ApplicationForm(VerbType.GET, null, guid, null, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
        Assert.assertEquals(responseStatusCode, 200, "Failed to fetch the record from database using the GET request.");
        jsonData = apiUtil.getAttributeValuesInNestedHashMap("data");
        data = jsonData.get("applicationInfo");

        softAssert.assertEquals(data.get("firstGivenNm"), fName_updated, "First Name mismatch.");
        softAssert.assertEquals(data.get("secondGivenNm"), sName_updated, "Second Name mismatch.");
        softAssert.assertEquals(data.get("surnameNm"), surname_updated, "Surname mismatch.");
        softAssert.assertEquals(data.get("phoneNo"), phone_updated, "Phone mismatch.");
        softAssert.assertEquals(data.get("faxNo"), fax_updated, "Fax mismatch.");
        softAssert.assertEquals(data.get("email"), email_updated, "Email mismatch.");
        //softAssert.assertEquals(data.get("formData"),formData_updated,"Form data mismatch.");
        //softAssert.assertEquals(data.get("manualEntryYN"),manualEntry_updated,"Manual Entry mismatch.");
        softAssert.assertAll();

    }


    @And("the db record should be reverted to the original state")
    public void theDbRecordShouldBeRevertedToTheOriginalState() throws Exception {
        String jsonBody = "{\n" +
                "    \"applicationInfo\": {\n" +
                "        \"email\": \"" + email + "\",\n" +
                "        \"faxNo\": \"" + fax + "\",\n" +
                "        \"firstGivenNm\": \"" + fName + "\",\n" +
                "        \"formData\": \"" + formData + "\",\n" +
//                "        \"manualEntryYN\": \"" + manualEntry + "\",\n" +
                "        \"noticeSubjectCd\": \"" + noticeSubject + "\",\n" +
                "        \"phoneNo\": \"" + phone + "\",\n" +
                "        \"presentationTypeCd\": \"" + presentationType + "\",\n" +
                "        \"reviewRoleTypeCd\": \"" + reviewRoleType + "\",\n" +
                "        \"secondGivenNm\": \"" + sName + "\",\n" +
                "        \"surnameNm\": \"" + surname + "\"\n" +
                "    }\n" +
                "}";

        endpointUrl = generateEndpoint.ApplicationForm(VerbType.PATCH, formType, guid, null, correlationId);
        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername, GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch().getStatusCode();
        Assert.assertEquals(responseStatusCode, 200, "Failed to revert the database record using PATCH request");
    }

}
