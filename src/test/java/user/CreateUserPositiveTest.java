package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import user.models.CreateUserRequest;
import user.models.UserSuccessResponse;
import org.junit.After;
import org.junit.Test;

import static java.lang.Boolean.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUser;

public class CreateUserPositiveTest {
    private CreateUserRequest createUserRequest;
    private UserSuccessResponse userSuccessResponse;
    private final UserActions userActions = new UserActions();

    @DisplayName("Create user with all fields")
    @Test
    public void createUserWithAllFields() {
        createUserRequest = randomUser();
        Response response = userActions.createUser(createUserRequest);

        userSuccessResponse = response.as(UserSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, userSuccessResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userActions.deleteUser(userSuccessResponse, createUserRequest);
    }
}
