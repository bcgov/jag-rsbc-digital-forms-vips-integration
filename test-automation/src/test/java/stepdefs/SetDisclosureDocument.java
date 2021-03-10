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

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class SetDisclosureDocument {

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


    public SetDisclosureDocument() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }

    @Given("the user has access to the Disclosure Document PATCH endpoint")
    public void theUserHasAccessToTheDisclosureDocumentPATCHEndpoint() {
    }

    @When("the user submits a PATCH Disclosure Document Request having DocumentId {string} Disclosed DateTime {string} CorrelationId {string}")
    public void theUserSubmitsAPATCHDisclosureDocumentRequestHavingDocumentIdDisclosedDateTimeCorrelationId(String documentId, String disclosedDateTime, String correlationId) {
        endpointUrl = generateEndpoint.Disclosure(VerbType.PATCH,null,correlationId);

        String jsonBody = "{\n" +
                "  \"disclosure\": {\n" +
                "    \"disclosedDtm\": \""+disclosedDateTime+"\",\n" +
                "    \"documentId\": \""+documentId+"\"\n" +
                "  }\n" +
                "}";
        responseStatusCode = given().auth().preemptive().basic(GlobalVariables.apiUsername, GlobalVariables.apiPassword).contentType(JSON).baseUri(endpointUrl).body(jsonBody).patch().getStatusCode();
    }

    @Then("the user should get a successful response for PATCH Disclosure Document")
    public void theUserShouldGetASuccessfulResponseForPATCHDisclosureDocument() {
        Assert.assertEquals(responseStatusCode, 200, "PATCH request failed.");
    }
}
