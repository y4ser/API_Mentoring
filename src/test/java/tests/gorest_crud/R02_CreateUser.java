package tests.gorest_crud;

import base_urls.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R02_CreateUser extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users
    And
        {
            "name":"Yaser",
            "gender":"male",
            "email":"Yaser@gmail.com",
            "status":"active"
         }
    When
        Send the post request
    Then
        Status code should be 201
    And
        Response body should be like:
          {
            "id":321435,
            "name":"Yaser",
            "gender":"male",
            "email":"Yaser@gmail.com",
            "status":"active"
         }

    */

    public static int userId;
    public static String randomEmail;

    @Test
    void createUserTest(){

        //Prepare the payload
        JsonNode payload = getJsonNode("gorest_user");
        randomEmail = Math.random()+"@gmail.com";
        ((ObjectNode) payload).put("email" ,randomEmail);
//        System.out.println(payload.toPrettyString());

        //Send the request
        Response response = given(spec).body(payload).post("/users");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "gender", equalTo(payload.get("gender").asText()),
                        "status", equalTo(payload.get("status").asText())
                );

        userId = response.jsonPath().getInt("id");

    }

}
