package automated_testing.practice_13_2_homework_UiTestsJunior;

import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.UserProfileResponse;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_api_senior.specs.ResponseSpecs;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeUsernameJuniorTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.baseUrl = "http://host.docker.internal:3000";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "128.0";
        Configuration.browserSize = "1920x1080";

        Configuration.browserCapabilities.setCapability(
                "selenoid:options",
                Map.of(
                        "enableVNC", true,
                        "enableLog", true
                )
        );
    }

    @Test
    public void userCanChangeNameToValidNameTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом нового пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию пользователя и сохраняем спецификацию с Authorization header
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение того же Authorization header для авторизации пользователя в браузере
        String userAuthHeader = SpecificationQuerier.query(userSpec).getHeaders().getValue("Authorization");

        // проверяем получение исходного профиля для итогового сравнения name
        UserProfileResponse profileBeforeChange = new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие приложения, сохранение токена и открытие Dashboard пользователя
        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        Selenide.open("/dashboard");

        // проверяем исходное name Noname и неизменённый username в шапке профиля
        $(Selectors.byClassName("user-name")).shouldHave(Condition.exactText("Noname"));
        $(Selectors.byClassName("user-username")).shouldHave(Condition.exactText("@" + user.getUsername()));

        // проверяем переход на страницу редактирования профиля через блок профиля в шапке
        $(Selectors.byClassName("profile-header")).click();

        // проверяем открытие страницы Edit Profile
        $(Selectors.byText("✏️ Edit Profile")).shouldBe(Condition.visible);

        Selenide.sleep(1000);

        // проверяем ввод допустимого name из двух слов, состоящих только из букв
        $(Selectors.byAttribute("placeholder", "Enter new name"))
                .sendKeys("Ivan Ivanov");

        // проверяем, что введённое name сохранилось в поле перед отправкой формы
        $(Selectors.byAttribute("placeholder", "Enter new name"))
                .shouldHave(Condition.value("Ivan Ivanov"));

        // проверяем отправку нового name через UI
        $(Selectors.byText("💾 Save Changes")).click();

        // проверяем успешный alert изменения name
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Name updated successfully!");

        // проверяем закрытие успешного alert
        alert.accept();

        // проверяем через API профиль сразу после успешного изменения name
        UserProfileResponse profileAfterChange = new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // проверяем через API новое name и неизменённый username
        assertThat(profileAfterChange.getName()).isEqualTo("Ivan Ivanov");
        assertThat(profileAfterChange.getUsername()).isEqualTo(profileBeforeChange.getUsername());

        // проверяем обновление страницы для повторного получения актуального профиля, т.к. без рефреша name не меняется
        Selenide.refresh();

        // проверяем новое name и неизменённый username в шапке
        $(Selectors.byClassName("user-name")).shouldBe(Condition.visible).shouldHave(Condition.exactText("Ivan Ivanov"));
        $(Selectors.byClassName("user-username")).shouldHave(Condition.exactText("@" + user.getUsername()));
    }

    @Test
    public void userCannotChangeNameToNameWithDigitTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом нового пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию пользователя и сохраняем спецификацию с Authorization header
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // проверяем получение того же Authorization header для авторизации пользователя в браузере
        String userAuthHeader = SpecificationQuerier.query(userSpec).getHeaders().getValue("Authorization");

        // проверяем получение исходного профиля для итогового сравнения name
        UserProfileResponse profileBeforeChange = new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие приложения, сохранение токена и открытие Dashboard пользователя
        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        Selenide.open("/dashboard");

        // проверяем исходное name Noname и username в шапке
        $(Selectors.byClassName("user-name")).shouldBe(Condition.visible).shouldHave(Condition.exactText("Noname"));
        $(Selectors.byClassName("user-username")).shouldHave(Condition.exactText("@" + userRequest.getUsername()));

        // проверяем переход на страницу редактирования профиля
        $(Selectors.byClassName("profile-header")).click();

        // проверяем открытие страницы Edit Profile
        $(Selectors.byText("✏️ Edit Profile")).shouldBe(Condition.visible);

        Selenide.sleep(1000);

        // проверяем ввод недопустимого name, содержащего цифру
        $(Selectors.byAttribute("placeholder", "Enter new name"))
                .sendKeys("Ivan1 Ivanov");

        // проверяем, что недопустимое name сохранилось в поле перед отправкой формы
        $(Selectors.byAttribute("placeholder", "Enter new name"))
                .shouldHave(Condition.value("Ivan1 Ivanov"));

        // проверяем отправку недопустимого name через UI
        $(Selectors.byText("💾 Save Changes")).click();

        // проверяем alert с сообщением о правиле валидации name
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("Name must contain two words with letters only");

        // проверяем закрытие alert с ошибкой
        alert.accept();

        // проверяем через API профиль сразу после отклонённого изменения name
        UserProfileResponse profileAfterChange = new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // проверяем через API, что name и username остались без изменений
        assertThat(profileAfterChange.getName()).isEqualTo(profileBeforeChange.getName());
        assertThat(profileAfterChange.getUsername()).isEqualTo(profileBeforeChange.getUsername());

        // проверяем обновление страницы для повторного получения профиля, т.к. без рефреша name не меняется
        Selenide.refresh();

        // проверяем, что в шапке осталось исходное name Noname и тот же username
        $(Selectors.byClassName("user-name")).shouldBe(Condition.visible).shouldHave(Condition.exactText("Noname"));
        $(Selectors.byClassName("user-username")).shouldHave(Condition.exactText("@" + userRequest.getUsername()));
    }
}
