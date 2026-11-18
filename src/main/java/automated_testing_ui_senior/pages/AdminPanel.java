package automated_testing_ui_senior.pages;

import automated_testing_ui_senior.elements.UserBage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class AdminPanel extends BasePage<AdminPanel> {
    private final SelenideElement adminPanelText = $(Selectors.byText("Admin Panel"));
    private final SelenideElement addUserButton = $(Selectors.byText("Add User"));

    @Override
    public String url() {
        return "/admin";
    }

    public AdminPanel createUser(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        addUserButton.click();
        return this;
    }

    public UserBage getUser(String username, String role) {
        SelenideElement userElement = getUserElements()
                .findBy(Condition.exactText(username + "\n" + role))
                .shouldBe(Condition.visible);
        return new UserBage(userElement);
    }

    public AdminPanel checkUserIsAbsent(String username, String role) {
        getUserElements().findBy(Condition.exactText(username + "\n" + role)).shouldNotBe(Condition.exist);
        return this;
    }

    private ElementsCollection getUserElements() {
        return $(Selectors.byText("All Users")).parent().findAll("li");
    }
}
