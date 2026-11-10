package automated_testing.practice_7_1_practice_ApiTestsMiddle;

import automated_testing.practice_7_2_homework_ApiTestsMiddle.BaseMiddleTest;
import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.AccountResponse;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.UserRole;
import automated_testing_api_middle.requests.AdminCreateUserRequester;
import automated_testing_api_middle.requests.CreateAccountRequester;
import automated_testing_api_middle.requests.GetAccountsRequester;
import automated_testing_api_middle.specs.RequestSpecs;
import automated_testing_api_middle.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class CreateAccountMiddleTest extends BaseMiddleTest {

    @Test
    public void userCanCreateAccountTest() {

        // подготавливаем данные нового пользователя
        CreateUserRequest user = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(RequestSpecs.adminSpec(), ResponseSpecs.entityWasCreated())
                .post(user);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание счёта авторизованным пользователем
        new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .post();

        // запрашиваем актуальные счета пользователя после пополнения
        new GetAccountsRequester(userSpec, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

    }
}