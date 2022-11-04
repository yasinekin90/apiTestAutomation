package apiTests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;
public class _10_JsonToJavaCollection {
    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    //De-Serialization  --> Json to Java Collection for example List, Set, Map
    /*
    {
    "version": "1.0.0",
    "major": 1,
    "minor": 0,
    "patch": 0
}
     */

    @Test
    public void versionToMap() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/version");
        assertEquals(response.statusCode(), 200);

        //we will convert json response to java map
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println(jsonDataMap);

        String version =  jsonDataMap.get("version").toString();
        Double major = (Double) jsonDataMap.get("major");
        Double minor = (Double) jsonDataMap.get("minor");
        Double patch = (Double) jsonDataMap.get("patch");

        System.out.println("version = " + version);
        System.out.println("major = " + major);
        System.out.println("minor = " + minor);
        System.out.println("patch = " + patch);


        assertEquals(version, "1.0.0");
        assertEquals(major, 1.0);
        assertEquals(minor, 0.0);
        assertEquals(patch, 0.0);
    }


    @Test
    public void allEmployeeListOfMap(){
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/employees");
        assertEquals(response.statusCode(),200);
        // we need to de-serialize JSON response to List Of Maps
        JsonPath jsonPath=response.jsonPath();
       jsonPath.prettyPrint();
      String items = jsonPath.getString("items");
      System.out.println("items = " + items);
        List<Map<String,Object>> allEmployeeList = response.body().as(List.class);
        System.out.println(allEmployeeList);
    }

    @Test
    public void allBookingIdToListOfMap(){
        Response response=given().get(ConfigurationReader.get("herokuURL")+"/booking");

            response.then().assertThat().statusCode(200);

            //we need to de-serialize JSON response List of Maps
        List<Map<String,Object>>allBookingList=response.body().as(List.class);

       // System.out.println("allBookingList = " + allBookingList);

        //ID of the first element of the list
        System.out.println("allBookingList.get(0).get(\"bookingid\") = " + allBookingList.get(0).get("bookingid"));

        Map<String ,Object> bookingId5=allBookingList.get(4);

        System.out.println("bookingId5 = " + bookingId5);


    }

    @Test
    public void productsToMap(){
        Response response=given().header("Authorization",accessToken)
                .when().get("api/products");

        //we de-serialize JSON response to Map
        Map<String,Object> productsMap=response.body().as(Map.class);

        System.out.println("productsMap.get(\"operationStatus\") = " + productsMap.get("operationStatus"));
        System.out.println("productsMap.get(\"first\") = " + productsMap.get("first"));


        //items list oldugu için ayrı liste atama yaptık
        List<Map<String,Object>>itemList= (List<Map<String, Object>>) productsMap.get("items");

        System.out.println("itemList.get(0) = " + itemList.get(0));

        System.out.println("itemList.get(1).get(\"id\") = " + itemList.get(1).get("id"));

    }

}
