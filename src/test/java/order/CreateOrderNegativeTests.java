package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.models.OrderSuccessResponse;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.junit.Assert.assertEquals;

public class CreateOrderNegativeTests {
    private final OrderActions orderActions = new OrderActions();

    @DisplayName("Create order without ingredients")
    @Test
    public void createOrderWithoutIngredients(){
        String json = "{\"ingredients\": []}";
        Response response = orderActions.createOrderWithoutAuth(json);
        OrderSuccessResponse orderSuccessResponse = response.as(OrderSuccessResponse.class);

        assertEquals("Invalid status code", SC_BAD_REQUEST, response.statusCode());
        assertEquals("The response body must contain \"success\": false", FALSE, orderSuccessResponse.isSuccess());
    }

    @DisplayName("Create order without ingredients")
    @Test
    public void createOrderWithInvalidHashOfIngredients(){
        String json = "{\"ingredients\": [\"non-existent hash of the ingredient\"]}";
        Response response = orderActions.createOrderWithoutAuth(json);

        assertEquals("Invalid status code", SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }
}
