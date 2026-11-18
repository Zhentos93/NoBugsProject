package automated_testing_ui_senior.elements;

import com.codeborne.selenide.SelenideElement;

public abstract class BaseElement {
    protected final SelenideElement element;

    public BaseElement(SelenideElement element) {
        this.element = element;
    }

    protected SelenideElement find(String cssSelector) {
        return element.find(cssSelector);
    }
}
