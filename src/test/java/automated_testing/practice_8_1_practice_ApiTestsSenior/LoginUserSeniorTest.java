package automated_testing.practice_8_1_practice_ApiTestsSenior;

import automated_testing.practice_8_2_homework_ApiTestsSenior.BaseSeniorTest;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.LoginUserRequest;
import automated_testing_api_senior.models.LoginUserResponse;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.skelethon.requesters.ValidatedCrudRequester;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_api_senior.specs.ResponseSpecs;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class LoginUserSeniorTest extends BaseSeniorTest {

    @Test
    public void adminCanGenerateAuthTokenTest() {
        LoginUserRequest userRequest = LoginUserRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new ValidatedCrudRequester<LoginUserResponse>(RequestSpecs.unAuthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(userRequest);
    }

    @Test
    public void userCanGenerateAuthTokenTest() {
        CreateUserRequest userRequest = AdminSteps.createUser();

        new CrudRequester(RequestSpecs.unAuthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(userRequest.getUsername()).password(userRequest.getPassword()).build())
                .header("Authorization", Matchers.notNullValue());
    }
}
