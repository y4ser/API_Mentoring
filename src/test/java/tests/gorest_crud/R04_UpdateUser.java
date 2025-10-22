package tests.gorest_crud;

import base_urls.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static tests.gorest_crud.R02_CreateUser.randomEmail;
import static tests.gorest_crud.R02_CreateUser.userId;
import static utilities.ObjectMapperUtils.getJsonNode;
import static utilities.ObjectMapperUtils.setJsonNode;

public class R04_UpdateUser extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users/userId
    When
       {
            "id":321435,
            "name":"Yaser",
            "gender":"male",
            "email":"yaser@gmail.com",
            "status":"active"
         }
    Then
        Status code should be 20o
    And
        Response body should be like:
         {
            "id":321435,
            "name":"y4ser",
            "gender":"male",
            "email":"y4ser@gmail.com",
            "status":"active"
         }

    */

    @Test
    void updateUserTest(){

        //Set the payload
        JsonNode payload = getJsonNode("gorest_user_updated");
        randomEmail = Math.random()+"@outlook.com";
        setJsonNode(payload, "email", randomEmail);

        //Send the request
        Response response = given(spec).body(payload).put("/users/" + userId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo((randomEmail)),
                        "gender", equalTo(payload.get("gender").asText()),
                        "status", equalTo(payload.get("status").asText())
                );

    }

}
