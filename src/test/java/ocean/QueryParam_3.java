package ocean;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class QueryParam_3 {

    @BeforeClass
    public static void setupClass() {

//        import io.restassured.RestAssured; -->import static io.restassured.RestAssured.*;
//        no need to write RestAssured each time

       baseURI = "https://fakerestapi.azurewebsites.net/";
    }

  /*
        Given accept type is Json
        And query parameter values are:
            gender -> Female
            nameContains -> J

        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        Ans response content-type: application/json; charset=utf-8; v=1.0
        And "Female" should be in response payload
        And "Janette" should be in response
        * */

    @Test
    public void queryParamOcean1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J" )
                .when().get("posts/{id}");

//        import org.junit.Assert; --> import static org.junit.Assert.*
//        no need "Assert." by using static .....*

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "charset=utf-8; v=1.0");

        assertTrue(response.body().toString().contains("Male"));

        response.body().prettyPrint();


    }

    @Test
    public void queryParamOcean2() {


//        creating map for query Params

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");


//        send request
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("posts/{id}");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "charset=utf-8; v=1.0");

        assertTrue(response.body().toString().contains("Male"));

        response.body().prettyPrint();


    }

}