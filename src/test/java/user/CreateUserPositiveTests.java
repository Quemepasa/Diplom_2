package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.CreateUserSuccessResponse;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUser;

public class CreateUserPositiveTests {
    private CreateUserRequest createUserRequest;
    private CreateUserSuccessResponse createUserSuccessResponse;
    private final UserProfile userProfile = new UserProfile();

    @DisplayName("Create user with all fields")
    @Test
    public void createUserWithAllFields() {
        createUserRequest = randomUser();
        Response response = userProfile.create(createUserRequest);

        createUserSuccessResponse = response.as(CreateUserSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, createUserSuccessResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userProfile.delete(createUserSuccessResponse, createUserRequest);
    }
}
