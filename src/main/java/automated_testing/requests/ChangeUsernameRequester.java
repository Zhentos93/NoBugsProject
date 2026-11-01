package automated_testing.requests;

import automated_testing.models.ChangeUsernameRequest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ChangeUsernameRequester {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;

    public ChangeUsernameRequester(RequestSpecification requestSpecification,
                                   ResponseSpecification responseSpecification) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
    }

    public ValidatableResponse put(ChangeUsernameRequest model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .put("/api/v1/customer/profile")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
