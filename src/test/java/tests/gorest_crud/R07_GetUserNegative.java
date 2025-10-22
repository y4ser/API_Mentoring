package tests.gorest_crud;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static tests.gorest_crud.R02_CreateUser.userId;

public class R07_GetUserNegative extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v2/users/userId
    When
        Send the get request
    Then
        Status code should be 404
    And
        Response body should be like:
        {
            "message": "Resource not found"
        }

    */
    @Test
    void getUserNegativeTest() {

        //Send the request
        Response response = given(spec).get("/users/" + userId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(404)
                .time(lessThan(3000L))
                .body("message", equalTo("Resource not found"));
    }
}