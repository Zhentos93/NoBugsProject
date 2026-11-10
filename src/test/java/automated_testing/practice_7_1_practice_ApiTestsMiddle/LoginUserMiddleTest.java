package automated_testing.practice_7_1_practice_ApiTestsMiddle;

import automated_testing.practice_7_2_homework_ApiTestsMiddle.BaseMiddleTest;
import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.HeaderName;
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

        // подготавливаем данные для админа
        LoginUserRequest user = LoginUserRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        // проверяем логин под админом
        new LoginUserRequester(RequestSpecs.unauthSpec(), ResponseSpecs.requestReturnsOK())
                .post(user);
    }

    @Test
    public void userCanGenerateAuthTokenTest() {

        // подготавливаем данные нового пользователя
        CreateUserRequest user = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(RequestSpecs.adminSpec(), ResponseSpecs.entityWasCreated())
                .post(user);

        // проверяем логин под авторизованным пользователем
        new LoginUserRequester(RequestSpecs.unauthSpec(), ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .header(HeaderName.AUTHORIZATION.getValue(), Matchers.notNullValue());
    }
}