package tests;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetStoreUserPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C13_CreatePetStoreUser extends PetStoreBaseUrl {

    /*
    Task: Write an automation test that creates a 'user' using the
    Petstore API at https://petstore.swagger.io
    Requirements:
    1. Review the Petstore API documentation
    2. Identify the endpoint for creating users (/user)
    3. Create User POJO with all required fields
    4. Implement POST request to create user
    5. Validate successful creation with assertions
*/
    PetStoreUserPojo expectedData;
    @Test
    void createPetStoreUserTest() {

        //Prepare the expected data
        expectedData = new PetStoreUserPojo("3029", "Yaser", "Yaser", "SDA3029", "yaser@gmail.com", "12345678", "12345", 0);

        //Send the request
        Response response = given(spec).body(expectedData).post("/v2/user");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .statusCode(200)
                .body(
                        "code", equalTo(200),
                        "type", equalTo("unknown"),
                        "message", equalTo(expectedData.getId())
                );
    }

    @Test
    void getPetStoreUserTest() {

        //Send the request
        Response response = given(spec).get("/v2/user/" + expectedData.getUsername());
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "id", equalTo(Integer.parseInt(expectedData.getId())),
                        "username", equalTo(expectedData.getUsername()),
                        "firstName", equalTo(expectedData.getFirstName()),
                        "lastName", equalTo(expectedData.getLastName()),
                        "email", equalTo(expectedData.getEmail()),
                        "password", equalTo(expectedData.getPassword()),
                        "phone", equalTo(expectedData.getPhone()),
                        "userStatus", equalTo(expectedData.getUserStatus())
                );
    }

}

