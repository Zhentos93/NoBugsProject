package automated_testing.practice_7_1_practice_ApiTestsMiddle;

import automated_testing.practice_7_2_homework_ApiTestsMiddle.BaseMiddleTest;
import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.LoginUserRequest;
import automated_testing_api_middle.models.UserRole;
import automated_testing_api_middle.requests.AdminCreateUserRequester;
import automated_testing_api_middle.requests.LoginUserRequester;
import automated_testing_api_middle.specs.RequestSpecs;
import automated_testing_api_middle.specs.ResponseSpecs;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class LoginUserMiddleTest extends BaseMiddleTest {

    @Test
    public void adminCanGenerateAuthTokenTest() {
        LoginUserRequest userRequest = LoginUserRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new LoginUserRequester(RequestSpecs.unauthSpec(),
                ResponseSpecs.requestReturnsOK())
                .post(userRequest);
    }

    @Test
    public void userCanGenerateAuthTokenTest() {
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        new LoginUserRequester(RequestSpecs.unauthSpec(),
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(userRequest.getUsername()).password(userRequest.getPassword()).build())
                .header("Authorization", Matchers.notNullValue());
    }
}
