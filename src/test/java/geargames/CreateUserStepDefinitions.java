package geargames;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.Map;

import static org.junit.Assert.*;

public class CreateUserStepDefinitions extends AbstractStep {
    @Before
    public void auth() {
        authenticate();
    }

    @Given("POST-запрос на создание пользователя с телом:")
    public void createUser(String requestBody) throws Exception {
        response = RestAssured.given()
                .baseUri(baseUri)
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(urls.get("users"));
    }

    @Then("Статус ответа для создания одного пользователя должен быть {int}")
    public void checkCreateUserStatus(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("Ответ должен содержать поле {string}")
    public void checkUserResponseFormat(String fieldName) {
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> json = jsonPath.getMap("$");
        assertTrue("Поле '" + fieldName + "' отсутствует в ответе", json.containsKey(fieldName));
    }

    @Given("POST-запрос на создание нескольких пользователей с телом:")
    public void createMultiUser(String requestBody) throws Exception {
        response = RestAssured.given()
                .baseUri(baseUri)
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(url);
    }

    @Then("Статус ответа для создания нескольких пользователей должен быть {int}")
    public void checkCreateMultiUserStatus(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }
}