package geargames;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class AbstractStep {
    protected final String correctCredentials = "{\"username\":\"johnd\",\"password\": \"m38rmF$\"}";
    protected final String baseUri = "https://fakestoreapi.com";
    protected final Map<String, String> urls = Map.of(
            "login", "/auth/login",
            "users", "/users"
    );
    protected final String url = "/users";

    protected Response response;
    protected String authToken;

    protected void authenticate() {
            response = RestAssured.given()
            .baseUri(baseUri)
            .contentType(JSON)
            .body(correctCredentials)
            .post("/auth/login");

        authToken = response.jsonPath().get("token");
    }
}
