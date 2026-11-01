package automated_testing.practice_7_2_homework_ApiTestsMiddle;

import automated_testing.generators.RandomData;
import automated_testing.models.AccountResponse;
import automated_testing.models.CreateUserRequest;
import automated_testing.models.DepositRequest;
import automated_testing.models.UserRole;
import automated_testing.requests.AdminCreateUserRequester;
import automated_testing.requests.CreateAccountRequester;
import automated_testing.requests.DepositRequester;
import automated_testing.requests.GetAccountsRequester;
import automated_testing.specs.RequestSpecs;
import automated_testing.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DepositMiddleTest extends BaseTest {

    public static Stream<Arguments> minimumDepositAmounts() {
        return Stream.of(
                Arguments.of(0.01),
                Arguments.of(0.011)
        );
    }

    @MethodSource("minimumDepositAmounts")
    @ParameterizedTest
    public void shouldAllowAuthorizedUserToDepositMinimumAmount(double amount) {

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        AccountResponse account = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();

        // проверяем успешное пополнение счёта на переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        softly.assertThat(accounts[0].getId()).isEqualTo(account.getId());
        softly.assertThat(accounts[0].getBalance()).isEqualTo(amount);
    }

    public static Stream<Arguments> maximumDepositAmounts() {
        return Stream.of(
                Arguments.of(4999.99),
                Arguments.of(5000.0)
        );
    }

    @MethodSource("maximumDepositAmounts")
    @ParameterizedTest
    public void shouldAllowAuthorizedUserToDepositMaximumAmount(double amount) {

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        AccountResponse account = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();

        // проверяем успешное пополнение счёта на переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        softly.assertThat(accounts[0].getId()).isEqualTo(account.getId());
        softly.assertThat(accounts[0].getBalance()).isEqualTo(amount);
    }

    @Test
    public void shouldPreventAuthorizedUserFromDepositingZeroAmount() {

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        AccountResponse account = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(0)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsBadRequest())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        softly.assertThat(accounts[0].getId()).isEqualTo(account.getId());
        softly.assertThat(accounts[0].getBalance()).isEqualTo(0.0);
    }

    public static Stream<Arguments> depositAmountsOutsideAllowedRange() {
        return Stream.of(
                Arguments.of(0.0099),
                Arguments.of(5000.01)
        );
    }

    @MethodSource("depositAmountsOutsideAllowedRange")
    @ParameterizedTest
    public void shouldPreventAuthorizedUserFromDepositingAmountOutsideAllowedRange(double amount) {

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        AccountResponse account = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsBadRequest())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        softly.assertThat(accounts[0].getId()).isEqualTo(account.getId());
        softly.assertThat(accounts[0].getBalance()).isEqualTo(0.0);
    }

    @Test
    public void shouldPreventAuthorizedUserFromFundingNonExistentDeposit() {

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        AccountResponse account = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные для пополнения несуществующего счёта пользователя
        DepositRequest depositRequest = DepositRequest.builder()
                .id(999_999_999L)
                .balance(100)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsForbidden())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        softly.assertThat(accounts[0].getId()).isEqualTo(account.getId());
        softly.assertThat(accounts[0].getBalance()).isEqualTo(0.0);
    }

    @Test
    public void shouldPreventAuthorizedUserFromDepositingFundsIntoAnotherAccountsDeposit() {

        // подготавливаем данные нового пользователя, от имени которого будем пополнять счёт
        CreateUserRequest firstUserRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя, от имени которого будем пополнять счёт
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(firstUserRequest);

        // проверяем авторизацию созданного пользователя, от имени которого будем пополнять счёт
        // и сохраняем спецификацию с его токеном
        RequestSpecification firstUserSpec = RequestSpecs.authAsUser(
                firstUserRequest.getUsername(),
                firstUserRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем, от имени которого будем пополнять счёт
        // и сохраняем данные созданного счёта
        AccountResponse firstUserAccount = new CreateAccountRequester(
                firstUserSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные нового пользователя, чей счёт будем пополнять
        CreateUserRequest secondUserRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя, чей счёт будем пополнять
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(secondUserRequest);

        // проверяем авторизацию созданного пользователя, чей счёт будем пополнять
        RequestSpecification secondUserSpec = RequestSpecs.authAsUser(
                secondUserRequest.getUsername(),
                secondUserRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем, чей счёт будем пополнять
        AccountResponse secondUserAccount = new CreateAccountRequester(
                secondUserSpec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        // подготавливаем запрос с данными на пополнение счёта другого пользователя
        DepositRequest depositRequest = DepositRequest.builder()
                .id(secondUserAccount.getId())
                .balance(100)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new DepositRequester(
                firstUserSpec,
                ResponseSpecs.requestReturnsForbidden())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения, от имени которого пытались пополнить счёт
        AccountResponse[] firstUserAccounts = new GetAccountsRequester(
                firstUserSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // запрашиваем актуальные счета пользователя после пополнения, чей счёт пытались пополнять
        AccountResponse[] secondUserAccounts = new GetAccountsRequester(
                secondUserSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем, что у пользователя, от имени которого пытались пополнить счёт, сохранился единственный
        // созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(firstUserAccounts.length).isEqualTo(1);
        softly.assertThat(firstUserAccounts[0].getId()).isEqualTo(firstUserAccount.getId());
        softly.assertThat(firstUserAccounts[0].getBalance()).isEqualTo(0.0);

        // проверяем, что у пользователя, чей счёт пытались пополнить, сохранился единственный
        // созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(secondUserAccounts.length).isEqualTo(1);
        softly.assertThat(secondUserAccounts[0].getId()).isEqualTo(secondUserAccount.getId());
        softly.assertThat(secondUserAccounts[0].getBalance()).isEqualTo(0.0);
    }
}
