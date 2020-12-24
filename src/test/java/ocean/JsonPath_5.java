package ocean;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsonPath_5 {

    @BeforeClass
    public static void setupClass() {

        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";


    }
    @Test
    public void jsonTest() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("api/v1/Authors/{id}");

        assertEquals(response.statusCode(), 200);

//        how to read value with path() method
        Object id = response.path("id");
        System.out.println(id);

//        how to read value with JsonPath
//       JsonPath_5 jsonData = response.jsonPath();
//
//       int id1 = jsonData.getInt("id");
//       int idBook = jsonData.getInt("idBook");
//       String firstName = jsonData.getString("firstName");
//       String lastName = jsonData.getString("lastName");
//
//        System.out.println("id1 = " + id1);
//        System.out.println("idBook = " + idBook);
//        System.out.println("firstName = " + firstName);
//        System.out.println("lastName = " + lastName);
//
//        assertEquals(id1, 10);
//        assertEquals(idBook, 3);
//        assertEquals(firstName, "First Name 10");
//        assertEquals(lastName, "Last Name 10");

    }


}
