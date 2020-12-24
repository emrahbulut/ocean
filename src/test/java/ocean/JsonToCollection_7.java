package ocean;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsonToCollection_7 {

    /*
    Reading Json Body/Payload
    1-String contains ("value") --> boolean verification
    2-path()
    3-JsonPath()
    4-Hamcrest Matchers (chaining)

    5-JSON --> Java Datastructures/Collections
    Map<String, Object>
    De-Serialization
    Json Parser
    -GSON
    -Jackson 1.x
    -Jackson 2.x
    * */

    /*
        Given accept type is Json
        And ID parameter value is 1
        When user sends GET request to /posts/{id}
        Then response status code should be 200
        And response content-type: application/json; charset=utf-8
        And response payload values match the following
            "id": 10,
            "idBook": 3,
            "firstName": "First Name 10",
            "lastName": "Last Name 10"
        * */

    @BeforeClass
    public static void setupClass () {

        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

    }
    //    Short Video 15

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .and().when().get("/api/v1/Authors/{id}");


//        convert Json response to Java Collections (Map)
        Map<String, Object> collection = response.body().as(Map.class);

        System.out.println(collection.get("firstName"));
        System.out.println(collection.get("id"));

//        one example verification one side map / expected value
        Assert.assertEquals(collection.get("firstName"), "First Name 10");

    }

//    Short Video 16
    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/v1/Authors/");

//        response.prettyPrint();

//        convert full Json response to List of Map
        List<Map<String, Object>> listOfMap = response.body().as(List.class);

//      print all data of first author
        System.out.println(listOfMap.get(0));
        Map<String, Object> saveMap = listOfMap.get(0);
        System.out.println(saveMap);
        System.out.println(saveMap.get("firstName"));

        int counter = 1;

        for (Map<String, Object> map : listOfMap) {
            System.out.println(counter + "- spartan " + map);
            counter++;
        }
    }

}












