package automated_testing_ui_middle;

import lombok.Getter;

@Getter
public enum BankAlert {
    USER_CREATED_SUCCESSFULLY("✅ User created successfully!"),
    USERNAME_MUST_BE_BETWEEN_3_AND_15_CHARACTERS("Username must be between 3 and 15 characters"),
    NEW_ACCOUNT_CREATED("✅ New Account Created! Account Number: "),
    DEPOSIT_SUCCESSFULLY("✅ Successfully deposited $%s to account %s!"),
    DEPOSIT_AMOUNT_EXCEEDS_MAXIMUM("❌ Please deposit less or equal to 5000$."),
    TRANSFER_SUCCESSFULLY("✅ Successfully transferred $%s to account %s!"),
    TRANSFER_REJECTED("❌ Error: Invalid transfer: insufficient funds or invalid accounts"),
    NAME_UPDATED_SUCCESSFULLY("✅ Name updated successfully!"),
    NAME_MUST_CONTAIN_TWO_WORDS_WITH_LETTERS_ONLY("Name must contain two words with letters only");

    private final String message;

    BankAlert(String message) {
        this.message = message;
    }
}
