package automated_testing.practice_7_2_homework_ApiTestsMiddle;

import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.AccountResponse;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.DepositRequest;
import automated_testing_api_middle.models.TransferRequest;
import automated_testing_api_middle.models.UserRole;
import automated_testing_api_middle.requests.AdminCreateUserRequester;
import automated_testing_api_middle.requests.CreateAccountRequester;
import automated_testing_api_middle.requests.DepositRequester;
import automated_testing_api_middle.requests.GetAccountsRequester;
import automated_testing_api_middle.requests.TransferRequester;
import automated_testing_api_middle.specs.RequestSpecs;
import automated_testing_api_middle.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.within;

public class TransferMiddleTest extends BaseMiddleTest {

    @Test
    public void shouldAllowAuthorizedUserToTransferMinimumAmountBetweenOwnAccounts() {

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

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        AccountResponse senderAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        AccountResponse receiverAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        double initialBalance = RandomData.getAmount(100.0, 5000.0);
        double transferAmount = 0.01;

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(initialBalance)
                        .build());

        // проверяем успешный перевод на переданную сумму
        new TransferRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(TransferRequest.builder()
                        .senderAccountId(senderAccount.getId())
                        .receiverAccountId(receiverAccount.getId())
                        .amount(transferAmount)
                        .build());

        // запрашиваем актуальные счета пользователя после перевода
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // находим счета пользователя в актуальном списке счетов по их ID
        AccountResponse actualSenderAccount = findAccountById(accounts, senderAccount.getId());
        AccountResponse actualReceiverAccount = findAccountById(accounts, receiverAccount.getId());

