package POJO;


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class _01_Pojo_Deserialize {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void versionPOJO(){
        Response response=given().header("Authorization",accessToken)
                .when().get("/version");
        assertEquals(response.statusCode(),200);
  //JSON to POJO-->deserialize to java custom class
        //Json to our version class object
        Version version1=response.body().as(Version.class);

        System.out.println("version1 = " + version1);
        System.out.println("version1.getVersion() = " + version1.getVersion());
        assertEquals(version1.getMajor(),1);
        assertEquals(version1.getMinor(),0);

    }

    /*{
    "firstname": "Raúl",
    "lastname": "Camacho",
    "totalprice": 334,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-25",
        "checkout": "2022-11-02"
    },
    "additionalneeds": "Lunch"
}*/

    @Test
    public void bookingToPOJO(){
        Response response=given().accept("application/json")
                .when().get("https://restful-booker.herokuapp.com/booking/15");

        assertEquals(response.statusCode(),200);

        //googledan json to java yazıp body icin bir POJO class olusturulabilir

        Booking booking=response.body().as(Booking.class);

        System.out.println("booking.getFirstname() = " + booking.getFirstname());

    }

    @Test
    public void gson_example(){
        Gson gson=new Gson();
        //Json to Java collections or POJO -->de-serialization

        String ourJsonData="{\n" +
                "    \"version\": \"1.0.0\",\n" +
                "    \"major\": 1,\n" +
                "    \"minor\": 0,\n" +
                "    \"patch\": 0\n" +
                "}";

        Map<String,Object>map=gson.fromJson(ourJsonData,Map.class);

        System.out.println(map);

        Version version=gson.fromJson(ourJsonData,Version.class);

        System.out.println(version);


        //----serialization--------
        //Java Collection or POJO to JSON

        Version versionNew=new Version("1.1.0",2,1,1);
        String versionStr=gson.toJson(versionNew);
        System.out.println("versionStr = " + versionStr);
    }

}
