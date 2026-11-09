package automated_testing.practice_13_2_homework_UiTestsJunior;

import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
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

public class DepositJuniorTest {
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
    public void userCanDepositSelectedAccountTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение того же Authorization header из спецификации для авторизации браузера
        String userAuthHeader = SpecificationQuerier.query(userSpec).getHeaders().getValue("Authorization");

        // проверяем создание первого аккаунта, который будем пополнять через UI
        CreateAccountResponse firstAccount = AccountSteps.createAccount(userSpec);

        // проверяем создание второго аккаунта, баланс которого не должен измениться
        CreateAccountResponse secondAccount = AccountSteps.createAccount(userSpec);

        // проверяем начальный баланс 100.0 для первого аккаунта
        AccountSteps.deposit(userSpec, firstAccount.getId(), 100.0);

        // проверяем начальный баланс 250.0 для второго аккаунта
        AccountSteps.deposit(userSpec, secondAccount.getId(), 250.0);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие приложения, сохранение токена в localStorage и открытие Dashboard
        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        Selenide.open("/dashboard");

        // проверяем переход с Dashboard на страницу пополнения счёта
        $(Selectors.byText("💰 Deposit Money")).click();

        // проверяем открытие страницы Deposit Money
        $(Selectors.byText("💰 Deposit Money")).shouldBe(Condition.visible);

        // проверяем выбор первого аккаунта
        $(Selectors.byClassName("account-selector")).selectOptionContainingText(firstAccount.getAccountNumber());

        // проверяем ввод суммы 100 и отправку формы пополнения
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys("100");
        $(Selectors.byText("💵 Deposit")).click();

        // проверяем успешный alert с номером выбранного первого аккаунта
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains(
                "✅ Successfully deposited $100 to account " + firstAccount.getAccountNumber() + "!"
        );

        // проверяем закрытие успешного alert
        alert.accept();

        // проверяем через API актуальные аккаунты сразу после пополнения
        CreateAccountResponse[] accountsAfterDeposit = AccountSteps.getAccounts(userSpec);

        // проверяем поиск пополненного первого аккаунта по его ID
        CreateAccountResponse firstAccountAfterDeposit = AccountSteps.findAccountById(
                accountsAfterDeposit,
                firstAccount.getId()
        );

        // проверяем поиск второго аккаунта, баланс которого не изменился
        CreateAccountResponse secondAccountAfterDeposit = AccountSteps.findAccountById(
                accountsAfterDeposit,
                secondAccount.getId()
        );

        // проверяем увеличение баланса первого аккаунта со 100.0 до 200.0
        assertThat(firstAccountAfterDeposit.getBalance()).isEqualTo(200.0);

        // проверяем сохранение баланса второго аккаунта на уровне 250.0
        assertThat(secondAccountAfterDeposit.getBalance()).isEqualTo(250.0);
    }

    @Test
    public void userCannotDepositMoreThanMaximumTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // проверяем получение того же Authorization header из спецификации для авторизации браузера
        String userAuthHeader = SpecificationQuerier.query(userSpec).getHeaders().getValue("Authorization");

        // проверяем создание аккаунта, который попробуем пополнить на недопустимую сумму
        CreateAccountResponse account = AccountSteps.createAccount(userSpec);

        // проверяем начальный баланс 100.0
        AccountSteps.deposit(userSpec, account.getId(), 100.0);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие приложения, сохранение токена в localStorage и открытие Dashboard
        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        Selenide.open("/dashboard");

        // проверяем переход с Dashboard на страницу пополнения счёта
        $(Selectors.byText("💰 Deposit Money")).click();

        // проверяем открытие страницы Deposit Money
        $(Selectors.byText("💰 Deposit Money")).shouldBe(Condition.visible);

        // проверяем выбор аккаунта
        $(Selectors.byClassName("account-selector")).selectOptionContainingText(account.getAccountNumber());

        // проверяем ввод недопустимой суммы для пополнения и отправку формы
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys("5000.01");
        $(Selectors.byText("💵 Deposit")).click();

        // проверяем alert с сообщением об отказе в пополнении
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please deposit less or equal to 5000$.");

        // проверяем закрытие alert с ошибкой
        alert.accept();

        // проверяем через API поиск аккаунта по его ID
        CreateAccountResponse accountAfterDeposit = AccountSteps.findAccountById(
                AccountSteps.getAccounts(userSpec),
                account.getId()
        );

        // проверяем сохранение исходного баланса аккаунта 100.0
        assertThat(accountAfterDeposit.getBalance()).isEqualTo(100.0);
    }
}
