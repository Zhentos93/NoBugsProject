package automated_testing.practice_7_1_practice_ApiTestsMiddle;

import automated_testing.practice_7_2_homework_ApiTestsMiddle.BaseMiddleTest;
import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.UserRole;
import automated_testing_api_middle.requests.AdminCreateUserRequester;
import automated_testing_api_middle.requests.CreateAccountRequester;
import automated_testing_api_middle.specs.RequestSpecs;
import automated_testing_api_middle.specs.ResponseSpecs;
import org.junit.jupiter.api.Test;

public class CreateAccountMiddleTest extends BaseMiddleTest {

    @Test
    public void userCanCreateAccountTest() {
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        new CreateAccountRequester(RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword()),
                ResponseSpecs.entityWasCreated())
                .post(null);

        // запросить все аккаунты пользователя и проверить, что наш аккаунт там
    }
}
