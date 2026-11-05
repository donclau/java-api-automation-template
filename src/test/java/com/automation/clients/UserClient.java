package com.automation.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class UserClient {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
    private static final String USERS_ENDPOINT = "/users";

    public Response getUser(int userId) {

        return RestAssured
                .given()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .when()
                .get(USERS_ENDPOINT + "/" + userId)
                .then()
                .extract()
                .response();
    }

    public Response createUser(Map<String, String> userData) {

        return RestAssured
                .given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userData)
                .when()
                .post(USERS_ENDPOINT)
                .then()
                .extract()
                .response();
    }
}