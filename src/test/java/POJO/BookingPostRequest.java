package POJO;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class BookingPostRequest {

    String accessToken = ConfigurationReader.get("accessTokenHeroku");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("herokuURL");
    }


    /*{
    "firstname": "GuiderSoft1",
    "lastname": "Hunters",
    "totalprice": 1990,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-01-01",
        "checkout": "2023-01-01"
    },
    "additionalneeds": "Breakfast"
}*/
    @Test
    public void PostNewBooking(){

        String postBody="{\n" +
                "    \"firstname\": \"GuiderSoft1\",\n" +
                "    \"lastname\": \"Hunters\",\n" +
                "    \"totalprice\": 1990,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-01-01\",\n" +
                "        \"checkout\": \"2023-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

       Response response=  given().header("Content-Type","application/json")
                 .header("Accept","application/json")
                 .and().body(postBody)
                 .post("/booking");
       response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);

        String firstName=response.path("booking.firstname");
        String lastname=response.path("booking.lastname");
        String checkin=response.path("booking.bookingdates.checkin");
        int bookingid=response.path("bookingid");

        System.out.println("firstName = " + firstName);
        System.out.println("lastname = " + lastname);
        System.out.println("checkin = " + checkin);
        System.out.println("bookingid = " + bookingid);

    }

    @Test
    public void PostNewBooking2(){

        Map<String,Object> bookingDatesMap=new HashMap<>();

        bookingDatesMap.put("checkin","2022-01-01");
        bookingDatesMap.put("checkout","2023-01-01");

        Map<String,Object> requestBodyMap=new HashMap<>();
        requestBodyMap.put("firstname","GuiderSoft2");
        requestBodyMap.put("lastname","Hunter");
        requestBodyMap.put("totalprice","1990");
        requestBodyMap.put("depositpaid","true");
        requestBodyMap.put("bookingdates",bookingDatesMap);
        requestBodyMap.put("additionalneeds","Lunch");




        Response response=  given().header("Content-Type","application/json")
                .header("Accept","application/json")
                .and().body(requestBodyMap)
                .post("/booking");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void PostNewBooking3(){

        Bookingdates bookingdates=new Bookingdates("2024-01-01","2025-01-01");
        Booking booking=new Booking("GuiderSoft3","Hunter",1990,true,bookingdates,"dinner+lunch");

        booking.setFirstname("Ziya");
        Response response=  given().header("Content-Type","application/json")
                .header("Accept","application/json")
                .and().body(booking)
                .post("/booking");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
    }


}
