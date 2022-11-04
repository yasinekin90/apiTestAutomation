package apiTests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _05_TestWithPath {

    String petStoreURL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public void beforeClass() {
        baseURI = petStoreURL;
    }

    /*
          Given Id parameter value is 12
          When user sends GET request to /pet/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "doggie" should be in response payload
       */

    @Test
    public void getOnePet_path(){
        Response response=given().pathParam("id",10)
                .when().get("/pet/{id}");
        assertEquals(response.statusCode(),200);
     response.prettyPrint();
        // assertTrue(response.body().asString().contains("doggie"));

        //printing each key values in the json body/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("status").toString());
        System.out.println(response.path("category").toString());
        System.out.println(response.path("tags").toString());

        int id = response.path("id");
        String name = response.path("name");
        String status = response.path("status");


        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("status = " + status);

        assertEquals(id,10);
        assertEquals(name,"doggie");
        assertEquals(status,"string");
    }
}
