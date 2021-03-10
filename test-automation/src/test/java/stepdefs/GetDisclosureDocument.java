package stepdefs;

import api.ApiUtil;
import api.GenerateEndpoint;
import api.VerbType;
import config.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static api.ErrorMessages.apiErrorMessage;

public class GetDisclosureDocument {
    GenerateEndpoint generateEndpoint;
    ApiUtil apiUtil;
    SoftAssert softAssert;
    HashMap<String, HashMap<String, String>> jsonData;
    HashMap<String, String> data;
    String endpointUrl;
    int responseStatusCode;

    public GetDisclosureDocument() {
        generateEndpoint = new GenerateEndpoint(GlobalVariables.apiBaseUrl);
        softAssert = new SoftAssert();
    }

    @Given("the user has access to the Disclosure Document GET endpoint")
    public void theUserHasAccessToTheDisclosureDocumentGETEndpoint() {
    }

    @When("the user submits a GET Disclosure Document Request for DocumentId {string} CorrelationId {string}")
    public void theUserSubmitsAGETDisclosureDocumentRequestForDocumentIdCorrelationId(String documentId, String correlationId) {
        endpointUrl = generateEndpoint.Disclosure(VerbType.GET, documentId, correlationId);
        apiUtil = new ApiUtil(endpointUrl);
        responseStatusCode = apiUtil.getResponseStatusCodeUsingBasicAuthentication(GlobalVariables.apiUsername, GlobalVariables.apiPassword);
    }

    @Then("the user should get a response having attributes MimeType {string} and Document {string}")
    public void theUserShouldGetAResponseHavingAttributesMimeTypeAndDocument(String mimeType, String document) {
        Assert.assertEquals(responseStatusCode, 200, apiErrorMessage(responseStatusCode));

        jsonData = apiUtil.getAttributeValuesInNestedHashMap("data");
        data = jsonData.get("document");

        softAssert.assertEquals(data.get("mimeType"), mimeType, "Mime Type mismatch.");
        String documentActual = data.get("document");
        boolean documentPass = documentActual.endsWith(document);
        softAssert.assertTrue(documentPass,"Document Value Mismatch");
        softAssert.assertAll();

    }
}
