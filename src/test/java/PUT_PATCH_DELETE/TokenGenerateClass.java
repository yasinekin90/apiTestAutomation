package PUT_PATCH_DELETE;

import Post.TokenPost;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenGenerateClass {

    public static String TokenGenerator(){
        TokenPost tokenPost=new TokenPost("guidersoft","quality_hunter");

        Response response=given().header("Accept","*/*")
                .header("Content-Type","application/json")
                .and().body(tokenPost)
                .and().post("/session");

        JsonPath jsonPath=response.jsonPath();

        return  jsonPath.getString("item.token");


    }
}
