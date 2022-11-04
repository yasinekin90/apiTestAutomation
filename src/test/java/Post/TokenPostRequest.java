package Post;


import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class TokenPostRequest {


    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void PostNewToken(){
        String jsonBody="{\n" +
                "  \"password\": \"quality_hunter\",\n" +
                "  \"username\": \"guidersoft\"\n" +
                "}";

        Response response=given().log().all()
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .and().body(jsonBody)
                .when().post("/session");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

      /*  String operationStatus=response.path("operationStatus");
        String operationMessage=response.path("operationMessage");

        System.out.println("operationMessage = " + operationMessage);*/

        JsonPath jsonPath=response.jsonPath();
        String operationStatus=jsonPath.getString("operationStatus");
        String operationMessage=jsonPath.getString("operationMessage");
        String item=jsonPath.getString("item");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);

    }

    @Test
    public void PostNewTokenPOJO(){
        TokenPost tokenPost=new TokenPost("guidersoft","quality_hunter");

        Response response=given().header("Accept","*/*")
                .header("Content-Type","application/json")
                .and().body(tokenPost)
                .and().post("/session");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();


        /*{
  "operationStatus" : "SUCCESS",
  "operationMessage" : "Login Success",
  "item" : {
    "token" : "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjY4NTUyNDQsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.7vtbUMcvRqXXFnozxFm9hX1W3RcKOPYp2hEGhAWhk4g",
    "userId" : "guidersoft",
    "firstName" : "Mrinmoy",
    "lastName" : "Majumdar",
    "email" : "arivera2@joomla.org",
    "roles" : null
  }
}*/
        String operationStatus=jsonPath.getString("operationStatus");
        String operationMessage=jsonPath.getString("operationMessage");
        String item=jsonPath.getString("item");
        String userId=jsonPath.getString("item.userId");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);

        System.out.println("userId = " + userId);

    }

    public static String TokenGenerator(String username,String password){
        TokenPost tokenPost=new TokenPost(username,password);

        Response response=given().header("Accept","*/*")
                .header("Content-Type","application/json")
                .and().body(tokenPost)
                .and().post("/session");

        JsonPath jsonPath=response.jsonPath();

        return  jsonPath.getString("item.token");


    }


}
