package automated_testing_ui_senior.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DepositPage extends BasePage<DepositPage> {
    private final SelenideElement pageTitle = $(Selectors.byText("💰 Deposit Money"));
    private final SelenideElement accountSelector = $(Selectors.byClassName("account-selector"));
    private final SelenideElement amountInput = $(
            Selectors.byAttribute("placeholder", "Enter amount")
    );
    private final SelenideElement depositButton = $(Selectors.byText("💵 Deposit"));

    @Override
    public String url() {
        return "/deposit";
    }

    public DepositPage checkPageIsOpened() {
        pageTitle.shouldBe(Condition.visible);
        accountSelector.shouldBe(Condition.visible);
        amountInput.shouldBe(Condition.visible);
        depositButton.shouldBe(Condition.visible);
        return this;
    }

    public DepositPage selectAccount(String accountNumber) {
        accountSelector.selectOptionContainingText(accountNumber);
        return this;
    }

    public DepositPage checkSelectedAccount(String accountNumber) {
        accountSelector.getSelectedOption().shouldHave(Condition.text(accountNumber));
        return this;
    }

    public DepositPage enterAmount(String amount) {
        amountInput.setValue(amount);
        return this;
    }

    public DepositPage checkEnteredAmount(String amount) {
        amountInput.shouldHave(Condition.value(amount));
        return this;
    }

    public DepositPage submitDeposit() {
        depositButton.click();
        return this;
    }
}
