package common.storage;

import automated_testing_api_senior.models.CreateAccountResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AccountSessionStorage {
    private static final AccountSessionStorage INSTANCE = new AccountSessionStorage();

    private final Map<Integer, List<CreateAccountResponse>> accountsByUser = new LinkedHashMap<>();

    private AccountSessionStorage() {
    }

    public static void addAccount(int userNumber, CreateAccountResponse account) {
        INSTANCE.accountsByUser
                .computeIfAbsent(userNumber, key -> new ArrayList<>())
                .add(account);
    }

    public static List<CreateAccountResponse> getAccounts(int userNumber) {
        return List.copyOf(INSTANCE.accountsByUser.getOrDefault(userNumber, List.of()));
    }

    public static CreateAccountResponse getAccount(int userNumber, int accountNumber) {
        List<CreateAccountResponse> accounts = getAccounts(userNumber);
        if (accountNumber < 1 || accountNumber > accounts.size()) {
            throw new IllegalArgumentException("Account number must be between 1 and %d, but was %d"
                    .formatted(accounts.size(), accountNumber));
        }
        return accounts.get(accountNumber - 1);
    }

    public static CreateAccountResponse getAccount(int accountNumber) {
        return getAccount(1, accountNumber);
    }

    public static void clear() {
        INSTANCE.accountsByUser.clear();
    }
}
