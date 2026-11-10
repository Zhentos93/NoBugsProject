package automated_testing_api_middle.requests.steps;

import automated_testing_api_middle.models.AccountResponse;

public class AccountSteps {
    private AccountSteps() {
    }

    public static AccountResponse findAccountById(AccountResponse[] accounts, long accountId) {
        for (AccountResponse account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }

        throw new AssertionError("Account was not found: " + accountId);
    }
}
