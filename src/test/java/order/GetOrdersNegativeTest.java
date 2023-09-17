package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.models.OrderErrorResponse;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.assertEquals;

public class GetOrdersNegativeTest {
    private final OrderActions orderActions = new OrderActions();

    @DisplayName("Create order with auth token and ingredients")
    @Test
    public void createOrderWithAuthTokenAndIngredients(){
        Response response = orderActions.getOrdersWithoutAuth();
        OrderErrorResponse orderErrorResponse = response.as(OrderErrorResponse.class);

        assertEquals("Invalid status code", SC_UNAUTHORIZED, response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, orderErrorResponse.isSuccess());
    }
}
