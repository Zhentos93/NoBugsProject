package automated_testing.practice_15_1_practice_UiTestsSenior;

import automated_testing.practice_15_2_homework_UiTestsSenior.BaseUiSeniorTest;
import automated_testing_api_senior.generators.RandomModelGenerator;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.CreateUserResponse;
import automated_testing_api_senior.models.comparison.ModelAssertions;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_ui_senior.elements.UserBage;
import automated_testing_ui_senior.pages.AdminPanel;
import automated_testing_ui_senior.pages.BankAlert;
import common.annotations.AdminSession;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserSeniorTest extends BaseUiSeniorTest {

    @Test
    @AdminSession
    public void adminCanCreateUserTest() {
        CreateUserRequest newUser = RandomModelGenerator.generate(CreateUserRequest.class);

        UserBage createdUserBage = new AdminPanel()
                .open()
                .createUser(newUser.getUsername(), newUser.getPassword())
                .checkAlertMessageAndAccept(BankAlert.USER_CREATED_SUCCESSFULLY.getMessage())
                .getUser(newUser.getUsername(), newUser.getRole());

        assertThat(createdUserBage.getUsername()).isEqualTo(newUser.getUsername());
        assertThat(createdUserBage.getRole()).isEqualTo(newUser.getRole());

        CreateUserResponse createdUser = AdminSteps.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(newUser.getUsername()))
                .findFirst().get();

        ModelAssertions.assertThatModels(newUser, createdUser).match();
    }

    @Test
    @AdminSession
    public void adminCannotCreateUserWithInvalidDataTest() {
        CreateUserRequest newUser = RandomModelGenerator.generate(CreateUserRequest.class);
        newUser.setUsername("a");

        new AdminPanel()
                .open()
                .createUser(newUser.getUsername(), newUser.getPassword())
                .checkAlertMessageAndAccept(BankAlert.USERNAME_MUST_BE_BETWEEN_3_AND_15_CHARACTERS.getMessage())
                .checkUserIsAbsent(newUser.getUsername(), newUser.getRole());

        long usersWithSameUsernameAsNewUser = AdminSteps.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(newUser.getUsername()))
                .count();

        assertThat(usersWithSameUsernameAsNewUser).isZero();
    }
}
