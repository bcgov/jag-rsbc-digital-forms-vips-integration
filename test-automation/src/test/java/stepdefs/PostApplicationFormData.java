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

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostApplicationFormData extends BaseUtil {
    GenerateEndpoint generateEndpoint;
    DatabaseConnection databaseConnection;
    BaseUtil baseUtil;
    String selectQuery, deleteQuery, endpointUrl;
    int responseStatusCode;


    public PostApplicationFormData(BaseUtil baseUtil) {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        this.baseUtil = baseUtil;
        this.databaseConnection = baseUtil.databaseConnection;
    }


    @Given("the database doesn't have any record for FormType {string} FirstName {string} SecondName {string} LastName {string} CorrelationId {string}")
    public void theDatabaseDoesnTHaveAnyRecordForFormTypeFirstNameSecondNameLastNameCorrelationId(String formType, String fName, String sName, String surname, String correlationId) throws Exception {
        // Verify there is no record in the DB, if there is, delete it.
        selectQuery = "select * from rsdfrm_digital_forms where notice_type_cd='" + formType + "' and first_given_nm='" + fName + "' and second_given_nm='" + sName + "' and surname_nm='" + surname + "' and correlation_guid='" + correlationId + "'";
        deleteQuery = "delete from rsdfrm_digital_forms where notice_type_cd='" + formType + "' and first_given_nm='" + fName + "' and second_given_nm='" + sName + "' and surname_nm='" + surname + "' and correlation_guid='" + correlationId + "'";

        int rowCount = databaseConnection.getRowCount(selectQuery);

        if (rowCount > 0) {
            databaseConnection.executeDeleteQuery(deleteQuery);
            rowCount = databaseConnection.getRowCount(selectQuery);
        }

        Assert.assertEquals(rowCount, 0, "There should be no record in the database for SQL Query " + selectQuery);

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

        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).body(jsonBody).when().post(endpointUrl).getStatusCode();
    }

    @Then("the user should get a successful response")
    public void theUserShouldGetASuccessfulResponse() {
        Assert.assertEquals(responseStatusCode, 201, "The response status should be Created:201");
    }

    @And("the record should exist in the database")
    public void theRecordShouldExistInTheDatabase() throws Exception {
        int rowCount = databaseConnection.getRowCount(selectQuery);

        Assert.assertEquals(rowCount, 1, "There should be one record in the database for SQL Query " + selectQuery);
    }

}
