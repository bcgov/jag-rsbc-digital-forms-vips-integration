package stepdefs;

import api.ApiUtil;
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

public class PatchApplicationFormData {

    GenerateEndpoint generateEndpoint;
    DatabaseConnection databaseConnection;
    BaseUtil baseUtil;
    SoftAssert softAssert;
    String selectQuery, endpointUrl;
    int responseStatusCode;
    String guid;
    String email, fax, fName, formData, manualEntry, noticeSubject, phone, presentationType, reviewRoleType, sName, surname;
    String email_updated, fax_updated, fName_updated, formData_updated, manualEntry_updated, noticeSubject_updated, phone_updated, presentationType_updated, reviewRoleType_updated, sName_updated, surname_updated;

    public PatchApplicationFormData(BaseUtil baseUtil) {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        this.baseUtil = baseUtil;
        this.databaseConnection = baseUtil.databaseConnection;
        softAssert = new SoftAssert();
    }

    @Given("the database has a record for FormType {string} GUID {string} FirstName {string} SecondName {string} Surname {string} CorrelationId {string}")
    public void theDatabaseHasARecordForFormTypeGUIDFirstNameSecondNameSurnameCorrelationId(String formType, String guid, String fName, String sName, String surname, String correlationId) throws Exception {
        selectQuery = "select * from rsdfrm_digital_forms where notice_type_cd='" + formType + "' and first_given_nm='" + fName + "' and second_given_nm='" + sName + "' and surname_nm='" + surname + "' and correlation_guid='" + correlationId + "' and form_object_guid='" + guid + "'";
        this.guid = guid;
        int rowCount = databaseConnection.getRowCount(selectQuery);

        Assert.assertEquals(rowCount, 1, "There should be ONE record in the database for SQL Query " + selectQuery);
    }

    @When("the user submits a PATCH Application Form Data request with parameters FormType {string} GUID {string} Correlation ID {string}")
    public void theUserSubmitsAPATCHApplicationFormDataRequestWithParametersFormTypeGUIDCorrelationID(String formType, String guid, String correlationId) throws Exception {
        endpointUrl = generateEndpoint.ApplicationForm(VerbType.PATCH,formType,guid,null,correlationId);
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

        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername,GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch().getStatusCode();
    }

    @Then("the user should get a successful response from PATCH request and the record should be updated in the database")
    public void theUserShouldGetASuccessfulResponseFromPATCHRequestAndTheRecordShouldBeUpdatedInTheDatabase() throws Exception {
        Assert.assertEquals(responseStatusCode, 200, "The response status should be OK:200");
        selectQuery = "select * from rsdfrm_digital_forms where form_object_guid='" + guid + "'";

        int rowCount = databaseConnection.getRowCount(selectQuery);
        Assert.assertEquals(rowCount, 1, "There should be ONE record in the database for SQL Query " + selectQuery);

        ResultSet dbResult = databaseConnection.executeSelectQuery(selectQuery);
        dbResult.next();

        softAssert.assertEquals(dbResult.getString("first_given_nm"),fName_updated,"First Name mismatch.");
        softAssert.assertEquals(dbResult.getString("second_given_nm"),sName_updated,"Second Name mismatch.");
        softAssert.assertEquals(dbResult.getString("surname_nm"),surname_updated,"Surname mismatch.");
        softAssert.assertEquals(dbResult.getString("phone_no"),phone_updated,"Phone mismatch.");
        softAssert.assertEquals(dbResult.getString("fax_no"),fax_updated,"Fax mismatch.");
        softAssert.assertEquals(dbResult.getString("electronic_address_txt"),email_updated,"Email mismatch.");
        //softAssert.assertEquals(dbResult.getString("form_xml"),formData_updated,"Form data mismatch.");
        softAssert.assertAll();

    }

    @And("the db record should be reverted to the original state")
    public void theDbRecordShouldBeRevertedToTheOriginalState() throws SQLException {
        String updateSqlQuery = "update rsdfrm_digital_forms set first_given_nm='"+fName+"', second_given_nm ='"+sName+"', surname_nm='"+surname+"', phone_no='"+phone+"', fax_no='"+phone+"', electronic_address_txt='"+email+"', form_xml='"+formData+"'  where form_object_guid='" + guid + "'";
        int updatedRows = databaseConnection.executeUpdateQuery(updateSqlQuery);
        Assert.assertEquals(updatedRows, 1, "One row should be updated for Query " + updateSqlQuery);
    }

}
