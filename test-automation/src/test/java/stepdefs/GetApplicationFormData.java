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

public class GetApplicationFormData {
    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    String endpointUrl;
    int responseStatusCode;

    public GetApplicationFormData() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }

    @Given("the user has access to GET Application Form Data")
    public void theUserHasAccessToGETApplicationFormData() {
    }


    @When("the user submits a GET Application Form Data request with parameters GUID {string} Correlation ID {string}")
    public void theUserSubmitsAGETApplicationFormDataRequestWithParametersGUIDCorrelationID(String guid, String correlationId) throws Exception {
        endpointUrl = generateEndpoint.ApplicationForm(VerbType.GET, null, guid, null, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);

    }


    @Then("the user should get a response having attributes ProhibitionNoticeNumber {string} NoticeType {string} ReviewApplicationType {string} NoticeSubject {string} PresentationType {string} ReviewRoleType {string}")
    public void theUserShouldGetAResponseHavingAttributesProhibitionNoticeNumberNoticeTypeReviewApplicationTypeNoticeSubjectPresentationTypeReviewRoleType(String prohibitionNoticeNo, String noticeType, String applicationType, String noticeSubject, String presentationType, String roleType) {
        Assert.assertEquals(responseStatusCode, 200);

        jsonData = apiUtil.getAttributeValuesInNestedHashMap("data");
        data = jsonData.get("applicationInfo");

        String prohibitionNoticeNoActual = data.get("prohibitionNoticeNo");
        String noticeTypeActual = data.get("noticeTypeCd");
        String applicationTypeActual = data.get("reviewApplnTypeCd");
        String noticeSubjectActual = data.get("noticeSubjectCd");
        String presentationTypeActual = data.get("presentationTypeCd");
        String roleTypeActual = data.get("reviewRoleTypeCd");

        softAssert.assertEquals(prohibitionNoticeNoActual, prohibitionNoticeNo, "Prohibition Number mismatch.");
        softAssert.assertEquals(noticeTypeActual, noticeType, "Notice Type mismatch.");
        softAssert.assertEquals(applicationTypeActual, applicationType, "Review Application Type mismatch.");
        softAssert.assertEquals(noticeSubjectActual, noticeSubject, "Notice Subject mismatch.");
        softAssert.assertEquals(presentationTypeActual, presentationType, "Presentation Type mismatch.");
        softAssert.assertEquals(roleTypeActual, roleType, "Review Role Type mismatch.");
        softAssert.assertAll();
    }


    @And("the response body should have attributes FirstName {string} Surname {string} Phone {string} Fax {string} Email {string} ManualEntry {string}")
    public void theResponseBodyShouldHaveAttributesFirstNameSurnamePhoneFaxEmailManualEntry(String firstName, String surname, String phone, String fax, String email, String manualEntry) {
        String firstNameActual = data.get("firstGivenNm");
        String surnameActual = data.get("surnameNm");
        String phoneActual = data.get("phoneNo");
        String faxActual = data.get("faxNo");
        String emailActual = data.get("email");
        String manualEntryActual = data.get("manualEntryYN");

        softAssert.assertEquals(firstNameActual, firstName, "First Name mismatch.");
        softAssert.assertEquals(surnameActual, surname, "Surname mismatch.");
        softAssert.assertEquals(phoneActual, phone, "Phone Number mismatch.");
        softAssert.assertEquals(faxActual, fax, "Fax number mismatch.");
        softAssert.assertEquals(emailActual, email, "Email mismatch.");
        softAssert.assertEquals(manualEntryActual, manualEntry, "Manual Entry mismatch.");
        softAssert.assertAll();
    }

}
