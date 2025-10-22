package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class GoRestBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {

        String apiKey = ConfigReader.getProperty("API_KEY");

        spec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2")
                .addHeader("Authorization", "Bearer " + apiKey)
                .setContentType(ContentType.JSON)
                .build();
    }

}
