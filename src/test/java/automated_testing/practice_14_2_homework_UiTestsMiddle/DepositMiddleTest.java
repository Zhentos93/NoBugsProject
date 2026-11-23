package automated_testing.practice_14_2_homework_UiTestsMiddle;

import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_ui_middle.BankAlert;
import automated_testing_ui_middle.DepositPage;
import automated_testing_ui_middle.UserDashboard;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositMiddleTest extends BaseUiMiddleTest {
    private static final double FIRST_ACCOUNT_INITIAL_BALANCE = 100.0;
    private static final double SECOND_ACCOUNT_INITIAL_BALANCE = 250.0;
    private static final int DEPOSIT_AMOUNT = 100;
    private static final String ABOVE_MAXIMUM_DEPOSIT_AMOUNT = "5000.01";

    @Test
    public void userCanDepositSelectedAccountTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание первого аккаунта, который будем пополнять через UI
        CreateAccountResponse firstAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание второго аккаунта, баланс которого не должен измениться
        CreateAccountResponse secondAccount = AccountSteps.createAccount(userSpec);

        // проверяем начальный баланс 100.0 для первого аккаунта
        AccountSteps.deposit(userSpec, firstAccount.getId(), FIRST_ACCOUNT_INITIAL_BALANCE);

        // проверяем начальный баланс 250.0 для второго аккаунта
        AccountSteps.deposit(userSpec, secondAccount.getId(), SECOND_ACCOUNT_INITIAL_BALANCE);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем авторизацию пользователя через localStorage
        authAsUser(user);

        // проверяем открытие Dashboard и переход на страницу пополнения счёта
        DepositPage depositPage = new UserDashboard()
                .open()
                .openDepositPage()
                .checkPageIsOpened();

        // проверяем выбор первого аккаунта
        depositPage.selectAccount(firstAccount.getAccountNumber()).checkSelectedAccount(firstAccount.getAccountNumber());

        String depositAmountText = String.valueOf(DEPOSIT_AMOUNT);

        // проверяем ввод суммы 100
        depositPage.enterAmount(depositAmountText);

        // проверяем отправку формы и успешный alert с номером выбранного аккаунта
        depositPage.submitDeposit().checkAlertMessageAndAccept(BankAlert.DEPOSIT_SUCCESSFULLY.getMessage()
                .formatted(depositAmountText, firstAccount.getAccountNumber()));

        // проверяем через API актуальные аккаунты сразу после пополнения
        CreateAccountResponse[] accountsAfterDeposit = AccountSteps.getAccounts(userSpec);

        // проверяем поиск пополненного первого аккаунта по его ID
        CreateAccountResponse firstAccountAfterDeposit = AccountSteps.findAccountById(
                accountsAfterDeposit,
                firstAccount.getId()
        );

        // проверяем поиск второго аккаунта, баланс которого не изменился
        CreateAccountResponse secondAccountAfterDeposit = AccountSteps.findAccountById(
                accountsAfterDeposit,
                secondAccount.getId()
        );

        double expectedFirstAccountBalance = FIRST_ACCOUNT_INITIAL_BALANCE + DEPOSIT_AMOUNT;

        // проверяем увеличение баланса первого аккаунта со 100.0 до 200.0
        assertThat(firstAccountAfterDeposit.getBalance()).isEqualTo(expectedFirstAccountBalance);

        // проверяем сохранение баланса второго аккаунта на уровне 250.0
        assertThat(secondAccountAfterDeposit.getBalance()).isEqualTo(SECOND_ACCOUNT_INITIAL_BALANCE);
    }

    @Test
    public void userCannotDepositMoreThanMaximumTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем создание аккаунта, который попробуем пополнить на недопустимую сумму
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);

        // проверяем начальный баланс 100.0
        AccountSteps.deposit(userSpec, account.getId(), FIRST_ACCOUNT_INITIAL_BALANCE);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем авторизацию пользователя через localStorage
        authAsUser(user);

        // проверяем открытие Dashboard и переход на страницу пополнения счёта
        DepositPage depositPage = new UserDashboard()
                .open()
                .openDepositPage()
                .checkPageIsOpened();

        // проверяем выбор аккаунта, баланс которого должен остаться неизменным
        depositPage.selectAccount(account.getAccountNumber()).checkSelectedAccount(account.getAccountNumber());

        // проверяем ввод недопустимой суммы для пополнения
        depositPage.enterAmount(ABOVE_MAXIMUM_DEPOSIT_AMOUNT);

        // проверяем отправку формы и alert с сообщением об отказе в пополнении
        depositPage.submitDeposit().checkAlertMessageAndAccept(BankAlert.DEPOSIT_AMOUNT_EXCEEDS_MAXIMUM.getMessage());

        // проверяем через API поиск аккаунта по его ID
        CreateAccountResponse accountAfterDeposit = AccountSteps.findAccountById(
                AccountSteps.getAccounts(userSpec),
                account.getId()
        );

        // проверяем сохранение исходного баланса аккаунта 100.0
        assertThat(accountAfterDeposit.getBalance()).isEqualTo(FIRST_ACCOUNT_INITIAL_BALANCE);
    }
}