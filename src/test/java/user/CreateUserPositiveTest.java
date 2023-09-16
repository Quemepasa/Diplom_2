package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.UserSuccessResponse;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUserForRegistration;

public class CreateUserPositiveTest {
    private CreateUserRequest createUserRequest;
    private UserSuccessResponse userSuccessResponse;
    private final UserProfile userProfile = new UserProfile();

    @DisplayName("Create user with all fields")
    @Test
    public void createUserWithAllFields() {
        createUserRequest = randomUserForRegistration();
        Response response = userProfile.create(createUserRequest);

        userSuccessResponse = response.as(UserSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, userSuccessResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userProfile.delete(userSuccessResponse, createUserRequest);
    }
}
