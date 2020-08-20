package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class ApiUtil {
    String apiEndpoint;
    RequestSpecification httpRequest;

    public ApiUtil(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        httpRequest = RestAssured.given();
    }


    public int getResponseStatusCode() {
        return httpRequest.get(apiEndpoint).getStatusCode();
    }

    public int getResponseStatusCodeUsingBasicAuthentication(String username, String password) {
        return httpRequest.auth().preemptive().basic(username, password).get(apiEndpoint).getStatusCode();
    }

    public String getResponseStatusLine() {
        return httpRequest.get(apiEndpoint).getStatusLine();
    }

    public String getResponseStatusLineUsingBasicAuthentication(String username, String password) {
        return httpRequest.auth().preemptive().basic(username, password).get(apiEndpoint).getStatusLine();
    }


    public String getResponseBody() {
        ResponseBody body = httpRequest.get(apiEndpoint).getBody();
        return body.asString();
    }


    public String getResponseBodyUsingBasicAuthentication(String username, String password) {
        ResponseBody body = httpRequest.auth().preemptive().basic(username, password).get(apiEndpoint).getBody();
        return body.asString();
    }


    public String getNodeValueFromResponse(String nodeName) {
        JsonPath jsonPathEvaluator = httpRequest.get(apiEndpoint).jsonPath();
        return jsonPathEvaluator.get(nodeName);
    }


    public String getAttributeValues(String nodeName) {
        JsonPath jsonPathEvaluator = httpRequest.get(apiEndpoint).jsonPath();
        String dataList = jsonPathEvaluator.get(nodeName);
        return dataList;
    }

    public HashMap<String, String> getAttributeValuesInHashMap(String nodeName) {
        JsonPath jsonPathEvaluator = httpRequest.get(apiEndpoint).jsonPath();
        HashMap<String, String> dataList = jsonPathEvaluator.get(nodeName);
        return dataList;
    }


    public HashMap<String, HashMap<String, String>> getAttributeValuesInNestedHashMap(String nodeName) {
        JsonPath jsonPathEvaluator = httpRequest.get(apiEndpoint).jsonPath();
        HashMap<String, HashMap<String, String>> dataList = jsonPathEvaluator.get(nodeName);
        return dataList;
    }


}
