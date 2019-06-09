package basic;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


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

    @Test
    public void statusCodeVerification() {
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyDizijJu7RWKHoq26ODh7uMp9w_LPbAZ9E")

                .when()
                .get("/distancematrix/json")

                .then()
                .statusCode(200);



    }
}
