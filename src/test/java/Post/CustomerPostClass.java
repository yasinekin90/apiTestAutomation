package Post;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public  class  CustomerPostClass {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }


    @Test
    public void postCustomer(){
        CustomerPOJO customerPOJO=
                new CustomerPOJO(19991999,"Clair","MuhammedALİ"
                ,"muhammedali@gmail.com","IBM","1-(260)615-5114"
                ,"02937 Merrick Avenue","KAYABASIMAH"
                ,"nEWyORK","nORTaMERİCA","34494","USA");


        Response response=given().header("Authorization",TokenGenerator())
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body(customerPOJO)
                .and().post("/api/customers");

        response.prettyPrint();

        String operationStatus=response.path("operationStatus");
        String operationMessage=response.path("operationMessage");

        Assert.assertEquals(operationStatus,"SUCCESS");
        Assert.assertEquals(operationMessage,"Customer Added");
    }

    @Test
    public void CustomerPostAutoTest(){

        int id=7020;
        for (int i =id; i <7040 ; i++) {

            Faker faker=new Faker();

            CustomerPOJO customerPOJO=
                    new CustomerPOJO(i,faker.name().firstName()
                            ,faker.name().lastName()
                            ,faker.internet().emailAddress()
                            ,faker.company().name()
                            ,faker.phoneNumber().cellPhone()
                            ,faker.address().fullAddress()
                            ,faker.address().secondaryAddress()
                            ,faker.address().city()
                            ,faker.address().state()
                            ,faker.code().asin()
                            ,faker.address().country());


            Response response=given().header("Authorization",TokenGenerator())
                    .header("Accept","application/json")
                    .header("Content-Type","application/json")
                    .body(customerPOJO)
                    .and().post("/api/customers");
            response.prettyPrint();
        }

    }

    // http://142.93.110.12:9119/api/customers?customerid=1998
    @Test
    public void getCustomer(){
        Response response=given().header("Authorization",TokenGenerator())
                .header("Accept","application/json")
                .queryParam("customerid",19991990)
                .and().get("/api/customers");

        response.prettyPrint();
    }

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
