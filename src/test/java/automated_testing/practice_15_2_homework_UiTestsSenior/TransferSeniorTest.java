package automated_testing.practice_15_2_homework_UiTestsSenior;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_ui_senior.pages.BankAlert;
import automated_testing_ui_senior.pages.TransferPage;
import automated_testing_ui_senior.pages.UserDashboard;
import common.annotations.AccountSession;
import common.annotations.BalanceSession;
import common.annotations.UserSession;
import common.storage.AccountSessionStorage;
import common.storage.SessionStorage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class TransferSeniorTest extends BaseUiSeniorTest {

    @Test
    @UserSession(value = 2, auth = 1)
    @AccountSession({2, 1})
    @BalanceSession
    public void userCanTransferMoneyToAnotherUserTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем получение созданного админом пользователя-отправителя
        CreateUserRequest senderUser = SessionStorage.getUser(1);

        // проверяем получение созданного админом пользователя-получателя
        CreateUserRequest receiverUser = SessionStorage.getUser(2);

        // проверяем авторизацию отправителя и сохраняем спецификацию с Authorization header
        RequestSpecification senderSpec = RequestSpecs.authAsUser(senderUser.getUsername(), senderUser.getPassword());

        // проверяем авторизацию получателя и сохраняем спецификацию с Authorization header
        RequestSpecification receiverSpec = RequestSpecs.authAsUser(
                receiverUser.getUsername(),
                receiverUser.getPassword()
        );

        // проверяем получение первого аккаунта отправителя, с которого выполним перевод
        CreateAccountResponse senderFirstAccount = AccountSessionStorage.getAccount(1, 1);

        // проверяем получение второго аккаунта отправителя, баланс которого не должен измениться
        CreateAccountResponse senderSecondAccount = AccountSessionStorage.getAccount(1, 2);

        // проверяем получение аккаунта получателя, на который выполним перевод
        CreateAccountResponse receiverAccount = AccountSessionStorage.getAccount(2, 1);

        // проверяем получение исходных балансов
        double senderFirstAccountInitialBalance = senderFirstAccount.getBalance();
        double senderSecondAccountInitialBalance = senderSecondAccount.getBalance();
        double receiverAccountInitialBalance = receiverAccount.getBalance();

        double transferAmount = RandomData.getValidAmountNotGreaterThan(senderFirstAccountInitialBalance);
        String transferAmountText = String.valueOf(transferAmount);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие Dashboard и переход на страницу перевода
        TransferPage transferPage = new UserDashboard()
                .open()
                .openTransferPage()
                .checkPageIsOpened()
                .startNewTransfer();

        // проверяем выбор первого аккаунта отправителя
        transferPage.selectSenderAccount(senderFirstAccount.getAccountNumber())
                .checkSelectedSenderAccount(senderFirstAccount.getAccountNumber());

        // проверяем, что Recipient Name осталось пустым, т.к. поле не входит в API-контракт перевода
        transferPage.checkRecipientNameIsEmpty();

        // проверяем ввод номера аккаунта получателя при пустом Recipient Name
        transferPage.enterRecipientAccountNumber(receiverAccount.getAccountNumber())
                .checkRecipientAccountNumber(receiverAccount.getAccountNumber());

        // проверяем ввод сгенерированной суммы перевода и сохранение значения в поле
        transferPage.enterAmount(transferAmountText).checkEnteredAmount(transferAmountText);

        // проверяем установку checkbox подтверждения правильности данных
        transferPage.confirmDetails();

        // проверяем отправку перевода и успешный alert с номером аккаунта получателя
        transferPage.submitTransfer().checkAlertMessageAndAccept(BankAlert.TRANSFER_SUCCESSFULLY.getMessage()
                .formatted(transferAmountText, receiverAccount.getAccountNumber()));

        // проверяем через API итоговые балансы отправителя и получателя сразу после успешного перевода
        CreateAccountResponse[] senderAccountsAfterTransfer = AccountSteps.getAccounts(senderSpec);
        CreateAccountResponse[] receiverAccountsAfterTransfer = AccountSteps.getAccounts(receiverSpec);

        double expectedSenderFirstAccountBalance = senderFirstAccountInitialBalance - transferAmount;
        double expectedReceiverAccountBalance = receiverAccountInitialBalance + transferAmount;

        softly.assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderFirstAccount.getId())
                .getBalance()).isEqualTo(expectedSenderFirstAccountBalance);

        softly.assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderSecondAccount.getId())
                .getBalance()).isEqualTo(senderSecondAccountInitialBalance);

        softly.assertThat(AccountSteps.findAccountById(receiverAccountsAfterTransfer, receiverAccount.getId())
                .getBalance()).isEqualTo(expectedReceiverAccountBalance);
    }

    @Test
    @UserSession(value = 2, auth = 1)
    @AccountSession({1, 1})
    @BalanceSession
    public void userCannotTransferMoreThanAvailableBalanceTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем получение созданного админом пользователя-отправителя
        CreateUserRequest senderUser = SessionStorage.getUser(1);

        // проверяем получение созданного админом пользователя-получателя
        CreateUserRequest receiverUser = SessionStorage.getUser(2);

        // проверяем авторизацию отправителя и сохраняем спецификацию с Authorization header
        RequestSpecification senderSpec = RequestSpecs.authAsUser(senderUser.getUsername(), senderUser.getPassword());

        // проверяем авторизацию получателя и сохраняем спецификацию с Authorization header
        RequestSpecification receiverSpec = RequestSpecs.authAsUser(
                receiverUser.getUsername(),
                receiverUser.getPassword()
        );

        // проверяем получение аккаунта отправителя с недостаточным балансом
        CreateAccountResponse senderAccount = AccountSessionStorage.getAccount(1, 1);

        // проверяем получение аккаунта получателя
        CreateAccountResponse receiverAccount = AccountSessionStorage.getAccount(2, 1);

        // проверяем получение исходных балансов
        double senderAccountInitialBalance = senderAccount.getBalance();
        double receiverAccountInitialBalance = receiverAccount.getBalance();

        double transferAmount = RandomData.getAmountGreaterThan(senderAccountInitialBalance);
        String transferAmountText = String.valueOf(transferAmount);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие Dashboard и переход на страницу перевода
        TransferPage transferPage = new UserDashboard()
                .open()
                .openTransferPage()
                .checkPageIsOpened()
                .startNewTransfer();

        // проверяем выбор аккаунта отправителя
        transferPage.selectSenderAccount(senderAccount.getAccountNumber())
                .checkSelectedSenderAccount(senderAccount.getAccountNumber());

        // проверяем, что Recipient Name осталось пустым, т.к. поле не входит в API-контракт перевода
        transferPage.checkRecipientNameIsEmpty();

        // проверяем ввод номера аккаунта получателя при пустом Recipient Name
        transferPage.enterRecipientAccountNumber(receiverAccount.getAccountNumber())
                .checkRecipientAccountNumber(receiverAccount.getAccountNumber());

        // проверяем ввод сгенерированной суммы перевода, превышающей доступный баланс отправителя
        // и сохранение значения в поле
        transferPage.enterAmount(transferAmountText).checkEnteredAmount(transferAmountText);

        // проверяем установку checkbox подтверждения правильности данных
        transferPage.confirmDetails();

        // проверяем отправку перевода и alert с сообщением об отказе из-за недостаточного баланса
        transferPage.submitTransfer().checkAlertMessageAndAccept(BankAlert.TRANSFER_REJECTED.getMessage());

        // проверяем через API итоговые балансы отправителя и получателя сразу после неуспешного перевода
        CreateAccountResponse[] senderAccountsAfterTransfer = AccountSteps.getAccounts(senderSpec);
        CreateAccountResponse[] receiverAccountsAfterTransfer = AccountSteps.getAccounts(receiverSpec);

        // проверяем, что после отказа баланс отправителя не изменился
        softly.assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderAccount.getId())
                .getBalance()).isEqualTo(senderAccountInitialBalance);

        // проверяем, что после отказа баланс получателя не изменился
        softly.assertThat(AccountSteps.findAccountById(receiverAccountsAfterTransfer, receiverAccount.getId())
                .getBalance()).isEqualTo(receiverAccountInitialBalance);
    }
}
