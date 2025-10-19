package tasks;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class T02_JsonPath extends BookerBaseUrl {

    /*
    Given
     https://restful-booker.herokuapp.com/booking/32
    When
     User sends GET request
    Then
     Status Code: 200
    And
     Content Type: application/json
    And
     firstname: "Josh"
     lastname: "Allen"
     totalprice: 111
     */

    @Test
    void test01() {

        Response response = given(spec).get("/booking/1224");
        response.prettyPrint();


        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(
                        "firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111)
                        );

    }

}
