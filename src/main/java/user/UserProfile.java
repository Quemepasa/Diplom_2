package user;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.CreateUserSuccessResponse;

import static io.restassured.RestAssured.given;

public class UserProfile {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String CREATE_URL = "api/auth/register";
    private static final String DELETE_URL = "api/auth/user";

    public UserProfile() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step("Create user")
    public Response create(CreateUserRequest createUserRequest) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createUserRequest)
                .when()
                .post(CREATE_URL);
    }

    @Step("Delete user")
    public void delete(CreateUserSuccessResponse createUserSuccessResponse, CreateUserRequest createUserRequest) {
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(createUserSuccessResponse.getAccessToken().replace("Bearer ", ""))
                .and()
                .body(createUserRequest)
                .when()
                .delete(DELETE_URL);
    }
}
