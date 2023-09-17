package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.models.OrderSuccessResponse;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class CreateOrderWithoutAuthPositiveTest {
    private final OrderActions orderActions = new OrderActions();

    @DisplayName("Create order with ingredients and without auth token")
    @Test
    public void createOrderWithIngredientsAndWithoutAuthToken(){
        String json = "{\"ingredients\": [\"61c0c5a71d1f82001bdaaa79\", \"61c0c5a71d1f82001bdaaa76\"]}";
        Response response = orderActions.createOrderWithoutAuth(json);
        OrderSuccessResponse orderSuccessResponse = response.as(OrderSuccessResponse.class);

        assertEquals("Invalid status code", SC_OK, response.statusCode());
        assertEquals("The response body must contain \"success\": true", TRUE, orderSuccessResponse.isSuccess());
    }
}
