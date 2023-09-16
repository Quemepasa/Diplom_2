package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUserErrorResponse;
import models.CreateUserRequest;
import models.CreateUserSuccessResponse;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.*;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUser;

public class CreateUserNegativeTests {
    private CreateUserRequest createUserRequest;
    private CreateUserSuccessResponse createUserSuccessResponse;
    private final UserProfile userProfile = new UserProfile();

    @DisplayName("Create user that already exists")
    @Test
    public void createUserThatAlreadyExists() {
        createUserRequest = randomUser();

        Response success_response = userProfile.create(createUserRequest);
        Response error_response = userProfile.create(createUserRequest);

        createUserSuccessResponse = success_response.as(CreateUserSuccessResponse.class);
        CreateUserErrorResponse createUserErrorResponse = error_response.as(CreateUserErrorResponse.class);

        assertEquals("Invalid status code", SC_FORBIDDEN, error_response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, createUserErrorResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userProfile.delete(createUserSuccessResponse, createUserRequest);
    }
}
