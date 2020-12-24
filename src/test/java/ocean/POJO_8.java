package ocean;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class POJO_8 {

    /*
    POJO -> Plain Old Java Object
    Json response -> Java object (Pojo) : De-serialization
    Java object -> Json response : Serialization
    * */


    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

    }

    @Test
    public void Test1 () {

        Response response = RestAssured.given().accept(ContentType.JSON)
                            .pathParam("id", 10)
                            .and().when().get("/api/v1/Authors/{id}");

//        how to convert json response to our spartan class

        Pojo pojo = response.body().as(Pojo.class); // Pojo class !!!

        System.out.println(pojo.toString());

//        verify each key with spartan object
        Assert.assertEquals(pojo.getFirstName(),"First Name 10");
        Assert.assertEquals(pojo.getLastName(), "Last Name 10");
        Assert.assertEquals(pojo.getId(),10);
        Assert.assertEquals(pojo.getIdBook(), 3);

    }

    @Test
    public void Test2 () {

        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "        \"id\": 10,\n" +
                "        \"idBook\": 3,\n" +
                "        \"firstName\": \"First Name 10\",\n" +
                "        \"lastName\": \"Last Name 10\"\n" +
                "    }";

//        using gson method to de-serialize our json body

        Pojo pojoMeta = gson.fromJson(myJsonBody, Pojo.class);

        System.out.println(pojoMeta.toString());

//        serialization Java object --> Json Body

        Pojo pojo = new Pojo(13, 3, "Emrah", "Bulut");
        String jsonBody = gson.toJson(pojo);


    }
}
