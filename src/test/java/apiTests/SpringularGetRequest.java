package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SpringularGetRequest {
    String baseUrl = "http://142.93.110.12:9119";
    String token = "Bearer eyJhbGciOiJIUzI1NiJ9" +
            ".eyJleHAiOjE2NjQwMDgxNDIsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9" +
            ".BHwZmih3JOuxvW8jadpbmr5Mz-L2dnmP7k1qQnW2tgU";

    @Test
    public void test1() {
        Response response = given().header("Authorization", token)
                .when().get(baseUrl+"/api/orders");

        Assert.assertEquals(response.statusCode(),200);
    }
}
