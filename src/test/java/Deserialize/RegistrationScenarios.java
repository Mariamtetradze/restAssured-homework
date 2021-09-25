package Deserialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.internal.runners.statements.Fail;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationScenarios {
    @Test
 public void registrationSuccess() {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "pistol");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        Response response = request.post("/api/register");
        ResponseBody body = response.getBody();

    }

    @Test
    public void UnsuccessfullRegistration() {
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        requestParams.put("email", "sydney@fife");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        Response response = request.post("/register");
        ResponseBody body = response.getBody();

        int statusCode = response.getStatusCode();
        System.out.print(statusCode);

        if(response.statusCode() == 400)
        {
            FailureResponse responseBody = body.as(FailureResponse.class);
            Assert.assertEquals("User already exists", responseBody.FaultId);
            Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
        }
        else if (response.statusCode() == 200)
        {
            SuccessResponse responseBody = body.as(SuccessResponse.class);
            Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
            Assert.assertEquals("Operation completed successfully", responseBody.Message);
        }

    }
    @Test
    public void userWithParameters() {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "morpheus");
        requestParams.put("job", "leader");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        Response response = request.post("/api/users");
        ResponseBody body = response.getBody();

        int statusCode = response.getStatusCode();
        System.out.print(statusCode);

    }

}
