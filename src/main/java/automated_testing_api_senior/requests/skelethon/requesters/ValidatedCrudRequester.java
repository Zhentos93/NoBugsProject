package automated_testing_api_senior.requests.skelethon.requesters;

import automated_testing_api_senior.models.BaseModel;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.HttpRequest;
import automated_testing_api_senior.requests.skelethon.interfaces.CrudEndpointInterface;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ValidatedCrudRequester<T extends BaseModel> extends HttpRequest implements CrudEndpointInterface {
    private CrudRequester crudRequester;

    public ValidatedCrudRequester(RequestSpecification requestSpecification, Endpoint endpoint,
                                  ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
        this.crudRequester = new CrudRequester(requestSpecification, endpoint, responseSpecification);
    }

    public T post() {
        return post(null);
    }

    @Override
    public T post(BaseModel model) {
        return (T) crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    public Object get(long id) {
        return null;
    }
}