        // проверяем списание суммы с счёта, с которого переводили и её зачисление на счёт, на который переводили
        softly.assertThat(actualSenderAccount.getBalance())
                .isCloseTo(initialBalance - transferAmount, within(0.000001));
        softly.assertThat(actualReceiverAccount.getBalance())
                .isCloseTo(transferAmount, within(0.000001));
    }

    @Test
    public void shouldAllowAuthorizedUserToTransferMinimumAmountToAnotherUsersAccount() {

        // подготавливаем данные нового пользователя-отправителя
        CreateUserRequest senderUserRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя-отправителя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(senderUserRequest);

        // проверяем авторизацию созданного пользователя-отправителя и сохраняем спецификацию с его токеном
        RequestSpecification senderUserSpec = RequestSpecs.authAsUser(
                senderUserRequest.getUsername(),
                senderUserRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем-отправителем и сохраняем данные созданного счёта
        AccountResponse senderAccount = new CreateAccountRequester(
                senderUserSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // подготавливаем данные нового пользователя-получателя
        CreateUserRequest receiverUserRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя-получателя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(receiverUserRequest);

        // проверяем авторизацию созданного пользователя-получателя и сохраняем спецификацию с его токеном
        RequestSpecification receiverUserSpec = RequestSpecs.authAsUser(
                receiverUserRequest.getUsername(),
                receiverUserRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем-получателем и сохраняем данные созданного счёта
        AccountResponse receiverAccount = new CreateAccountRequester(
                receiverUserSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        double initialBalance = RandomData.getAmount(100.0, 5000.0);
        double transferAmount = 0.01;

        // проверяем успешное пополнение счёта-отправителя на переданную сумму
        new DepositRequester(
                senderUserSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(initialBalance)
                        .build());

        // проверяем успешный перевод денег получателю на переданную сумму
        new TransferRequester(
                senderUserSpec,
                ResponseSpecs.requestReturnsOK())
                .post(TransferRequest.builder()
                        .senderAccountId(senderAccount.getId())
                        .receiverAccountId(receiverAccount.getId())
                        .amount(transferAmount)
                        .build());

        // находим счета отправителя и получателя в актуальном списке счетов по их ID
        AccountResponse[] senderAccounts = new GetAccountsRequester(
                senderUserSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        AccountResponse[] receiverAccounts = new GetAccountsRequester(
                receiverUserSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // проверяем списание суммы со счёта отправителя и её зачисление на счёт получателя
        softly.assertThat(senderAccounts[0].getBalance())
                .isCloseTo(initialBalance - transferAmount, within(0.000001));
        softly.assertThat(receiverAccounts[0].getBalance())
                .isCloseTo(transferAmount, within(0.000001));
    }

    public static Stream<Arguments> maximumTransferAmounts() {
        return Stream.of(
                Arguments.of(9999.99, 0.01, 9999.99),
                Arguments.of(10000.0, 0.0, 10000.0)
        );
    }

    @MethodSource("maximumTransferAmounts")
    @ParameterizedTest
    public void shouldAllowAuthorizedUserToTransferMaximumAmount(
            double amount,
            double expectedSenderBalance,
            double expectedReceiverBalance) {

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

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        AccountResponse senderAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        AccountResponse receiverAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(5000)
                        .build());

        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(5000)
                        .build());

        // проверяем успешный перевод на переданную сумму
        new TransferRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(TransferRequest.builder()
                        .senderAccountId(senderAccount.getId())
                        .receiverAccountId(receiverAccount.getId())
                        .amount(amount)
                        .build());

        // запрашиваем актуальные счета пользователя после перевода
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // находим счета пользователя в актуальном списке счетов по их ID
        AccountResponse actualSenderAccount = findAccountById(accounts, senderAccount.getId());
        AccountResponse actualReceiverAccount = findAccountById(accounts, receiverAccount.getId());

        // проверяем списание суммы с счёта, с которого переводили и её зачисление на счёт, на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isCloseTo(expectedSenderBalance, within(0.000001));
        softly.assertThat(actualReceiverAccount.getBalance()).isCloseTo(expectedReceiverBalance, within(0.000001));
    }

    @Test
    public void shouldPreventAuthorizedUserFromTransferringZeroAmount() {

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

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        AccountResponse senderAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        AccountResponse receiverAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        double initialBalance = RandomData.getAmount(100.0, 5000.0);

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(initialBalance)
                        .build());

        // проверяем неуспешный перевод на переданную сумму
        new TransferRequester(
                userSpec,
                ResponseSpecs.requestReturnsBadRequest())
                .post(TransferRequest.builder()
                        .senderAccountId(senderAccount.getId())
                        .receiverAccountId(receiverAccount.getId())
                        .amount(0)
                        .build());

        // запрашиваем актуальные счета пользователя после перевода
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // находим счета пользователя в актуальном списке счетов по их ID
        AccountResponse actualSenderAccount = findAccountById(accounts, senderAccount.getId());
        AccountResponse actualReceiverAccount = findAccountById(accounts, receiverAccount.getId());

        // проверяем неуспешное списание суммы с счёта, с которого переводили и её неуспешное зачисление на счёт,
        // на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isCloseTo(initialBalance, within(0.000001));
        softly.assertThat(actualReceiverAccount.getBalance()).isCloseTo(0.0, within(0.000001));
    }

    public static Stream<Arguments> transferAmountsOutsideAllowedRange() {
        return Stream.of(
                Arguments.of(0.0099),
                Arguments.of(10000.01)
        );
    }

    @MethodSource("transferAmountsOutsideAllowedRange")
    @ParameterizedTest
    public void shouldPreventAuthorizedUserFromTransferringAmountOutsideAllowedRange(double amount) {

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

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        AccountResponse senderAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        AccountResponse receiverAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(5000)
                        .build());

        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(5000)
                        .build());

        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(5000)
                        .build());

        // проверяем неуспешный перевод на переданную сумму
        new TransferRequester(
                userSpec,
                ResponseSpecs.requestReturnsBadRequest())
                .post(TransferRequest.builder()
                        .senderAccountId(senderAccount.getId())
                        .receiverAccountId(receiverAccount.getId())
                        .amount(amount)
                        .build());

        // запрашиваем актуальные счета пользователя после перевода
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // находим счета пользователя в актуальном списке счетов по их ID
        AccountResponse actualSenderAccount = findAccountById(accounts, senderAccount.getId());
        AccountResponse actualReceiverAccount = findAccountById(accounts, receiverAccount.getId());

        // проверяем неуспешное списание суммы с счёта, с которого переводили и её неуспешное зачисление на счёт,
        // на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(15000.0);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    public void shouldPreventAuthorizedUserFromTransferringAmountDueToInsufficientFunds() {

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

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        AccountResponse senderAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        AccountResponse receiverAccount = new CreateAccountRequester(
                userSpec,
                ResponseSpecs.entityWasCreated())
                .post()
                .extract()
                .as(AccountResponse.class);

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        new DepositRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .post(DepositRequest.builder()
                        .id(senderAccount.getId())
                        .balance(5000)
                        .build());

        // проверяем неуспешный перевод на переданную сумму
        new TransferRequester(
                userSpec,
                ResponseSpecs.requestReturnsBadRequest())
                .post(TransferRequest.builder()
                        .senderAccountId(senderAccount.getId())
                        .receiverAccountId(receiverAccount.getId())
                        .amount(5100)
                        .build());

        // запрашиваем актуальные счета пользователя после перевода
        AccountResponse[] accounts = new GetAccountsRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        // находим счета пользователя в актуальном списке счетов по их ID
        AccountResponse actualSenderAccount = findAccountById(accounts, senderAccount.getId());
        AccountResponse actualReceiverAccount = findAccountById(accounts, receiverAccount.getId());

        // проверяем неуспешное списание суммы с счёта, с которого переводили и её неуспешное зачисление на счёт,
        // на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(5000.0);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(0.0);
    }

    private AccountResponse findAccountById(AccountResponse[] accounts, long accountId) {
        for (AccountResponse account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }

        throw new AssertionError("Account was not found: " + accountId);
    }
}