package automated_testing.practice_15_1_practice_UiTestsSenior;

import automated_testing.practice_15_2_homework_UiTestsSenior.BaseUiSeniorTest;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_ui_senior.pages.AdminPanel;
import automated_testing_ui_senior.pages.LoginPage;
import automated_testing_ui_senior.pages.UserDashboard;
import com.codeborne.selenide.Condition;
import common.annotations.Browsers;
import org.junit.jupiter.api.Test;

public class LoginUserSeniorTest extends BaseUiSeniorTest {
    @Test
    @Browsers({"chrome"})
    public void adminCanLoginWithCorrectDataTest() {
        CreateUserRequest admin = CreateUserRequest.getAdmin();

        new LoginPage()
                .open()
                .login(admin.getUsername(), admin.getPassword())
                .getPage(AdminPanel.class)
                .getAdminPanelText()
                .shouldBe(Condition.visible);
    }

    @Test
    public void userCanLoginWithCorrectDataTest() {
        CreateUserRequest user = AdminSteps.createUser();

        new LoginPage().open().login(user.getUsername(), user.getPassword())
                .getPage(UserDashboard.class).getWelcomeText()
                .shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));
    }
}
