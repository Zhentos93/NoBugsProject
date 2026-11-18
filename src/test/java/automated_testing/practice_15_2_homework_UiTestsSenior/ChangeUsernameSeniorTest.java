package automated_testing.practice_15_2_homework_UiTestsSenior;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.generators.RandomModelGenerator;
import automated_testing_api_senior.models.ChangeUsernameRequest;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.UserProfileResponse;
import automated_testing_api_senior.models.comparison.ModelAssertions;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_ui_senior.pages.BankAlert;
import automated_testing_ui_senior.pages.EditProfilePage;
import automated_testing_ui_senior.pages.UserDashboard;
import common.annotations.UserSession;
import common.storage.SessionStorage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class ChangeUsernameSeniorTest extends BaseUiSeniorTest {

    @Test
    @UserSession
    public void userCanChangeNameToValidNameTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем получение созданного админом пользователя
        CreateUserRequest user = SessionStorage.getUser();

        // проверяем авторизацию пользователя и сохраняем спецификацию с Authorization header
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение исходного профиля для итогового сравнения name
        UserProfileResponse profileBeforeChange = AccountSteps.getProfile(userSpec);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        ChangeUsernameRequest changeUsernameRequest = RandomModelGenerator.generate(ChangeUsernameRequest.class);
        String newName = changeUsernameRequest.getName();

        // проверяем открытие Dashboard с исходными name и username в шапке профиля
        UserDashboard userDashboard = new UserDashboard()
                .open()
                .checkProfile(profileBeforeChange.getName(), user.getUsername());

        // проверяем переход на страницу редактирования профиля и ожидание загрузки исходного профиля
        EditProfilePage editProfilePage = userDashboard
                .openEditProfilePage()
                .checkPageIsOpened()
                .waitUntilProfileIsLoaded(profileBeforeChange.getName());

        // проверяем ввод допустимого name из двух слов, состоящих только из букв и сохранение значения в поле
        editProfilePage.enterName(newName).checkEnteredName(newName);

        // проверяем отправку нового name через UI и успешный alert изменения name
        editProfilePage.saveChanges().checkAlertMessageAndAccept(BankAlert.NAME_UPDATED_SUCCESSFULLY.getMessage());

        // проверяем через API профиль сразу после успешного изменения name
        UserProfileResponse profileAfterChange = AccountSteps.getProfile(userSpec);

        // проверяем соответствие отправленной модели фактическому профилю
        ModelAssertions.assertThatModels(changeUsernameRequest, profileAfterChange).match();

        // проверяем через API, что username пользователя не изменился
        softly.assertThat(profileAfterChange.getUsername()).isEqualTo(profileBeforeChange.getUsername());

        // проверяем обновление страницы для повторного получения актуального профиля, т.к. без рефреша name не меняется
        // и проверяем новое name и неизменённый username в шапке
        editProfilePage.refresh().checkProfileHeader(newName, user.getUsername());
    }

    @Test
    @UserSession
    public void userCannotChangeNameToInvalidNameTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем получение созданного админом пользователя
        CreateUserRequest user = SessionStorage.getUser();

        // проверяем авторизацию пользователя и сохраняем спецификацию с Authorization header
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение исходного профиля для итогового сравнения name
        UserProfileResponse profileBeforeChange = AccountSteps.getProfile(userSpec);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие Dashboard с исходными name и username в шапке профиля
        UserDashboard userDashboard = new UserDashboard()
                .open()
                .checkProfile(profileBeforeChange.getName(), user.getUsername());

        // проверяем переход на страницу редактирования профиля и ожидание загрузки исходного профиля
        EditProfilePage editProfilePage = userDashboard
                .openEditProfilePage()
                .checkPageIsOpened()
                .waitUntilProfileIsLoaded(profileBeforeChange.getName());

        String invalidName = RandomData.getNameWithDigit();

        // проверяем ввод недопустимого name, содержащего цифру и сохранение значения в поле
        editProfilePage.enterName(invalidName).checkEnteredName(invalidName);

        // проверяем отправку недопустимого name и alert с сообщением о правиле валидации name
        editProfilePage.saveChanges().checkAlertMessageAndAccept(
                BankAlert.NAME_MUST_CONTAIN_TWO_WORDS_WITH_LETTERS_ONLY.getMessage()
        );

        // проверяем через API профиль сразу после отклонённого изменения name
        UserProfileResponse profileAfterChange = AccountSteps.getProfile(userSpec);

        // проверяем через API, что name и username остались без изменений
        softly.assertThat(profileAfterChange.getName()).isEqualTo(profileBeforeChange.getName());
        softly.assertThat(profileAfterChange.getUsername()).isEqualTo(profileBeforeChange.getUsername());

        // проверяем, что в шапке осталось исходное name и тот же username после рефреша
        editProfilePage.refresh().checkProfileHeader(profileBeforeChange.getName(), user.getUsername());
    }
}
