package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import user.models.UserErrorResponse;
import user.models.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.Boolean.FALSE;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.*;

@RunWith(Parameterized.class)
public class CreateUserParametrizedNegativeTest {
    private final CreateUserRequest createUserRequest;
    private final UserActions userActions = new UserActions();

    public CreateUserParametrizedNegativeTest(String ignoredDescription, CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
    }

    @Parameterized.Parameters(name = "Test data: {0}")
    public static Object[][] getSumData() {
        return new Object[][] {
                { "User without email", randomUserWithoutEmail() },
                { "User without password", randomUserWithoutPassword() },
                { "User without name", randomUserWithoutName() },
        };
    }

    @DisplayName("Create user without required field")
    @Test
    public void createUserWithoutRequiredField() {
        Response response = userActions.createUser(createUserRequest);

        UserErrorResponse userErrorResponse = response.as(UserErrorResponse.class);

        assertEquals("Invalid status code", SC_FORBIDDEN, response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, userErrorResponse.isSuccess());
    }
}
