package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.UserSuccessResponse;
import models.LoginUserRequest;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUserLogin;

public class LoginUserNegativeTest {
    private final UserProfile userProfile = new UserProfile();

    @DisplayName("Login with invalid data")
    @Test
    public void loginWithInvalidData() {
        LoginUserRequest loginUserRequest = randomUserLogin();
        Response response = userProfile.login(loginUserRequest);

        UserSuccessResponse userSuccessResponse = response.as(UserSuccessResponse.class);

        assertEquals("Invalid status code", SC_UNAUTHORIZED, response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, userSuccessResponse.isSuccess());
    }
}
