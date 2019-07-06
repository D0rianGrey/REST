package Section_10_Validating_JSON_Response;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TwitterExtractResponsePart2 {
    String consumerKey = "sErcR7SzifXe4ij7GlvXiskf5";
    String consumerSecret = "XwQfG5mU0UiPRgiHz3wcpYde6C6Hi8kVI2ZDPmvdQw2VtQUqzS";
    String accessToken = "714345903-voxuRIAdVLMJ06f2vSXNoGZVYYkJ2AumHqIgggQL";
    String accessSecret = "NEOUtfkjP9pYd0mS2R1PbosXoG46ijOn38DnUxNjZh7x4";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Test
    public void extractResponse() {
        Response response =
                given()
                        .auth()
                        .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                        .queryParam("status", "My First Tweet")
                        .when()
                        .post("/update.json")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String id = response.path("id_str");
        System.out.println("The response.path: " + id);

        String responseString = response.asString();
        System.out.println(responseString);

        JsonPath jsPath = new JsonPath(responseString);
        String name = jsPath.get("user.name");
        System.out.println("The username is: " + name);
    }
}