package automated_testing.practice_8_2_homework_ApiTestsSenior;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
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

public class TransferSeniorTest extends BaseSeniorTest {

    @Test
    public void shouldAllowAuthorizedUserToTransferMinimumAmountBetweenOwnAccounts() {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        CreateAccountResponse senderAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(userSpec);

        double initialBalance = RandomData.getAmount(100.0, 5000.0);
        double transferAmount = 0.01;

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        AccountSteps.deposit(userSpec, senderAccount.getId(), initialBalance);

        // проверяем успешный перевод на переданную сумму
        AccountSteps.transfer(
                userSpec,
                senderAccount.getId(),
                receiverAccount.getId(),
                transferAmount,
                ResponseSpecs.requestReturnsOK());

        // запрашиваем актуальные счета пользователя после перевода
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);

        // находим счета пользователя в актуальном списке счетов по их ID
        CreateAccountResponse actualSenderAccount = AccountSteps.findAccountById(accounts, senderAccount.getId());
        CreateAccountResponse actualReceiverAccount = AccountSteps.findAccountById(accounts, receiverAccount.getId());

        // проверяем списание суммы с счёта, с которого переводили и её зачисление на счёт, на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(initialBalance - transferAmount);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(transferAmount);
    }

    @Test
    public void shouldAllowAuthorizedUserToTransferMinimumAmountToAnotherUsersAccount() {
        // проверяем создание админом пользователя-отправителя
        CreateUserRequest senderUserRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя-отправителя и сохраняем спецификацию с его токеном
        RequestSpecification senderUserSpec = RequestSpecs.authAsUser(
                senderUserRequest.getUsername(),
                senderUserRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем-отправителем и сохраняем данные созданного счёта
        CreateAccountResponse senderAccount = AccountSteps.createAccount(senderUserSpec);

        // проверяем создание админом пользователя-получателя
        CreateUserRequest receiverUserRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя-получателя и сохраняем спецификацию с его токеном
        RequestSpecification receiverUserSpec = RequestSpecs.authAsUser(
                receiverUserRequest.getUsername(),
                receiverUserRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем-получателем и сохраняем данные созданного счёта
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(receiverUserSpec);

        double initialBalance = RandomData.getAmount(100.0, 5000.0);
        double transferAmount = 0.011;

        // проверяем успешное пополнение счёта-отправителя на переданную сумму
        AccountSteps.deposit(senderUserSpec, senderAccount.getId(), initialBalance);

        // проверяем успешный перевод денег получателю на переданную сумму
        AccountSteps.transfer(
                senderUserSpec,
                senderAccount.getId(),
                receiverAccount.getId(),
                transferAmount,
                ResponseSpecs.requestReturnsOK());

        // находим счета отправителя и получателя в актуальном списке счетов по их ID
        CreateAccountResponse actualSenderAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(senderUserSpec),
                senderAccount.getId());

        CreateAccountResponse actualReceiverAccount = AccountSteps.findAccountById(
                AccountSteps.getAccounts(receiverUserSpec),
                receiverAccount.getId());

        // проверяем списание суммы со счёта отправителя и её зачисление на счёт получателя
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(initialBalance - transferAmount);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(transferAmount);
    }

    public static Stream<Arguments> maximumTransferAmounts() {
        return Stream.of(
                Arguments.of(9999.99),
                Arguments.of(10000.0)
        );
    }

    @MethodSource("maximumTransferAmounts")
    @ParameterizedTest
    public void shouldAllowAuthorizedUserToTransferMaximumAmount(double transferAmount) {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        CreateAccountResponse senderAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(userSpec);

        double firstDeposit = RandomData.getAmount(2500.0, 4999.0);
        double secondDeposit = RandomData.getAmount(5000.01 - firstDeposit, 4999.0);
        double thirdDeposit = 10000.0 - firstDeposit - secondDeposit;

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        AccountSteps.deposit(userSpec, senderAccount.getId(), firstDeposit);
        AccountSteps.deposit(userSpec, senderAccount.getId(), secondDeposit);
        AccountSteps.deposit(userSpec, senderAccount.getId(), thirdDeposit);

        // проверяем успешный перевод на переданную сумму
        AccountSteps.transfer(
                userSpec,
                senderAccount.getId(),
                receiverAccount.getId(),
                transferAmount,
                ResponseSpecs.requestReturnsOK());

        // запрашиваем актуальные счета пользователя после перевода
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);

        // находим счета пользователя в актуальном списке счетов по их ID
        CreateAccountResponse actualSenderAccount = AccountSteps.findAccountById(accounts, senderAccount.getId());
        CreateAccountResponse actualReceiverAccount = AccountSteps.findAccountById(accounts, receiverAccount.getId());

        // проверяем списание суммы с счёта, с которого переводили и её зачисление на счёт, на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(10000.0 - transferAmount);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(transferAmount);
    }

    @Test
    public void shouldPreventAuthorizedUserFromTransferringZeroAmount() {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        CreateAccountResponse senderAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(userSpec);

        double initialBalance = RandomData.getAmount(100.0, 5000.0);

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        AccountSteps.deposit(userSpec, senderAccount.getId(), initialBalance);

        // проверяем неуспешный перевод на переданную сумму
        AccountSteps.transfer(
                userSpec,
                senderAccount.getId(),
                receiverAccount.getId(),
                0,
                ResponseSpecs.requestReturnsBadRequest());

        // запрашиваем актуальные счета пользователя после перевода
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);

        // находим счета пользователя в актуальном списке счетов по их ID
        CreateAccountResponse actualSenderAccount = AccountSteps.findAccountById(accounts, senderAccount.getId());
        CreateAccountResponse actualReceiverAccount = AccountSteps.findAccountById(accounts, receiverAccount.getId());

        // проверяем неуспешное списание суммы с счёта, с которого переводили и её неуспешное зачисление на счёт,
        // на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(initialBalance);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(0.0);
    }

    public static Stream<Arguments> transferAmountsOutsideAllowedRange() {
        return Stream.of(
                Arguments.of(0.0099),
                Arguments.of(10000.01)
        );
    }

    @MethodSource("transferAmountsOutsideAllowedRange")
    @ParameterizedTest
    public void shouldPreventAuthorizedUserFromTransferringAmountOutsideAllowedRange(double transferAmount) {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        CreateAccountResponse senderAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(userSpec);

        double initialBalance;
        if (transferAmount > 10000.0) {
            double firstDeposit = RandomData.getAmount(4000.0, 5000.0);
            double secondDeposit = RandomData.getAmount(4000.0, 5000.0);
            double thirdDeposit = RandomData.getAmount(4000.0, 5000.0);
            initialBalance = firstDeposit + secondDeposit + thirdDeposit;

            AccountSteps.deposit(userSpec, senderAccount.getId(), firstDeposit);
            AccountSteps.deposit(userSpec, senderAccount.getId(), secondDeposit);
            AccountSteps.deposit(userSpec, senderAccount.getId(), thirdDeposit);
        } else {
            initialBalance = RandomData.getAmount(100.0, 5000.0);

            // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
            AccountSteps.deposit(userSpec, senderAccount.getId(), initialBalance);
        }

        // проверяем неуспешный перевод на переданную сумму
        AccountSteps.transfer(
                userSpec,
                senderAccount.getId(),
                receiverAccount.getId(),
                transferAmount,
                ResponseSpecs.requestReturnsBadRequest());

        // запрашиваем актуальные счета пользователя после перевода
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);

        // находим счета пользователя в актуальном списке счетов по их ID
        CreateAccountResponse actualSenderAccount = AccountSteps.findAccountById(accounts, senderAccount.getId());
        CreateAccountResponse actualReceiverAccount = AccountSteps.findAccountById(accounts, receiverAccount.getId());

        // проверяем неуспешное списание суммы с счёта, с которого переводили и её неуспешное зачисление на счёт,
        // на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(initialBalance);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    public void shouldPreventAuthorizedUserFromTransferringAmountDueToInsufficientFunds() {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, с которого будем
        // переводить сумму
        CreateAccountResponse senderAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание счёта авторизованным пользователем и сохраняем данные созданного счёта, на который будем
        // переводить сумму
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(userSpec);

        double initialBalance = RandomData.getAmount(100.0, 4900.0);
        double transferAmount = initialBalance + RandomData.getAmount(0.01, 100.0);

        // проверяем успешное пополнение счёта, с которого будем переводить переданную сумму
        AccountSteps.deposit(userSpec, senderAccount.getId(), initialBalance);

        // проверяем неуспешный перевод на переданную сумму
        AccountSteps.transfer(
                userSpec,
                senderAccount.getId(),
                receiverAccount.getId(),
                transferAmount,
                ResponseSpecs.requestReturnsBadRequest());

        // запрашиваем актуальные счета пользователя после перевода
        CreateAccountResponse[] accounts = AccountSteps.getAccounts(userSpec);

        // находим счета пользователя в актуальном списке счетов по их ID
        CreateAccountResponse actualSenderAccount = AccountSteps.findAccountById(accounts, senderAccount.getId());
        CreateAccountResponse actualReceiverAccount = AccountSteps.findAccountById(accounts, receiverAccount.getId());

        // проверяем неуспешное списание суммы с счёта, с которого переводили и её неуспешное зачисление на счёт,
        // на который переводили
        softly.assertThat(actualSenderAccount.getBalance()).isEqualTo(initialBalance);
        softly.assertThat(actualReceiverAccount.getBalance()).isEqualTo(0.0);
    }
}
