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
import static utilities.ObjectMapperUtils.*;

public class R05_PartialUpdateUser extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users/userId
    When
       {
            "name":"y4ser",
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
    void partialUpdateUserTest(){

        //Set the payload
        JsonNode payload = getJsonNode("gorest_user");
        setJsonNode(payload, "name" ,"John");
        removeField(payload, "gender");
        removeField(payload, "email");
        removeField(payload, "status");
        System.out.println(payload);


        //Send the request
        Response response = given(spec).body(payload).patch("/users/" + userId);
        response.prettyPrint();

        //Do assertion
        JsonNode updatedUser = getJsonNode("gorest_user_updated");
        response
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo((randomEmail)),
                        "gender", equalTo(updatedUser.get("gender").asText()),
                        "status", equalTo(updatedUser.get("status").asText())
                );

    }

}
