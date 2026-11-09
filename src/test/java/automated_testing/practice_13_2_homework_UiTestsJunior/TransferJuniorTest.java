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

public class TransferJuniorTest {
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
    public void userCanTransferMoneyToAnotherUserTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя-отправителя
        CreateUserRequest senderUser = AdminSteps.createUser();

        // проверяем авторизацию отправителя и сохраняем спецификацию с Authorization header
        RequestSpecification senderSpec = RequestSpecs.authAsUser(senderUser.getUsername(), senderUser.getPassword());

        // проверяем получение того же Authorization header для авторизации отправителя в браузере
        String senderAuthHeader = SpecificationQuerier
                .query(senderSpec)
                .getHeaders()
                .getValue("Authorization");

        // проверяем создание админом пользователя-получателя
        CreateUserRequest receiverUser = AdminSteps.createUser();

        // проверяем авторизацию получателя и сохраняем спецификацию с Authorization header
        RequestSpecification receiverSpec = RequestSpecs.authAsUser(
                receiverUser.getUsername(),
                receiverUser.getPassword()
        );

        // проверяем создание первого аккаунта отправителя, с которого выполним перевод
        CreateAccountResponse senderFirstAccount = AccountSteps.createAccount(senderSpec);

        // проверяем создание второго аккаунта отправителя, баланс которого не должен измениться
        CreateAccountResponse senderSecondAccount = AccountSteps.createAccount(senderSpec);

        // проверяем создание аккаунта получателя, на который выполним перевод
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(receiverSpec);

        // проверяем начальный баланс 1000.0 для первого аккаунта отправителя
        AccountSteps.deposit(senderSpec, senderFirstAccount.getId(), 1000.0);

        // проверяем начальный баланс 50.0 для второго аккаунта отправителя
        AccountSteps.deposit(senderSpec, senderSecondAccount.getId(), 50.0);

        // проверяем начальный баланс 200.0 для первого аккаунта получателя
        AccountSteps.deposit(receiverSpec, receiverAccount.getId(), 200.0);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие приложения, сохранение токена отправителя и открытие Dashboard
        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", senderAuthHeader);
        Selenide.open("/dashboard");

        // проверяем, что отправитель успешно авторизован в UI
        $(Selectors.byClassName("welcome-text"))
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Welcome, noname!"));

        // проверяем переход на страницу перевода денег
        $(Selectors.byText("🔄 Make a Transfer")).click();

        // проверяем открытие страницы Make a Transfer
        $(Selectors.byText("🔄 Make a Transfer")).shouldBe(Condition.visible);

        // проверяем выбор режима нового перевода
        $(Selectors.byText("🆕 New Transfer")).click();

        // проверяем выбор первого аккаунта отправителя
        $(Selectors.byClassName("account-selector")).selectOptionContainingText(senderFirstAccount.getAccountNumber());

        // проверяем ввод номера аккаунта получателя при пустом Recipient Name
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .sendKeys(receiverAccount.getAccountNumber());

        // проверяем ввод суммы перевода 100
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys("100");

        // проверяем установку checkbox подтверждения правильности данных
        $("#confirmCheck").click();
        $("#confirmCheck").shouldBe(Condition.selected);

        // проверяем отправку перевода через UI
        $(Selectors.byText("🚀 Send Transfer")).click();

        // проверяем успешный alert с номером аккаунта получателя
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains(
                "✅ Successfully transferred $100 to account " + receiverAccount.getAccountNumber() + "!"
        );

        // проверяем закрытие успешного alert
        alert.accept();

        // проверяем через API итоговые балансы отправителя и получателя сразу после успешного перевода
        CreateAccountResponse[] senderAccountsAfterTransfer = AccountSteps.getAccounts(senderSpec);
        CreateAccountResponse[] receiverAccountsAfterTransfer = AccountSteps.getAccounts(receiverSpec);

        assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderFirstAccount.getId())
                .getBalance()).isEqualTo(900.0);

        assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderSecondAccount.getId())
                .getBalance()).isEqualTo(50.0);

        assertThat(AccountSteps.findAccountById(receiverAccountsAfterTransfer, receiverAccount.getId())
                .getBalance()).isEqualTo(300.0);
    }

    @Test
    public void userCannotTransferMoreThanAvailableBalanceTest() {
        // ПРОВЕРКА API-ПРЕДУСЛОВИЙ \\

        // проверяем создание админом пользователя-отправителя
        CreateUserRequest senderUser = AdminSteps.createUser();

        // проверяем авторизацию отправителя и сохраняем спецификацию с Authorization header
        RequestSpecification senderSpec = RequestSpecs.authAsUser(senderUser.getUsername(), senderUser.getPassword());

        // проверяем получение того же Authorization header для авторизации отправителя в браузере
        String senderAuthHeader = SpecificationQuerier
                .query(senderSpec)
                .getHeaders()
                .getValue("Authorization");

        // проверяем создание админом пользователя-получателя
        CreateUserRequest receiverUser = AdminSteps.createUser();

        // проверяем авторизацию получателя и сохраняем спецификацию с Authorization header
        RequestSpecification receiverSpec = RequestSpecs.authAsUser(
                receiverUser.getUsername(),
                receiverUser.getPassword()
        );

        // проверяем создание аккаунта отправителя с недостаточным балансом
        CreateAccountResponse senderAccount = AccountSteps.createAccount(senderSpec);

        // проверяем создание аккаунта получателя
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(receiverSpec);

        // проверяем начальный баланс 50.0 для аккаунта отправителя
        AccountSteps.deposit(senderSpec, senderAccount.getId(), 50.0);

        // проверяем начальный баланс 200.0 для аккаунта получателя
        AccountSteps.deposit(receiverSpec, receiverAccount.getId(), 200.0);

        // ПРОВЕРКА UI-ШАГОВ И РЕЗУЛЬТАТА ЧЕРЕЗ API \\

        // проверяем открытие приложения, сохранение токена отправителя и открытие Dashboard
        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", senderAuthHeader);
        Selenide.open("/dashboard");

        // проверяем, что отправитель успешно авторизован в UI
        $(Selectors.byClassName("welcome-text"))
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Welcome, noname!"));

        // проверяем переход на страницу перевода денег
        $(Selectors.byText("🔄 Make a Transfer")).click();

        // проверяем открытие страницы Make a Transfer
        $(Selectors.byText("🔄 Make a Transfer")).shouldBe(Condition.visible);

        // проверяем выбор режима нового перевода
        $(Selectors.byText("🆕 New Transfer")).click();

        // проверяем выбор аккаунта отправителя
        $(Selectors.byClassName("account-selector")).selectOptionContainingText(senderAccount.getAccountNumber());

        // проверяем ввод номера аккаунта получателя
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .sendKeys(receiverAccount.getAccountNumber());

        // проверяем ввод суммы 100
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys("100");

        // проверяем установку checkbox подтверждения правильности данных
        $("#confirmCheck").click();
        $("#confirmCheck").shouldBe(Condition.selected);

        // проверяем отправку перевода через UI
        $(Selectors.byText("🚀 Send Transfer")).click();

        // проверяем alert с сообщением об отказе из-за недостаточного баланса
        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains(
                "❌ Error: Invalid transfer: insufficient funds or invalid accounts"
        );

        // проверяем закрытие alert с ошибкой
        alert.accept();

        // проверяем через API итоговые балансы отправителя и получателя сразу после неуспешного перевода
        CreateAccountResponse[] senderAccountsAfterTransfer = AccountSteps.getAccounts(senderSpec);
        CreateAccountResponse[] receiverAccountsAfterTransfer = AccountSteps.getAccounts(receiverSpec);

        assertThat(AccountSteps.findAccountById(senderAccountsAfterTransfer, senderAccount.getId())
                .getBalance()).isEqualTo(50.0);

        assertThat(AccountSteps.findAccountById(receiverAccountsAfterTransfer, receiverAccount.getId())
                .getBalance()).isEqualTo(200.0);
    }
}
