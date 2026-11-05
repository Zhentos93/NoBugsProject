package automated_testing_api_middle.requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class GetProfileRequester {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;

    public GetProfileRequester(RequestSpecification requestSpecification,
                               ResponseSpecification responseSpecification) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
