package PUT_PATCH_DELETE;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class PutRequest {
    String accessToken = ConfigurationReader.get("accessTokenHeroku");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("herokuURL");
    }

    /*
    id=115 will be updated
  {
    "firstname": "Guoqiang",
    "lastname": "Vera",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}*/

    @Test
    public void PutTest(){
        //Create one inner map for the put request jsonbody

        Map<String,Object>bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin","3018-01-01");
        bookingDatesMap.put("checkout","4018-01-01");

        Map<String,Object>jsonBodyMap=new HashMap<>();

        jsonBodyMap.put("firstname","Selami");
        jsonBodyMap.put("lastname","Sahin");
        jsonBodyMap.put("totalprice",111);
        jsonBodyMap.put("depositpaid",false);
        jsonBodyMap.put("bookingdates",bookingDatesMap);
        jsonBodyMap.put("additionalneeds","SarkSofrasi");

        given().header("Authorization",accessToken)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Cookie","token=cf6cd7565728f0e")
                .and().body(jsonBodyMap).put("/booking/115")
                .then().assertThat().statusCode(200);

    }

    @Test
    public void PatchTest(){
        Map<String,Object>bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","5018-01-01");

        Map<String,Object>jsonBodyMap=new HashMap<>();

        jsonBodyMap.put("firstname","Ankaralı");
        jsonBodyMap.put("lastname","Namık");
        jsonBodyMap.put("bookingdates",bookingDatesMap);
        jsonBodyMap.put("additionalneeds","Sabahlar OLMASIN");

        given().header("Authorization",accessToken)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Cookie","token=cf6cd7565728f0e")
                .and().body(jsonBodyMap).patch("/booking/115")
                .then().assertThat().statusCode(200);
    }
}
