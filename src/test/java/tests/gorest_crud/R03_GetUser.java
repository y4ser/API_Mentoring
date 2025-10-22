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

public class R03_GetUser extends GoRestBaseUrl {

      /*
    Given
        https://gorest.co.in/public/v2/users/userId
    When
        Send the get request
    Then
        Status code should be 20o
    And
        Response body should be like:
         {
            "id":321435,
            "name":"Yaser",
            "gender":"male",
            "email":"yaser@gmail.com",
            "status":"active"
         }

    */

    @Test
    void getUserTest(){

        //Send the request
        Response response = given(spec).get("/users/" + userId);
        response.prettyPrint();

        //Do assertion
        JsonNode userBody = getJsonNode("gorest_user");
        response
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(
                        "name", equalTo(userBody.get("name").asText()),
                        "email", equalTo((randomEmail)),
                        "gender", equalTo(userBody.get("gender").asText()),
                        "status", equalTo(userBody.get("status").asText())
                );

    }

}
