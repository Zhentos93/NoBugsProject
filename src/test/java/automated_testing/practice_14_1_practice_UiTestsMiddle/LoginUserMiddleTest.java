package automated_testing.practice_14_1_practice_UiTestsMiddle;

import automated_testing.practice_14_2_homework_UiTestsMiddle.BaseUiMiddleTest;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_ui_middle.AdminPanel;
import automated_testing_ui_middle.LoginPage;
import automated_testing_ui_middle.UserDashboard;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

public class LoginUserMiddleTest extends BaseUiMiddleTest {
    @Test
    public void adminCanLoginWithCorrectDataTest() {
        CreateUserRequest admin = CreateUserRequest.getAdmin();

        new LoginPage().open().login(admin.getUsername(), admin.getPassword())
                .getPage(AdminPanel.class).getAdminPanelText().shouldBe(Condition.visible);
    }

    @Test
    public void userCanLoginWithCorrectDataTest() {
        CreateUserRequest user = AdminSteps.createUser();

        new LoginPage().open().login(user.getUsername(), user.getPassword())
                .getPage(UserDashboard.class).getWelcomeText()
                .shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));
    }
}
