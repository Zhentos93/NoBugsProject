package automated_testing_ui_senior.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProfileHeader extends BaseElement {
    private static final String DEFAULT_DISPLAY_NAME = "Noname";

    public ProfileHeader(SelenideElement element) {
        super(element);
    }

    public ProfileHeader open() {
        element.shouldBe(Condition.visible).click();
        return this;
    }

    public ProfileHeader checkProfile(String expectedName, String expectedUsername) {
        element.shouldBe(Condition.visible);
        find(".user-name").shouldHave(Condition.exactText(displayName(expectedName)));
        find(".user-username").shouldHave(Condition.exactText("@" + expectedUsername));
        return this;
    }

    private String displayName(String apiName) {
        return apiName == null || apiName.isBlank() ? DEFAULT_DISPLAY_NAME : apiName;
    }
}
