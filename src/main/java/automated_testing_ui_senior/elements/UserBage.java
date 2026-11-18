package automated_testing_ui_senior.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UserBage extends BaseElement {
    private final String username;
    private final String role;

    public UserBage(SelenideElement element) {
        super(element);
        username = element.getText().split("\n")[0];
        role = element.getText().split("\n")[1];
    }
}
