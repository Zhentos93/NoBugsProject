package automated_testing_ui_middle;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class UserDashboard extends BasePage<UserDashboard> {
    private final SelenideElement welcomeText = $(Selectors.byClassName("welcome-text"));
    private final SelenideElement createNewAccount = $(Selectors.byText("➕ Create New Account"));
    private final SelenideElement depositMoneyButton = $(Selectors.byText("💰 Deposit Money"));
    private final SelenideElement transferMoneyButton = $(Selectors.byText("🔄 Make a Transfer"));
    private final SelenideElement profileHeader = $(Selectors.byClassName("profile-header"));
    private final SelenideElement userName = $(Selectors.byClassName("user-name"));
    private final SelenideElement userUsername = $(Selectors.byClassName("user-username"));

    @Override
    public String url() {
        return "/dashboard";
    }

    public UserDashboard createNewAccount() {
        createNewAccount.click();
        return this;
    }

    public UserDashboard checkWelcomeText(String expectedText) {
        welcomeText.shouldBe(Condition.visible).shouldHave(Condition.text(expectedText));
        return this;
    }

    public DepositPage openDepositPage() {
        depositMoneyButton.click();
        return getPage(DepositPage.class);
    }

    public TransferPage openTransferPage() {
        transferMoneyButton.click();
        return getPage(TransferPage.class);
    }

    public EditProfilePage openEditProfilePage() {
        profileHeader.click();
        return getPage(EditProfilePage.class);
    }

    public UserDashboard checkProfile(String expectedName, String expectedUsername) {
        userName.shouldBe(Condition.visible).shouldHave(Condition.exactText(expectedName));
        userUsername.shouldHave(Condition.exactText("@" + expectedUsername));
        return this;
    }
}
