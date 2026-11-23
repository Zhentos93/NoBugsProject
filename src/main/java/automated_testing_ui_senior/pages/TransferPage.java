package automated_testing_ui_senior.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage extends BasePage<TransferPage> {
    private final SelenideElement pageTitle = $(Selectors.byText("🔄 Make a Transfer"));
    private final SelenideElement newTransferButton = $(Selectors.byText("🆕 New Transfer"));
    private final SelenideElement accountSelector = $(Selectors.byClassName("account-selector"));
    private final SelenideElement recipientNameInput = $(
            Selectors.byAttribute("placeholder", "Enter recipient name")
    );
    private final SelenideElement recipientAccountInput = $(
            Selectors.byAttribute("placeholder", "Enter recipient account number")
    );
    private final SelenideElement amountInput = $(Selectors.byAttribute(
            "placeholder", "Enter amount"));
    private final SelenideElement confirmationCheckbox = $("#confirmCheck");
    private final SelenideElement sendTransferButton = $(Selectors.byText("🚀 Send Transfer"));

    @Override
    public String url() {
        return "/transfer";
    }

    public TransferPage checkPageIsOpened() {
        pageTitle.shouldBe(Condition.visible);
        newTransferButton.shouldBe(Condition.visible);
        return this;
    }

    public TransferPage startNewTransfer() {
        newTransferButton.click();
        accountSelector.shouldBe(Condition.visible);
        recipientNameInput.shouldBe(Condition.visible);
        recipientAccountInput.shouldBe(Condition.visible);
        amountInput.shouldBe(Condition.visible);
        confirmationCheckbox.shouldBe(Condition.visible);
        sendTransferButton.shouldBe(Condition.visible);
        return this;
    }

    public TransferPage selectSenderAccount(String accountNumber) {
        accountSelector.selectOptionContainingText(accountNumber);
        return this;
    }

    public TransferPage checkSelectedSenderAccount(String accountNumber) {
        accountSelector.getSelectedOption().shouldHave(Condition.text(accountNumber));
        return this;
    }

    public TransferPage checkRecipientNameIsEmpty() {
        recipientNameInput.shouldBe(Condition.empty);
        return this;
    }

    public TransferPage enterRecipientAccountNumber(String accountNumber) {
        recipientAccountInput.setValue(accountNumber);
        return this;
    }

    public TransferPage checkRecipientAccountNumber(String accountNumber) {
        recipientAccountInput.shouldHave(Condition.value(accountNumber));
        return this;
    }

    public TransferPage enterAmount(String amount) {
        amountInput.setValue(amount);
        return this;
    }

    public TransferPage checkEnteredAmount(String amount) {
        amountInput.shouldHave(Condition.value(amount));
        return this;
    }

    public TransferPage confirmDetails() {
        confirmationCheckbox.setSelected(true).shouldBe(Condition.selected);
        return this;
    }

    public TransferPage submitTransfer() {
        sendTransferButton.click();
        return this;
    }
}
