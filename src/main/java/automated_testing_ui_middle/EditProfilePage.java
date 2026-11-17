package automated_testing_ui_middle;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EditProfilePage extends BasePage<EditProfilePage> {
    private final SelenideElement pageTitle = $(Selectors.byText("✏️ Edit Profile"));
    private final SelenideElement nameInput = $(
            Selectors.byAttribute("placeholder", "Enter new name")
    );
    private final SelenideElement saveChangesButton = $(Selectors.byText("💾 Save Changes"));
    private final SelenideElement userName = $(Selectors.byClassName("user-name"));
    private final SelenideElement userUsername = $(Selectors.byClassName("user-username"));

    @Override
    public String url() {
        return "/profile";
    }

    public EditProfilePage checkPageIsOpened() {
        pageTitle.shouldBe(Condition.visible);
        nameInput.shouldBe(Condition.visible);
        saveChangesButton.shouldBe(Condition.visible);
        return this;
    }

    public EditProfilePage waitUntilProfileIsLoaded() {
        Selenide.sleep(1000);
        return this;
    }

    public EditProfilePage enterName(String name) {
        nameInput.setValue(name);
        return this;
    }

    public EditProfilePage checkEnteredName(String name) {
        nameInput.shouldHave(Condition.value(name));
        return this;
    }

    public EditProfilePage saveChanges() {
        saveChangesButton.click();
        return this;
    }

    public EditProfilePage refresh() {
        Selenide.refresh();
        return this;
    }

    public EditProfilePage checkProfileHeader(String expectedName, String expectedUsername) {
        userName.shouldBe(Condition.visible).shouldHave(Condition.exactText(expectedName));
        userUsername.shouldHave(Condition.exactText("@" + expectedUsername));
        return this;
    }
}
