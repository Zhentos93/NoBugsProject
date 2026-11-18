package common.extensions;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.requests.steps.AccountSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import common.annotations.AccountSession;
import common.annotations.BalanceSession;
import common.storage.AccountSessionStorage;
import common.storage.SessionStorage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BalanceSessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        BalanceSession annotation = extensionContext.getRequiredTestMethod().getAnnotation(BalanceSession.class);
        if (annotation == null) {
            return;
        }

        AccountSession accountSession = extensionContext.getRequiredTestMethod().getAnnotation(AccountSession.class);
        if (accountSession == null) {
            throw new IllegalArgumentException("BalanceSession requires AccountSession");
        }

        for (int userNumber = 1; userNumber <= accountSession.value().length; userNumber++) {
            CreateUserRequest user = SessionStorage.getUser(userNumber);
            RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

            for (CreateAccountResponse account : AccountSessionStorage.getAccounts(userNumber)) {
                double initialBalance = RandomData.getValidDepositAmount();
                AccountSteps.deposit(userSpec, account.getId(), initialBalance);
                account.setBalance(initialBalance);
            }
        }
    }
}
