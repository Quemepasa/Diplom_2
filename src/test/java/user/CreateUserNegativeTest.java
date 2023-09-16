package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.UserErrorResponse;
import models.UserSuccessResponse;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUserForRegistration;

public class CreateUserNegativeTest {
    private CreateUserRequest createUserRequest;
    private UserSuccessResponse userSuccessResponse;
    private final UserProfile userProfile = new UserProfile();

    @DisplayName("Create user that already exists")
    @Test
    public void createUserThatAlreadyExists() {
        createUserRequest = randomUserForRegistration();

        Response success_response = userProfile.create(createUserRequest);
        Response error_response = userProfile.create(createUserRequest);

        userSuccessResponse = success_response.as(UserSuccessResponse.class);
        UserErrorResponse userErrorResponse = error_response.as(UserErrorResponse.class);

        assertEquals("Invalid status code", SC_FORBIDDEN, error_response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, userErrorResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userProfile.delete(userSuccessResponse, createUserRequest);
    }
}
