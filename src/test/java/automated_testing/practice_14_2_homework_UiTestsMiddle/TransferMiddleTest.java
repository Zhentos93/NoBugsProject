package automated_testing.practice_14_2_homework_UiTestsMiddle;

import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_ui_middle.BankAlert;
import automated_testing_ui_middle.TransferPage;
import automated_testing_ui_middle.UserDashboard;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferMiddleTest extends BaseUiMiddleTest {
    private static final double SENDER_FIRST_ACCOUNT_INITIAL_BALANCE = 1000.0;
    private static final double SENDER_SECOND_ACCOUNT_INITIAL_BALANCE = 50.0;
    private static final double RECEIVER_ACCOUNT_INITIAL_BALANCE = 200.0;
    private static final String EXPECTED_WELCOME_TEXT = "Welcome, noname!";
    private static final int TRANSFER_AMOUNT = 100;
    private static final double SENDER_ACCOUNT_INITIAL_BALANCE = 50.0;


    @Test
    public void userCanTransferMoneyToAnotherUserTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя-отправителя
        CreateUserRequest senderUser = AdminSteps.createUser();

        // проверяем авторизацию отправителя и сохраняем спецификацию с Authorization header
        RequestSpecification senderSpec = RequestSpecs.authAsUser(senderUser.getUsername(), senderUser.getPassword());

        // проверяем создание админом пользователя-получателя
        CreateUserRequest receiverUser = AdminSteps.createUser();

        // проверяем авторизацию получателя и сохраняем спецификацию с Authorization header
        RequestSpecification receiverSpec = RequestSpecs.authAsUser(
                receiverUser.getUsername(),
                receiverUser.getPassword()
        );

        // проверяем создание первого аккаунта отправителя, с которого выполним перевод
        CreateAccountResponse senderFirstAccount = AccountSteps.createAccount(senderSpec);

        // проверяем создание второго аккаунта отправителя, баланс которого не должен измениться
        CreateAccountResponse senderSecondAccount = AccountSteps.createAccount(senderSpec);

        // проверяем создание аккаунта получателя, на который выполним перевод
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(receiverSpec);

        // проверяем начальный баланс 1000.0 для первого аккаунта отправителя
        AccountSteps.deposit(senderSpec, senderFirstAccount.getId(), SENDER_FIRST_ACCOUNT_INITIAL_BALANCE);

        // проверяем начальный баланс 50.0 для второго аккаунта отправителя
        AccountSteps.deposit(senderSpec, senderSecondAccount.getId(), SENDER_SECOND_ACCOUNT_INITIAL_BALANCE);

        // проверяем начальный баланс 200.0 для первого аккаунта получателя
        AccountSteps.deposit(receiverSpec, receiverAccount.getId(), RECEIVER_ACCOUNT_INITIAL_BALANCE);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем авторизацию отправителя через localStorage
        authAsUser(senderUser);

        // проверяем открытие Dashboard и переход на страницу перевода
        TransferPage transferPage = new UserDashboard()
                .open()
                .checkWelcomeText(EXPECTED_WELCOME_TEXT)
                .openTransferPage()
                .checkPageIsOpened()
                .startNewTransfer();

        // проверяем выбор первого аккаунта отправителя
        transferPage.selectSenderAccount(senderFirstAccount.getAccountNumber())
                .checkSelectedSenderAccount(senderFirstAccount.getAccountNumber());

        // проверяем ввод номера аккаунта получателя при пустом Recipient Name
        transferPage.enterRecipientAccountNumber(receiverAccount.getAccountNumber())
                .checkRecipientAccountNumber(receiverAccount.getAccountNumber());

        String transferAmountText = String.valueOf(TRANSFER_AMOUNT);

        // проверяем ввод суммы перевода 100
        transferPage.enterAmount(transferAmountText);

        // проверяем установку checkbox подтверждения правильности данных
        transferPage.confirmDetails();

        // проверяем отправку перевода и успешный alert с номером аккаунта получателя
        transferPage.submitTransfer().checkAlertMessageAndAccept(BankAlert.TRANSFER_SUCCESSFULLY.getMessage()
                .formatted(transferAmountText, receiverAccount.getAccountNumber()));

        // проверяем через API итоговые балансы отправителя и получателя сразу после успешного перевода
        CreateAccountResponse[] senderAccountsAfterTransfer = AccountSteps.getAccounts(senderSpec);
        CreateAccountResponse[] receiverAccountsAfterTransfer = AccountSteps.getAccounts(receiverSpec);

        double expectedSenderFirstAccountBalance = SENDER_FIRST_ACCOUNT_INITIAL_BALANCE - TRANSFER_AMOUNT;
        double expectedReceiverAccountBalance = RECEIVER_ACCOUNT_INITIAL_BALANCE + TRANSFER_AMOUNT;

        assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderFirstAccount.getId())
                .getBalance()).isEqualTo(expectedSenderFirstAccountBalance);

        assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderSecondAccount.getId())
                .getBalance()).isEqualTo(SENDER_SECOND_ACCOUNT_INITIAL_BALANCE);

        assertThat(AccountSteps.findAccountById(receiverAccountsAfterTransfer, receiverAccount.getId())
                .getBalance()).isEqualTo(expectedReceiverAccountBalance);
    }

    @Test
    public void userCannotTransferMoreThanAvailableBalanceTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя-отправителя
        CreateUserRequest senderUser = AdminSteps.createUser();

        // проверяем авторизацию отправителя и сохраняем спецификацию с Authorization header
        RequestSpecification senderSpec = RequestSpecs.authAsUser(senderUser.getUsername(), senderUser.getPassword());

        // проверяем создание админом пользователя-получателя
        CreateUserRequest receiverUser = AdminSteps.createUser();

        // проверяем авторизацию получателя и сохраняем спецификацию с Authorization header
        RequestSpecification receiverSpec = RequestSpecs.authAsUser(
                receiverUser.getUsername(),
                receiverUser.getPassword()
        );

        // проверяем создание аккаунта отправителя с недостаточным балансом
        CreateAccountResponse senderAccount = AccountSteps.createAccount(senderSpec);

        // проверяем создание аккаунта получателя
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(receiverSpec);

        // проверяем начальный баланс 50.0 для аккаунта отправителя
        AccountSteps.deposit(senderSpec, senderAccount.getId(), SENDER_ACCOUNT_INITIAL_BALANCE);

        // проверяем начальный баланс 200.0 для аккаунта получателя
        AccountSteps.deposit(receiverSpec, receiverAccount.getId(), RECEIVER_ACCOUNT_INITIAL_BALANCE);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем авторизацию отправителя через localStorage
        authAsUser(senderUser);

        // проверяем открытие Dashboard и переход на страницу перевода
        TransferPage transferPage = new UserDashboard()
                .open()
                .checkWelcomeText(EXPECTED_WELCOME_TEXT)
                .openTransferPage()
                .checkPageIsOpened()
                .startNewTransfer();

        // проверяем выбор аккаунта отправителя
        transferPage.selectSenderAccount(senderAccount.getAccountNumber())
                .checkSelectedSenderAccount(senderAccount.getAccountNumber());

        // проверяем ввод номера аккаунта получателя
        transferPage.enterRecipientAccountNumber(receiverAccount.getAccountNumber())
                .checkRecipientAccountNumber(receiverAccount.getAccountNumber());

        String transferAmountText = String.valueOf(TRANSFER_AMOUNT);

        // проверяем ввод суммы 100
        transferPage.enterAmount(transferAmountText);

        // проверяем установку checkbox подтверждения правильности данных
        transferPage.confirmDetails();

        // проверяем отправку перевода через UI и alert с сообщением об отказе из-за недостаточного баланса
        transferPage.submitTransfer().checkAlertMessageAndAccept(BankAlert.TRANSFER_REJECTED.getMessage());

        // проверяем через API итоговые балансы отправителя и получателя сразу после неуспешного перевода
        CreateAccountResponse[] senderAccountsAfterTransfer = AccountSteps.getAccounts(senderSpec);
        CreateAccountResponse[] receiverAccountsAfterTransfer = AccountSteps.getAccounts(receiverSpec);

        // проверяем, что после отказа баланс отправителя остался равен 50.0
        assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderAccount.getId())
                .getBalance()).isEqualTo(SENDER_ACCOUNT_INITIAL_BALANCE);

        // проверяем, что после отказа баланс получателя остался равен 200.0
        assertThat(AccountSteps.findAccountById(receiverAccountsAfterTransfer, receiverAccount.getId())
                .getBalance()).isEqualTo(RECEIVER_ACCOUNT_INITIAL_BALANCE);
    }
}