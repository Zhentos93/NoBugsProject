package automated_testing_api_senior.requests.steps;

import automated_testing_api_senior.models.CreateAccountResponse;
import automated_testing_api_senior.models.DepositRequest;
import automated_testing_api_senior.models.TransferRequest;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.skelethon.requesters.ValidatedCrudRequester;
import automated_testing_api_senior.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AccountSteps {
    private AccountSteps() {
    }

    public static CreateAccountResponse createAccount(RequestSpecification userSpec) {
        return new ValidatedCrudRequester<CreateAccountResponse>(
                userSpec,
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated())
                .post();
    }

    public static void deposit(RequestSpecification userSpec, long accountId, double amount) {
        DepositRequest depositRequest = DepositRequest.builder()
                .id(accountId)
                .balance(amount)
                .build();

        new CrudRequester(
                userSpec,
                Endpoint.DEPOSIT,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);
    }

    public static void transfer(
            RequestSpecification userSpec,
            long senderAccountId,
            long receiverAccountId,
            double amount,
            ResponseSpecification responseSpecification) {
        TransferRequest transferRequest = TransferRequest.builder()
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .amount(amount)
                .build();

        new CrudRequester(
                userSpec,
                Endpoint.TRANSFER,
                responseSpecification)
                .post(transferRequest);
    }

    public static CreateAccountResponse[] getAccounts(RequestSpecification userSpec) {
        return new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);
    }

    public static CreateAccountResponse findAccountById(CreateAccountResponse[] accounts, long accountId) {
        for (CreateAccountResponse account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }

        throw new AssertionError("Account was not found: " + accountId);
    }
}
