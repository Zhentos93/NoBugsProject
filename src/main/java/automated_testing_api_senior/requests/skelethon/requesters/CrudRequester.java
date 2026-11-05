package automated_testing_api_senior.requests.skelethon.requesters;

import automated_testing_api_senior.models.BaseModel;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.HttpRequest;
import automated_testing_api_senior.requests.skelethon.interfaces.CrudEndpointInterface;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CrudRequester extends HttpRequest implements CrudEndpointInterface {
    public CrudRequester(
            RequestSpecification requestSpecification,
            Endpoint endpoint,
            ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
    }

    public ValidatableResponse post() {
        return post(null);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        Object body = model == null ? "" : model;

        return given()
                .spec(requestSpecification)
                .body(body)
                .post(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    public ValidatableResponse put(BaseModel model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .put(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public Object get(long id) {
        return null;
    }
}
