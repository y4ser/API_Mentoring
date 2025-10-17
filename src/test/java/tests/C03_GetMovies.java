package tests;

import base_urls.MovieDBBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.Array;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C03_GetMovies extends MovieDBBaseUrl {
    /*
        https://api.themoviedb.org/3/tv/popular?api_key=2eeebe74d17da380e718f9066997a62a
        Objective:
        Test the popular TV shows API endpoint using various Hamcrest matchers
        Test Requirements:
        1. Verify that the "page" field in the response equals 1
        2. Check that the "results" array contains items with names "Peacemaker" and "One-Punch Man"
        3. Assert that the "overview" of the first item in "results" contains the substring "Ed Gein"
        4. Confirm that the "results" array has a size of 20 items
        5. Verify that the "vote_count" for "Game of Thrones" in the results is greater than 200
        6. Check that the "results" field is an instance of a List/Array
     */

    @Test
    void getMoviesTest() {

        Response response = given(spec).get("/3/tv/popular");

        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("page", equalTo(1))//        1. Verify that the "page" field in the response equals 1
                .body("results.name", hasItems("Peacemaker", "One-Punch Man"))//        2. Check that the "results" array contains items with names "Peacemaker" and "One-Punch Man"
                .body("results[0].overview", containsString("Ed Gein"))//        3. Assert that the "overview" of the first item in "results" contains the substring "Ed Gein"
                .body("results", hasSize(20))//        4. Confirm that the "results" array has a size of 20 items
                .body("results.find{it.name='Game of Thrones'}.vote_count", greaterThan(200))//        5. Verify that the "vote_count" for "Game of Thrones" in the results is greater than 200
                .body("results", instanceOf(List.class)); // 6. Check that the "results" field is an instance of a List/Array
    }
}