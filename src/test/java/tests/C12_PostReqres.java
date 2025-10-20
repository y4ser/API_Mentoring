package tests;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ReqresUserPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C12_PostReqres extends ReqresBaseUrl {

/*
    Given: Base URL: https://reqres.in/api/users
    Request Body:
    {
     "name": "morpheus",
     "job": "leader"
    }
    When: Send a POST request to the URL
    Then: Assert the status code is 201
    Verify the response body matches the structure:
    {
     "name": "morpheus",
     "job": "leader",
     "id": "496",
     "createdAt": "2022-10-04T15:18:56.372Z"
    }
    Note: Add authentication header: "x-api-key" with value "reqres-free-v1"
*/

    @Test
    void postReqresMapTest(){

        //Prepare the expected data
        Map<String, String> expectData = new HashMap<>();
        expectData.put("name", "morpheus");
        expectData.put("job", "leader");

        //Send the request
        Response response = given(spec).body(expectData).post("/api/users");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("name"), expectData.get("name"));
        assertEquals(actualData.get("job"), expectData.get("job"));


    }

    @Test
    void postReqresPojoTest(){

        //Prepare the expected data
        ReqresUserPojo expectData = new ReqresUserPojo("morpheus", "leader");

        //Send the request
        Response response = given(spec).body(expectData).post("/api/users");
        response.prettyPrint();

        //Do assertion
        ReqresUserPojo actualData = response.as(ReqresUserPojo.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getName(), expectData.getName());
        assertEquals(actualData.getJob(), expectData.getJob());


    }

}
