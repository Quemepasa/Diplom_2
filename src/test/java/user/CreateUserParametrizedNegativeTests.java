package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CreateUserErrorResponse;
import models.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.Boolean.FALSE;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.*;

@RunWith(Parameterized.class)
public class CreateUserParametrizedNegativeTests {
    private final CreateUserRequest createUserRequest;
    private final UserProfile userProfile = new UserProfile();

    public CreateUserParametrizedNegativeTests(String ignoredDescription, CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
    }

    @Parameterized.Parameters(name = "Test data: {0}")
    public static Object[][] getSumData() {
        return new Object[][] {
                { "Registration without email", userWithoutEmail() },
                { "Registration without password", userWithoutPassword() },
                { "Registration without name", userWithoutName() },
        };
    }

    @DisplayName("Create user without required field")
    @Test
    public void createUserWithoutRequiredField() {
        Response response = userProfile.create(createUserRequest);

        CreateUserErrorResponse createUserErrorResponse = response.as(CreateUserErrorResponse.class);

        assertEquals("Invalid status code", SC_FORBIDDEN, response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, createUserErrorResponse.isSuccess());
    }
}
