package Section_11_Twitter_End_To_End_Workflow;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TwitterEndToEndWorkflowFinal {
    String consumerKey = "sErcR7SzifXe4ij7GlvXiskf5";
    String consumerSecret = "XwQfG5mU0UiPRgiHz3wcpYde6C6Hi8kVI2ZDPmvdQw2VtQUqzS";
    String accessToken = "714345903-voxuRIAdVLMJ06f2vSXNoGZVYYkJ2AumHqIgggQL";
    String accessSecret = "NEOUtfkjP9pYd0mS2R1PbosXoG46ijOn38DnUxNjZh7x4";
    String tweetId = "";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Test
    public void postTweet() {
        Response response =
                given()
                        .auth()
                        .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                        .queryParam("status", "My Fourth Tweet")
                        .when()
                        .post("/update.json")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        tweetId = response.path("id_str");
        System.out.println("The response.path: " + tweetId);
    }

    @Test(dependsOnMethods={"postTweet"})
    public void readTweet() {
        Response response =
                given()
                        .auth()
                        .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                        .queryParam("id", tweetId)
                        .when()
                        .get("/show.json")
                        .then()
                        .extract()
                        .response();

        String text = response.path("text");
        System.out.println("The tweet text is: " + text);
    }

    @Test(dependsOnMethods={"readTweet"})
    public void deleteTweet() {
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .pathParam("id", tweetId)
                .when()
                .post("/destroy/{id}.json")
                .then()
                .statusCode(200);
    }
}