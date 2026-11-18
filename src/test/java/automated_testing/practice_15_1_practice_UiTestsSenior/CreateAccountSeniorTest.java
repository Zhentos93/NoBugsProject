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
        new UserDashboard()
                .open()
                .createNewAccount();

        List<CreateAccountResponse> createdAccounts = SessionStorage.getSteps().getAllAccounts();

        assertThat(createdAccounts).hasSize(1);

        new UserDashboard().checkAlertMessageAndAccept
                (BankAlert.NEW_ACCOUNT_CREATED.getMessage() + createdAccounts.getFirst().getAccountNumber());

        assertThat(createdAccounts.getFirst().getBalance()).isZero();
    }
}
