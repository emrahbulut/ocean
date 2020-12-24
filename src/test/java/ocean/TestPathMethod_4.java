package ocean;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestPathMethod_4 {

//    @BeforeClass
//    public void setupClass() {
//
//        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/";
//
//
//    }
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

    String name = "https://fakerestapi.azurewebsites.net/";

    @Test
    public void pathMethod() {

        Response response = RestAssured.given().accept(ContentType.JSON).and().when().get(name + "api/v1/Activities/10");

//        Response response = RestAssured.given().accept(ContentType.JSON)
//                .pathParam("id", 10)
//                .when().get("api/v1/Activities/{id}");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json; charset=utf-8; v=1.0");

        System.out.println("Id: " + response.body().path("id").toString());
        System.out.println("Title: " + response.body().path("title").toString());
        System.out.println("DueDate: " + response.body().path("dueDate").toString());
        System.out.println("Completed: " + response.body().path("completed").toString());

        int id = response.path("id");
        String title = response.body().path("title");
        String dueDate = response.body().path("dueDate");
        Boolean completed = response.body().path("completed");

        System.out.println("id = " + id);
        System.out.println("title = " + title);
        System.out.println("dueDate = " + dueDate);
        System.out.println("phone = " + completed);

        assertEquals(id, 10);
        assertEquals(title, "Activity 10");
        assertEquals(dueDate, "2020-12-03T23:59:01.4112338+00:00");
        assertEquals(completed, true);

    }

    @Test
    public void moreThanOne() {

        Response response = RestAssured.given().accept(ContentType.JSON).and().when().get(name + "api/v1/Activities");

//        System.out.println("response.prettyPrint() = " + response.prettyPrint());

        int firstID = response.path("id[0]");
        System.out.println("firstID " + firstID);

        String title = response.path("title[0]");
        System.out.println("title: " + title);

        String dateF = response.path("dueDate[0]");
        System.out.println("dateF = " + dateF);

        Boolean completed = response.path("completed[0]");
        System.out.println("completed = " + completed);

        List<String> allTitles = response.path("title");
        System.out.println("allTitles.size() = " + allTitles.size());
        System.out.println("allTitles = " + allTitles);

        List<Object> idNumber = response.path("id");

        for (Object idNumbers : idNumber) {
            System.out.println(idNumber);
        }

    }
}
