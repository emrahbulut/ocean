package ocean;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChainingHamcrest_6 {


    @BeforeClass
    public static void setupClass() {

        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

    }
  /*
        Given accept type is Json
        And ID parameter value is 18
        When user sends GET request to /posts/{id}
        Then response status code should be 200
        And response content-type: application/json; charset=utf-8
        And response payload values match the following
            "id": 10,
            "title": "Activity 10",
            "dueDate": "2020-12-03T21:50:56.3813756+00:00",
            "completed": true
        * */

    @Test
    public void test1() {

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .and().when().get("/api/v1/Authors/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8; v=1.0");
    }

    @Test
    public void test2() {

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .and().when().get("/api/v1/Authors/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8; v=1.0")
                .and().assertThat().body("id", Matchers.equalTo(10), "idBook", Matchers.equalTo(4),
                "firstName", Matchers.equalTo("First Name 10"), "lastName", Matchers.equalTo("Last Name 10"));


    }
}
