package geargames;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.HttpClientUtil;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AuthStepDefinition extends AbstractStep {
    @Given("POST-запрос на аутентификацию с верными учетными данными:")
    public void successAuthenticate(String requestBody) throws Exception {
        response = RestAssured.given()
                .baseUri(HttpClientUtil.baseUri)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(urls.get("login"));
    }

    @Then("Статус ответа для успешной аутентификации должен быть {int}")
    public void checkSuccessAuthStatus(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("Ответ должен содержать строковое поле {string}")
    public void checkTokenField(String fieldName) {
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> json = jsonPath.getMap("$");
        assertTrue("Поле '" + fieldName + "' отсутствует в ответе", json.containsKey(fieldName));
    }

    @Given("POST-запрос на аутентификацию с неверными учетными данными:")
    public void failedAuthenticate(String requestBody) throws Exception {
        response = RestAssured.given()
                .log().all()
                .baseUri(HttpClientUtil.baseUri)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(urls.get("login"));
    }

    @Then("Статус ответа для неудачной аутентификации должен быть {int}")
    public void checkFailedAuthStatus(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }
}
