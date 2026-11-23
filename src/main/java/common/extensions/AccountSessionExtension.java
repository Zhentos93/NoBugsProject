package common.extensions;

import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import common.RepeatUtils;
import common.annotations.AccountSession;
import common.annotations.UserSession;
import common.storage.AccountSessionStorage;
import common.storage.SessionStorage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AccountSessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        AccountSessionStorage.clear();

        AccountSession annotation = extensionContext.getRequiredTestMethod().getAnnotation(AccountSession.class);
        if (annotation == null) {
            return;
        }

        UserSession userSession = extensionContext.getRequiredTestMethod().getAnnotation(UserSession.class);
        if (userSession == null) {
            throw new IllegalArgumentException("AccountSession requires UserSession");
        }

        int[] accountCounts = annotation.value();
        int expectedUserCount = userSession.value();
        if (accountCounts.length != expectedUserCount) {
            throw new IllegalArgumentException(
                    "AccountSession must contain one account count for every user. Expected %d, but got %d"
                            .formatted(expectedUserCount, accountCounts.length)
            );
        }

        for (int userNumber = 1; userNumber <= accountCounts.length; userNumber++) {
            int accountCount = accountCounts[userNumber - 1];
            if (accountCount < 0) {
                throw new IllegalArgumentException("Account count cannot be negative");
            }

            CreateUserRequest user = SessionStorage.getUser(userNumber);
            RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

            int currentUserNumber = userNumber;
            RepeatUtils.repeat(accountCount, () -> {
                CreateAccountResponse account = AccountSteps.createAccount(userSpec);
                AccountSessionStorage.addAccount(currentUserNumber, account);
            });
        }
    }
}