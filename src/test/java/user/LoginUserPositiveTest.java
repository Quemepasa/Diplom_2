package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import user.models.CreateUserRequest;
import user.models.UserSuccessResponse;
import user.models.LoginUserRequest;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUser;

public class LoginUserPositiveTest {
    private CreateUserRequest createUserRequest;
    private UserSuccessResponse userSuccessResponse;
    private final UserActions userActions = new UserActions();

    @DisplayName("Login under existing user")
    @Test
    public void loginUnderExistingUser() {
        createUserRequest = randomUser();
        userActions.createUser(createUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest(createUserRequest.getEmail(), createUserRequest.getPassword());
        Response response = userActions.login(loginUserRequest);

        userSuccessResponse = response.as(UserSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, userSuccessResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userActions.deleteUser(userSuccessResponse, createUserRequest);
    }
}
