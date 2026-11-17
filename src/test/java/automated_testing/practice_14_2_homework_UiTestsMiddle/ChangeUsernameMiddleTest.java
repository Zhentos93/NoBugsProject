package automated_testing.practice_14_2_homework_UiTestsMiddle;

import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.UserProfileResponse;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_ui_middle.BankAlert;
import automated_testing_ui_middle.EditProfilePage;
import automated_testing_ui_middle.UserDashboard;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeUsernameMiddleTest extends BaseUiMiddleTest {

    @Test
    public void userCanChangeNameToValidNameTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом нового пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию пользователя и сохраняем спецификацию с Authorization header
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение исходного профиля для итогового сравнения name
        UserProfileResponse profileBeforeChange = AccountSteps.getProfile(userSpec);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        final String initialName = "Noname";
        final String newName = "Ivan Ivanov";
        final String expectedUsername = profileBeforeChange.getUsername();

        // проверяем авторизацию пользователя через localStorage
        authAsUser(user);

        // проверяем открытие Dashboard и неизменённый username в шапке профиля
        UserDashboard userDashboard = new UserDashboard()
                .open()
                .checkProfile(initialName, expectedUsername);

        // проверяем переход на страницу редактирования профиля и ожидание загрузки исходного профиля
        EditProfilePage editProfilePage = userDashboard
                .openEditProfilePage()
                .checkPageIsOpened()
                .waitUntilProfileIsLoaded();

        // проверяем ввод допустимого name из двух слов, состоящих только из букв и сохранение значения в поле
        editProfilePage.enterName(newName).checkEnteredName(newName);

        // проверяем отправку нового name через UI и успешный alert изменения name
        editProfilePage.saveChanges().checkAlertMessageAndAccept(BankAlert.NAME_UPDATED_SUCCESSFULLY.getMessage());

        // проверяем через API профиль сразу после успешного изменения name
        UserProfileResponse profileAfterChange = AccountSteps.getProfile(userSpec);

        // проверяем через API новое name и неизменённый username
        assertThat(profileAfterChange.getName()).isEqualTo(newName);
        assertThat(profileAfterChange.getUsername()).isEqualTo(expectedUsername);

        // проверяем обновление страницы для повторного получения актуального профиля, т.к. без рефреша name не меняется
        // и проверяем новое name и неизменённый username в шапке
        editProfilePage.refresh().checkProfileHeader(newName, expectedUsername);
    }

    @Test
    public void userCannotChangeNameToNameWithDigitTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом нового пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию пользователя и сохраняем спецификацию с Authorization header
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение исходного профиля для итогового сравнения name
        UserProfileResponse profileBeforeChange = AccountSteps.getProfile(userSpec);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        final String initialName = "Noname";
        final String invalidName = "Ivan1 Ivanov";
        final String expectedUsername = profileBeforeChange.getUsername();

        // проверяем авторизацию пользователя через localStorage
        authAsUser(user);

        // проверяем открытие Dashboard и неизменённый username в шапке профиля
        UserDashboard userDashboard = new UserDashboard()
                .open()
                .checkProfile(initialName, expectedUsername);

        // проверяем переход на страницу редактирования профиля и ожидание загрузки исходного профиля
        EditProfilePage editProfilePage = userDashboard
                .openEditProfilePage()
                .checkPageIsOpened()
                .waitUntilProfileIsLoaded();

        // проверяем ввод недопустимого name, содержащего цифру и сохранение значения в поле
        editProfilePage.enterName(invalidName).checkEnteredName(invalidName);

        // проверяем отправку недопустимого name через UI и alert с сообщением о правиле валидации name
        editProfilePage.saveChanges().checkAlertMessageAndAccept(
                BankAlert.NAME_MUST_CONTAIN_TWO_WORDS_WITH_LETTERS_ONLY.getMessage());

        // проверяем через API профиль сразу после отклонённого изменения name
        UserProfileResponse profileAfterChange = AccountSteps.getProfile(userSpec);

        // проверяем через API, что name и username остались без изменений
        assertThat(profileAfterChange.getName()).isEqualTo(profileBeforeChange.getName());
        assertThat(profileAfterChange.getUsername()).isEqualTo(expectedUsername);

        // проверяем, что в шапке осталось исходное name Noname и тот же username после рефреша
        editProfilePage.refresh().checkProfileHeader(initialName, expectedUsername);
    }
}
