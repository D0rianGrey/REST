package basic;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostRequestDemo {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://www.googleapis.com";
        RestAssured.basePath = "/geolocation/v1";
    }

    @Test
    public void statusCodeVerification() {
        Response res = given()
                .queryParam("key", "AIzaSyDizijJu7RWKHoq26ODh7uMp9w_LPbAZ9E")
                .body("{\n" +
                        "  \"macAddress\": \"00:25:9c:cf:1c:ac\",\n" +
                        "  \"signalStrength\": -43,\n" +
                        "  \"age\": 0,\n" +
                        "  \"channel\": 11,\n" +
                        "  \"signalToNoiseRatio\": 0\n" +
                        "}").header("content-type", "application/json")

                .post("/geolocate");
        System.out.println(res.body().asString());
//		.then()
//			.statusCode(200).and()
//			.contentType(ContentType.JSON).and()
//			.body("scope", equalTo("APP")).and()
//			.body("status", equalTo("OK"));
    }
}
