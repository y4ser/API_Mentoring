package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class T01_PetStoreUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/")
//                .addQueryParam("GET","/pet/findByStatus?status=sold")
                .build();
    }

}
