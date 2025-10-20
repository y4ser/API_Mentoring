package tasks;

import base_urls.ActivitiesBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ActivitiesCRUDPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class T03_ActivitiesCRUD extends ActivitiesBaseUrl {

/*
        Task: Write code that performs all CRUD operations on "activities"
        using the Fake REST API at https://fakerestapi.azurewebsites.net
        Requirements:
        1. Use POJO classes for all operations
        2. Implement CREATE (POST) - Add new activity
        3. Implement READ (GET) - Retrieve activity details
        4. Implement UPDATE (PUT) - Modify existing activity
        5. Implement DELETE - Remove activity
        6. Add appropriate assertions for each operation
*/

    ActivitiesCRUDPojo expectedData;

    @Test(priority = 1)
    void CreateActivitiesTest() {

        //Prepare the expected data
        expectedData = new ActivitiesCRUDPojo(7, "Activity 7", false);

        //Send the request
        Response response = given(spec).body(expectedData).post("/api/v1/Activities");
        response.prettyPrint();

        //Do assertion
        ActivitiesCRUDPojo actualData = response.as(ActivitiesCRUDPojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), expectedData.getId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());

    }


    //Read
    @Test(priority = 2)
    void GetActivitiesTest() {

        //Send the request
        Response response = given(spec).get("/api/v1/Activities/" + expectedData.getId());
        response.prettyPrint();

        //Do assertion
        ActivitiesCRUDPojo actualData = response.as(ActivitiesCRUDPojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), expectedData.getId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());

    }

    //Update
    @Test(priority = 3)
    void PutActivitiesTest() {

        //Prepare the update data
        ActivitiesCRUDPojo updatedData = new ActivitiesCRUDPojo(7, "Update 7", true);

        //Send the request
        Response response = given(spec).body(updatedData).put("/api/v1/Activities/" + updatedData.getId());
        response.prettyPrint();

        //Do assertion
        ActivitiesCRUDPojo actualData = response.as(ActivitiesCRUDPojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), updatedData.getId());
        assertEquals(actualData.getTitle(), updatedData.getTitle());

    }

    //Delete
    @Test(priority = 4)
    void DeleteActivitiesTest() {

        //Send the request
        Response response = given(spec).delete("/api/v1/Activities/" + expectedData.getId());
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);

    }


}
