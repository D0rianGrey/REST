package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;


public class GetRequestDemo {

    /***
     * GIVEN I Have this information
     * When I perform this action
     * Then this should be the output
     */

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.basePath = "/maps/api";
    }

    @Test (enabled = false)
    public void statusCodeVerification() {
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyAFNxOzcDNEZ9coJzPc_9N-CA8Euun2fDA")
                .when()
                .get("/distancematrix/json")
                .then()
                .statusCode(200)
                .and()
                .body("rows[0].elements[0].distance.text", equalTo("225 mi"))
                .contentType(ContentType.JSON);

    }

    @Test
    public void getResponseBody() {
        Response res = given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyDizijJu7RWKHoq26ODh7uMp9w_LPbAZ9E")

                .when()
                .get("/distancematrix/json");
        System.out.println(res.body().asString());
        //System.out.println(res.body().prettyPrint()); - if there is a bad view in case asString


    }
}
