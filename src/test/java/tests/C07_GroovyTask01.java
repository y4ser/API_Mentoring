package tests;

import base_urls.JPHBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class C07_GroovyTask01 extends JPHBaseUrl {

     /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send GET request
    Then
        1) Status code = 200
        2) Print all ids > 190 (10 total)
        3) Print userIds with ids < 5 (4 total)
        4) Verify title “quis eius est sint explicabo”
        5) Find id where title = "quo adipisci enim quam ut ab"
     */

    @Test
    void groovyTask01Test() {

        //Send the request and get the response
        Response response = given(spec).get("/todos");
        response.prettyPrint();

        //Do Assertion
        JsonPath jsonPath = response.jsonPath();

        //        1) Status code = 200
        assertEquals(response.statusCode(), 200);

        //        2) Print all ids > 190 (10 total)
        List<Integer> idsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);
        assertEquals(idsGreaterThan190.size(), 10);

        //        3) Print userIds with ids < 5 (4 total)
        List<Integer> userIdThatIdsLessThan5 = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIdThatIdsLessThan5 = " + userIdThatIdsLessThan5);
        assertEquals(userIdThatIdsLessThan5.size(), 4);

        //        4) Verify title “quis eius est sint explicabo”
        int listSize = jsonPath.getList("findAll{it.title=='quis eius est sint explicabo'}").size();
        assertTrue(listSize > 0);

//        5) Find id where title = "quo adipisci enim quam ut ab"
        int idTitle = jsonPath.getInt("find{it.title=='quo adipisci enim quam ut ab'}.id");
        System.out.println("idTitle = " + idTitle);
        assertEquals(idTitle, 8);

    }

}
