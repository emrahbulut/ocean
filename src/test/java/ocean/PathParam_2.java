package ocean;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class PathParam_2 {

    @BeforeClass
    public static void setupClass() {

        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/";

    }
  /*
        Given accept type is Json
        And path parameter value is 10
        When user sends GET request to /posts/{id}
        Then response status code should be 200
        And response content-type: application/json; charset=utf-8; v=1.0
        And "alias" should be in response payload
        * */

    @Test
    public void pathParamOcean1 () {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("api/v1/Activities/{id}");

//        import org.junit.Assert; --> import static org.junit.Assert.*
//        no need "Assert." by using static .....*

        assertEquals(response.statusCode(),200);
//        assertEquals(response.contentType(), "application/json; charset=utf-8; v=1.0");
//        assertTrue(response.body().toString().contains("Activity 1"));

        response.body().prettyPrint();

    }

    /*
        Given accept type is Json
        And ID parameter value is 500
        When user sends GET request to /posts/{id}
        Then response status code should be 404
        And response content-type: application/json; charset=utf-8; v=1.0
        And "alias not found" should be in response payload
        * */

    @Test
    public void negativePathParam() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .and().when().get("posts/{id}");

        assertEquals(response.statusCode(), 404);

        assertEquals(response.contentType(), "charset=utf-8");

        assertFalse(response.body().toString().contains("alias"));

        response.prettyPrint();
    }

}

