package automated_testing.practice_15_1_practice_UiTestsSenior;

import automated_testing.practice_15_2_homework_UiTestsSenior.BaseUiSeniorTest;
import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_ui_senior.pages.BankAlert;
import automated_testing_ui_senior.pages.UserDashboard;
import common.annotations.UserSession;
import common.storage.SessionStorage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountSeniorTest extends BaseUiSeniorTest {
    @Test
    @UserSession
    public void userCanCreateAccountTest() {
        String alertText = new UserDashboard()
                .open()
                .createNewAccount()
                .readAlertTextAndAccept();

        List<CreateAccountResponse> createdAccounts = SessionStorage.getSteps().getAllAccounts();
        assertThat(createdAccounts).hasSize(1);
        CreateAccountResponse createdAccount = createdAccounts.getFirst();

        String expectedAlert = BankAlert.NEW_ACCOUNT_CREATED.getMessage() + createdAccount.getAccountNumber();
        assertThat(alertText).isEqualTo(expectedAlert);
        assertThat(createdAccount.getBalance()).isZero();
    }
}