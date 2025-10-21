package tests;

import base_urls.FakeStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C17_CreateCartJsonNode extends FakeStoreBaseUrl {
    //Create a new shopping cart using the Fake Store API with JsonNode for dynamic payload handling.
    //https://fakestoreapi.com/carts

    @Test
    void C17_CreateCartJsonNodeTest() throws IOException {

        //Prepare the payload
        JsonNode payload = new ObjectMapper().readTree(new File("src/test/resources/test_data/cart_body.json"));
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/carts");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "userId", equalTo(payload.get("userId").intValue()),
                        "products[0].id", equalTo(payload.get("products").get(0).get("id").intValue()),
                        "products[0].title", equalTo(payload.get("products").get(0).get("title").textValue()),
                        "products[0].price", equalTo(payload.get("products").get(0).get("price").intValue()),
                        "products[0].description", equalTo(payload.get("products").get(0).get("description").textValue()),
                        "products[0].category", equalTo(payload.get("products").get(0).get("category").textValue()),
                        "products[0].image", equalTo(payload.get("products").get(0).get("image").textValue()),
                        "products[1].id", equalTo(payload.get("products").get(1).get("id").intValue()),
                        "products[1].title", equalTo(payload.get("products").get(1).get("title").textValue()),
                        "products[1].price", equalTo(payload.get("products").get(1).get("price").intValue()),
                        "products[1].description", equalTo(payload.get("products").get(1).get("description").textValue()),
                        "products[1].category", equalTo(payload.get("products").get(1).get("category").textValue()),
                        "products[1].image", equalTo(payload.get("products").get(1).get("image").textValue())
                );
    }
}