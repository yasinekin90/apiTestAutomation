package apiTests;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Tokens {

    String baseUrl = "http://142.93.110.12:9119";
    String token = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjY2ODc4MzQsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.Q3Bwj8SQPNmF6Dim0sMFlZ1b6kgd97k4c7Kk0h-NvFY";


    @Test
    public void test1() {
        Response response = given().header("Authorization", token)
                .when().get(baseUrl+"/api/orders");
        response.prettyPrint();

    }

    @Test
    public void test2() {
        Response response = given().header("Authorization", token)
                .when().get(baseUrl+"/api/orders?orderid=4003");
        response.prettyPrint();

    }
    @Test
    public void test3() {
        Response response = given().header("Authorization", token)
                .when().get(baseUrl+"/api/orders?orderid=4500");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);

        System.out.println("response.getHeaders() = " + response.getHeaders());

    }
}