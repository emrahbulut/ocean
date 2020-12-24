package ocean;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class OceanTest_1 {

    String oceanBaseUrl = "https://jsonplaceholder.typicode.com";

    @Test
    public void viewOceanTest() {

        Response response = RestAssured.get(oceanBaseUrl + "/posts");

//        status code
        System.out.println(response.statusCode());

//        print body
        System.out.println(response.body().asString());

        System.out.println("========================");

//        print body
        System.out.println(response.body().prettyPrint());

//        When user send GET request to /posts end point
//        Then status code must be 200
//        And body should contains Allen

    }

    @Test
    public void getContain() {

        Response response = RestAssured.get(oceanBaseUrl + "/posts");

//        verify status code 200
        Assert.assertEquals(response.statusCode(), 200);

//        verify body contains Allen
        Assert.assertFalse(response.body().toString().contains("ullam"));

    }

    /*Given type is Json
    When user sends a get request to spartanAllUrl
    Then response status code 200
    And response body should be Json format
    */

    @Test
    public void typeOcean() {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(oceanBaseUrl + "/posts");

        Assert.assertEquals(response.statusCode(), 200);

//        verify response body Json
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

}
