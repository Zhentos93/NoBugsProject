package automated_testing_api_senior.requests.steps;

import automated_testing_api_senior.generators.RandomModelGenerator;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.CreateUserResponse;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.ValidatedCrudRequester;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_api_senior.specs.ResponseSpecs;

public class AdminSteps {
    public static CreateUserRequest createUser() {
        CreateUserRequest userRequest = RandomModelGenerator.generate(CreateUserRequest.class);

        new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.adminSpec(),
                Endpoint.ADMIN_USER,
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        return userRequest;
    }
}
