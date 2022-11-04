package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _01_SimpleGetRequests {

    String herokuUrl="https://restful-booker.herokuapp.com";

    @Test
    public void test01(){
        Response response= RestAssured.get(herokuUrl+"/booking");

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

    }


    @Test
    public void test02(){

        Response response= RestAssured.get(herokuUrl+"/booking");
        //print json body
        response.prettyPrint();

    }

    @Test
    public void test03(){

        Response response= RestAssured.get(herokuUrl+"/booking");

        Assert.assertEquals(response.statusCode(),200);

        System.out.println("response.contentType() = " + response.contentType());

        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void test04(){

                given()
               .when().get(herokuUrl+"/booking")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8");


    }

    @Test
    public void test05(){
        Response response= given().accept("application/json")
                .when().get(herokuUrl+"/booking/150");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("a"),"name");

    }

    @Test
    public void test06(){
        /*Response response= given().accept("application/json")
                .when().get(herokuUrl+"/booking/100");
        response.prettyPrint();

    /*    Assert.assertEquals(response.statusCode(),200);
       Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
       Assert.assertTrue(response.getHeader("Server").equals("Cowboy"));
        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        Assert.assertTrue(response.getStatusLine().equals("HTTP/1.1 200 OK"));*/

        given()
                .when().get(herokuUrl+"/booking/100")
                .then().assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");

    }

    @Test
    public void test07(){

     /*   Response response= given().get(herokuUrl+"/booking/150");
        response.prettyPrint();*/

      /*  Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.body().asString().contains("Howard"));*/

    given().get(herokuUrl+"/booking/100")
            .then().assertThat().statusCode(200)
            .contentType("application/json; charset=utf-8")
            .and().body("firstname",Matchers.equalTo("abc"));
    }

    @Test
    public void test08(){
    Response r=    given().get(herokuUrl+"/booking/150");
        given().get(herokuUrl+"/booking/150")
                .then().assertThat()
                .statusCode(200).contentType("application/json; charset=utf-8")
                .and().body("firstname", Matchers.equalTo("Agustin"));

        System.out.println("r.getHeaders() = " + r.getHeaders());
    }
//response.prettyPrint();             // response u yazdırır.
//        response.getStatusCode();           // Status kodu verir.
//        response.getHeaders();              // tüm headerları verir.
//        response.getHeader("Server"); // istenen header değerini verir.
//        response.getContentType();          // Response Content Type ını verir.
//        response.getStatusLine();           // Response StatusLine verir.
//        response.getTime();                 // Response gerçekleşme süresini verir.

    @Test
    public void test09(){

        given().get(herokuUrl+"/booking/150")
                .then().assertThat()
                .statusCode(200).contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK")
                .and().time(Matchers.lessThan(2000L));

    }

}


