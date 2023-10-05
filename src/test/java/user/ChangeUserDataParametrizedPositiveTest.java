package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import user.models.ChangeUserDataRequest;
import user.models.CreateUserRequest;
import user.models.LoginUserRequest;
import user.models.UserSuccessResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.Boolean.TRUE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.*;

@RunWith(Parameterized.class)
public class ChangeUserDataParametrizedPositiveTest {
    private static final CreateUserRequest createUserRequest = randomUser();
    private UserSuccessResponse userSuccessResponse;
    private final UserActions userActions = new UserActions();
    private final ChangeUserDataRequest changeUserDataRequest;

    public ChangeUserDataParametrizedPositiveTest(String ignoredDescription, ChangeUserDataRequest changeUserDataRequest) {
        this.changeUserDataRequest = changeUserDataRequest;
    }

    @Parameterized.Parameters(name = "Test data: {0}")
    public static Object[][] getSumData() {
        return new Object[][] {
                { "New user email", changeUserEmail(createUserRequest) },
                { "New user password", changeUserPassword(createUserRequest) },
                { "New user name", changeUserName(createUserRequest) },
        };
    }

    @DisplayName("Change user data with auth token")
    @Test
    public void changeUserDataWithAuthToken() {
        userActions.createUser(createUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest(createUserRequest.getEmail(), createUserRequest.getPassword());
        Response login_response = userActions.login(loginUserRequest);

        userSuccessResponse = login_response.as(UserSuccessResponse.class);

        Response change_data_response = userActions.changeDataWithAuth(userSuccessResponse, changeUserDataRequest);

        userSuccessResponse = change_data_response.as(UserSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, change_data_response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, userSuccessResponse.isSuccess());

        // Overwritten to further delete the user from the database
        userSuccessResponse = login_response.as(UserSuccessResponse.class);
    }

    @After
    public void tearDown() {
        userActions.deleteUser(userSuccessResponse, changeUserDataRequest);
    }
}
