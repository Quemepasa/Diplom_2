package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.UserSuccessResponse;
import models.LoginUserRequest;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUser;

public class LoginUserPositiveTest {
    private CreateUserRequest createUserRequest;
    private UserSuccessResponse userSuccessResponse;
    private final UserProfile userProfile = new UserProfile();

    @DisplayName("Login under existing user")
    @Test
    public void loginUnderExistingUser() {
        createUserRequest = randomUser();
        userProfile.create(createUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest(createUserRequest.getEmail(), createUserRequest.getPassword());
        Response response = userProfile.login(loginUserRequest);

        userSuccessResponse = response.as(UserSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, userSuccessResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userProfile.delete(userSuccessResponse, createUserRequest);
    }
}
