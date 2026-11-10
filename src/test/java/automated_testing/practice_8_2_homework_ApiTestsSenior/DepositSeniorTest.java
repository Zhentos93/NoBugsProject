package automated_testing.practice_8_2_homework_ApiTestsSenior;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.DepositRequest;
import automated_testing_api_senior.models.comparison.ModelAssertions;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_api_senior.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DepositSeniorTest extends BaseSeniorTest {

    public static Stream<Arguments> minimumDepositAmounts() {
        return Stream.of(
                Arguments.of(0.01),
                Arguments.of(0.011)
        );
    }

    @MethodSource("minimumDepositAmounts")
    @ParameterizedTest
    public void shouldAllowAuthorizedUserToDepositMinimumAmount(double amount) {
        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();

        // проверяем успешное пополнение счёта на переданную сумму
        new CrudRequester(userSpec, Endpoint.DEPOSIT, ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);
        CreateAccountResponse actualAccount = AccountSteps.findAccountById(accounts, account.getId());

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        ModelAssertions.assertThatModels(depositRequest, actualAccount).match();
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
        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();

        // проверяем успешное пополнение счёта на переданную сумму
        new CrudRequester(userSpec, Endpoint.DEPOSIT, ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);
        CreateAccountResponse actualAccount = AccountSteps.findAccountById(accounts, account.getId());

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(accounts.length).isEqualTo(1);
        ModelAssertions.assertThatModels(depositRequest, actualAccount).match();
    }

    @Test
    public void shouldPreventAuthorizedUserFromDepositingZeroAmount() {
        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(0)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new CrudRequester(userSpec, Endpoint.DEPOSIT, ResponseSpecs.requestReturnsBadRequest())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        CreateAccountResponse actualAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(userSpec),
                account.getId());

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(actualAccount.getBalance()).isEqualTo(0.0);
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
        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);

        // подготавливаем данные для пополнения созданного счёта
        DepositRequest depositRequest = DepositRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new CrudRequester(userSpec, Endpoint.DEPOSIT, ResponseSpecs.requestReturnsBadRequest())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        CreateAccountResponse actualAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(userSpec),
                account.getId());

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(actualAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    public void shouldPreventAuthorizedUserFromFundingNonExistentDeposit() {
        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);
        double amount = RandomData.getValidDepositAmount();

        // подготавливаем данные для пополнения несуществующего счёта пользователя
        DepositRequest depositRequest = DepositRequest.builder()
                .id(RandomData.getNonExistentAccountId())
                .balance(amount)
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new CrudRequester(userSpec, Endpoint.DEPOSIT, ResponseSpecs.requestReturnsForbidden())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения
        CreateAccountResponse actualAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(userSpec),
                account.getId());

        // проверяем, что у пользователя сохранился единственный созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(actualAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    public void shouldPreventAuthorizedUserFromDepositingFundsIntoAnotherAccountsDeposit() {
        // проверяем создание админом пользователя, от имени которого будем пополнять счёт
        CreateUserRequest firstUser = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя, от имени которого будем пополнять счёт
        // и сохраняем спецификацию с его токеном
        RequestSpecification firstUserSpec = RequestSpecs.authAsUser(
                firstUser.getUsername(),
                firstUser.getPassword());

        // проверяем создание счёта авторизованным пользователем, от имени которого будем пополнять счёт
        // и сохраняем данные созданного счёта
        CreateAccountResponse firstUserAccount = AccountSteps.createAccount(firstUserSpec);

        // проверяем создание админом пользователя, чей счёт будем пополнять
        CreateUserRequest secondUser = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя, чей счёт будем пополнять
        RequestSpecification secondUserSpec = RequestSpecs.authAsUser(
                secondUser.getUsername(),
                secondUser.getPassword());

        // проверяем создание счёта авторизованным пользователем, чей счёт будем пополнять
        CreateAccountResponse secondUserAccount = AccountSteps.createAccount(secondUserSpec);

        // подготавливаем запрос с данными на пополнение счёта другого пользователя
        DepositRequest depositRequest = DepositRequest.builder()
                .id(secondUserAccount.getId())
                .balance(RandomData.getValidDepositAmount())
                .build();

        // проверяем неуспешное пополнение счёта на переданную сумму
        new CrudRequester(firstUserSpec, Endpoint.DEPOSIT, ResponseSpecs.requestReturnsForbidden())
                .post(depositRequest);

        // запрашиваем актуальные счета пользователя после пополнения, от имени которого пытались пополнить счёт
        CreateAccountResponse actualFirstUserAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(firstUserSpec),
                firstUserAccount.getId());

        // запрашиваем актуальные счета пользователя после пополнения, чей счёт пытались пополнять
        CreateAccountResponse actualSecondUserAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(secondUserSpec),
                secondUserAccount.getId());

        // проверяем, что у пользователя, чей счёт пытались пополнить, сохранился единственный
        // созданный счёт с ожидаемыми ID и балансом
        softly.assertThat(actualFirstUserAccount.getBalance()).isEqualTo(0.0);
        softly.assertThat(actualSecondUserAccount.getBalance()).isEqualTo(0.0);
    }
}