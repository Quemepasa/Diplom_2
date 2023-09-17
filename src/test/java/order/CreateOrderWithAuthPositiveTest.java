package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.models.OrderSuccessResponse;
import org.junit.After;
import org.junit.Test;
import user.UserActions;
import user.models.CreateUserRequest;
import user.models.LoginUserRequest;
import user.models.UserSuccessResponse;

import static java.lang.Boolean.TRUE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static user.UserGenerator.randomUser;

public class CreateOrderWithAuthPositiveTest {
    private CreateUserRequest createUserRequest;
    private UserSuccessResponse userSuccessResponse;
    private final UserActions userActions = new UserActions();
    private final OrderActions orderActions = new OrderActions();

    @DisplayName("Create order with auth token and ingredients")
    @Test
    public void createOrderWithAuthTokenAndIngredients(){
        createUserRequest = randomUser();
        userActions.createUser(createUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest(createUserRequest.getEmail(), createUserRequest.getPassword());
        Response login_response = userActions.login(loginUserRequest);

        userSuccessResponse = login_response.as(UserSuccessResponse.class);

        String json = "{\"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\", \"61c0c5a71d1f82001bdaaa76\"]}";
        Response create_order_response = orderActions.createOrderWithAuth(userSuccessResponse, json);
        OrderSuccessResponse orderSuccessResponse = create_order_response.as(OrderSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, create_order_response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, orderSuccessResponse.isSuccess());
    }

    @After
    public void tearDown() {
        userActions.deleteUser(userSuccessResponse, createUserRequest);
    }
}
