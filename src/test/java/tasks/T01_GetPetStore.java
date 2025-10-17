package tasks;

import base_urls.T01_PetStoreUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class T01_GetPetStore extends T01_PetStoreUrl {

    /*
    Task 2: Pet Store API - Sold Pets Analysis
    API Documentation: https://petstore.swagger.io/
    Objective: Test sold pets endpoint and verify the number of sold pets is greater than 5
    API Endpoint:
    GET /pet/findByStatus?status=sold
    Test Requirements:
    1. Verify that the response array contains at least one item where the status field equals "sold"
    2. Check that the response array contains items with specific pet names (choose any two names that exist in the response)
    3. Assert that at least one item in the response array has a name field that contains a specific substring
    4. Confirm that the response array has a specific size (count the number of items in the array)
    5. Verify that at least one item in the response array has an id field with a value greater than a specified number
    6. Check that the response is an instance of a List/Array
    7. Verify that at least one item in the response array has a name field that starts with a specific prefix
    8. Check that every item in the response array has a status field with the value "sold"
    9. Assert that at least one item in the response array has either a specific name or a specific id value
     */

    @Test
    void getPetStoreTest(){

        Response response = given(spec).get("v2/pet/findByStatus?status=sold");

        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", hasItem("sold"))// 1. Verify that the response array contains at least one item where the status field equals "sold"
                .body("name", hasItems("doggie", "Victoria"))// 2. Check that the response array contains items with specific pet names (choose any two names that exist in the response)
                .body("name", hasItems(containsString("dog")))// 3. Assert that at least one item in the response array has a name field that contains a specific substring
                .body("size", hasSize(greaterThan(5)))// 4. Confirm that the response array has a specific size (count the number of items in the array)
                .body("id", hasItem(greaterThan(200)))// 5. Verify that at least one item in the response array has an id field with a value greater than a specified number
                .body("", instanceOf(List.class))// 6. Check that the response is an instance of a List/Array
                .body("name", hasItem(startsWith("up")))// 7. Verify that at least one item in the response array has a name field that starts with a specific prefix
                .body("status", everyItem(equalTo("sold")))// 8. Check that every item in the response array has a status field with the value "sold"
                .body("findAll{ it.name == 'Victoria' }", not(empty()));// 9. Assert that at least one item in the response array has either a specific name or a specific id value




    }

}
