
package com.automation.steps;

import com.automation.clients.UserClient;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserSteps {

    private final UserClient userClient = new UserClient();

    private Response response;

    @When("I request the user with ID {int}")
    public void getUser(int userId) {

        response = userClient.getUser(userId);
    }

    @When("I create a user with the following information")
    public void createUser(DataTable dataTable) {

        Map<String, String> userData =
                dataTable.asMap(String.class, String.class);

        response = userClient.createUser(userData);
    }

    @Then("the response status code should be {int}")
    public void validateStatusCode(int expectedStatusCode) {

        assertEquals(
                expectedStatusCode,
                response.statusCode()
        );
    }

    @Then("the user name should be {string}")
    public void validateUserName(String expectedName) {

        assertEquals(
                expectedName,
                response.jsonPath().getString("name")
        );
    }

    @Then("the user email should be {string}")
    public void validateUserEmail(String expectedEmail) {

        assertEquals(
                expectedEmail,
                response.jsonPath().getString("email")
        );
    }

    @Then("the response should contain the user name {string}")
    public void validateCreatedUser(String expectedName) {

        assertNotNull(response.jsonPath().get("id"));

        assertEquals(
                expectedName,
                response.jsonPath().getString("name")
        );
    }
}
