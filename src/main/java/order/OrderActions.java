package order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import user.models.UserSuccessResponse;

import static io.restassured.RestAssured.given;

public class OrderActions {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String ORDERS_URL = "api/orders";

    public OrderActions() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step("Create order with auth token")
    public Response createOrderWithAuth(UserSuccessResponse userSuccessResponse, String json) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(userSuccessResponse.getAccessToken().replace("Bearer ", ""))
                .and()
                .body(json)
                .when()
                .post(ORDERS_URL);
    }

    @Step("Create order without auth token")
    public Response createOrderWithoutAuth(String json) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(ORDERS_URL);
    }

    @Step("Get orders with auth token")
    public Response getOrdersWithAuth(UserSuccessResponse userSuccessResponse) {
        return given()
                .auth().oauth2(userSuccessResponse.getAccessToken().replace("Bearer ", ""))
                .when()
                .get(ORDERS_URL);
    }

    @Step("Get orders without auth token")
    public Response getOrdersWithoutAuth() {
        return given()
                .get(ORDERS_URL);
    }
}
