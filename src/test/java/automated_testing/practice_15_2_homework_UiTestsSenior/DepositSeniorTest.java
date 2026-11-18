package automated_testing.practice_15_2_homework_UiTestsSenior;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_ui_senior.pages.BankAlert;
import automated_testing_ui_senior.pages.DepositPage;
import automated_testing_ui_senior.pages.UserDashboard;
import common.annotations.AccountSession;
import common.annotations.BalanceSession;
import common.annotations.UserSession;
import common.storage.AccountSessionStorage;
import common.storage.SessionStorage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class DepositSeniorTest extends BaseUiSeniorTest {

    @Test
    @UserSession
    @AccountSession({2})
    @BalanceSession
    public void userCanDepositSelectedAccountTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем получение созданного админом пользователя
        CreateUserRequest user = SessionStorage.getUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение первого аккаунта, который будем пополнять через UI
        CreateAccountResponse firstAccount = AccountSessionStorage.getAccount(1);

        // проверяем получение второго аккаунта, баланс которого не должен измениться
        CreateAccountResponse secondAccount = AccountSessionStorage.getAccount(2);

        // проверяем получение исходных балансов
        double firstAccountInitialBalance = firstAccount.getBalance();
        double secondAccountInitialBalance = secondAccount.getBalance();

        double depositAmount = RandomData.getValidDepositAmount();
        String depositAmountText = String.valueOf(depositAmount);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие Dashboard и переход на страницу пополнения счёта
        DepositPage depositPage = new UserDashboard()
                .open()
                .openDepositPage()
                .checkPageIsOpened();

        // проверяем выбор первого аккаунта
        depositPage.selectAccount(firstAccount.getAccountNumber()).checkSelectedAccount(firstAccount.getAccountNumber());

        // проверяем ввод сгенерированной допустимой суммы и сохранение этого значения в поле
        depositPage.enterAmount(depositAmountText).checkEnteredAmount(depositAmountText);

        // проверяем отправку формы и успешный alert с номером выбранного аккаунта
        depositPage.submitDeposit().checkAlertMessageAndAccept(BankAlert.DEPOSIT_SUCCESSFULLY.getMessage()
                .formatted(depositAmountText, firstAccount.getAccountNumber()));

        // проверяем через API актуальные аккаунты сразу после пополнения
        CreateAccountResponse[] accountsAfterDeposit = AccountSteps.getAccounts(userSpec);

        // проверяем поиск пополненного первого аккаунта по его ID
        CreateAccountResponse firstAccountAfterDeposit =
                AccountSteps.findAccountById(accountsAfterDeposit, firstAccount.getId());

        // проверяем поиск второго аккаунта, баланс которого не изменился
        CreateAccountResponse secondAccountAfterDeposit =
                AccountSteps.findAccountById(accountsAfterDeposit, secondAccount.getId());

        double expectedFirstAccountBalance = firstAccountInitialBalance + depositAmount;

        // проверяем увеличение баланса первого аккаунта
        softly.assertThat(firstAccountAfterDeposit.getBalance()).isEqualTo(expectedFirstAccountBalance);

        // проверяем, что баланс второго аккаунта не изменился
        softly.assertThat(secondAccountAfterDeposit.getBalance()).isEqualTo(secondAccountInitialBalance);
    }

    @Test
    @UserSession
    @AccountSession({1})
    @BalanceSession
    public void userCannotDepositMoreThanMaximumTest() {
        // ПРОВЕРКА ПОДГОТОВЛЕННЫХ API-ПРЕДУСЛОВИЙ \\

        // проверяем получение созданного админом пользователя
        CreateUserRequest user = SessionStorage.getUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение аккаунта, который попробуем пополнить на недопустимую сумму
        CreateAccountResponse account = AccountSessionStorage.getAccount(1);

        // проверяем получение исходного баланса
        double initialBalance = account.getBalance();

        double depositAmount = RandomData.getDepositAmountAboveMaximum();
        String depositAmountText = String.valueOf(depositAmount);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие Dashboard и переход на страницу пополнения счёта
        DepositPage depositPage = new UserDashboard()
                .open()
                .openDepositPage()
                .checkPageIsOpened();

        // проверяем выбор аккаунта, баланс которого должен остаться неизменным
        depositPage.selectAccount(account.getAccountNumber()).checkSelectedAccount(account.getAccountNumber());

        // проверяем ввод недопустимой суммы для пополнения
        depositPage.enterAmount(depositAmountText).checkEnteredAmount(depositAmountText);

        // проверяем отправку формы и alert с сообщением об отказе в пополнении
        depositPage.submitDeposit().checkAlertMessageAndAccept(BankAlert.DEPOSIT_AMOUNT_EXCEEDS_MAXIMUM.getMessage());

        // проверяем через API поиск аккаунта по его ID
        CreateAccountResponse accountAfterDeposit = AccountSteps.findAccountById(
                AccountSteps.getAccounts(userSpec), account.getId()
        );

        softly.assertThat(accountAfterDeposit.getBalance()).isEqualTo(initialBalance);
    }
}
