package PUT_PATCH_DELETE;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
public class DeleteRequest {
    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void deleteCustomer(){

        Random rn=new Random();
        int id=rn.nextInt(20)+7020;

        System.out.println("idDeleted = " + id);
        given().header("Authorization",TokenGenerateClass.TokenGenerator())
                .pathParam("id", id)
                .when().delete("/api/customers/{id}")
                .then().statusCode(200).log().all();
    }
}
