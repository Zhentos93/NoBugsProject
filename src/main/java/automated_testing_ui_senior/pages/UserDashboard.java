package automated_testing_ui_senior.pages;

import automated_testing_ui_senior.elements.ProfileHeader;
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
    private final ProfileHeader profileHeader = new ProfileHeader($(Selectors.byClassName("profile-header")));

    @Override
    public String url() {
        return "/dashboard";
    }

    public UserDashboard createNewAccount() {
        createNewAccount.click();
        return this;
    }

    public DepositPage openDepositPage() {
        depositMoneyButton.shouldBe(Condition.visible).click();
        return getPage(DepositPage.class);
    }

    public TransferPage openTransferPage() {
        transferMoneyButton.shouldBe(Condition.visible).click();
        return getPage(TransferPage.class);
    }

    public EditProfilePage openEditProfilePage() {
        profileHeader.open();
        return getPage(EditProfilePage.class);
    }

    public UserDashboard checkProfile(String expectedName, String expectedUsername) {
        profileHeader.checkProfile(expectedName, expectedUsername);
        return this;
    }
}
