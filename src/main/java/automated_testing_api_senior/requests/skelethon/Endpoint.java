package automated_testing_api_senior.requests.skelethon;

import automated_testing_api_senior.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoint {
    ADMIN_USER(
            "/admin/users",
            CreateUserRequest.class,
            CreateUserResponse.class
    ),

    LOGIN(
            "/auth/login",
            LoginUserRequest.class,
            LoginUserResponse.class
    ),

    ACCOUNTS(
            "/accounts",
            BaseModel.class,
            CreateAccountResponse.class
    ),

    DEPOSIT(
            "/accounts/deposit",
            DepositRequest.class,
            BaseModel.class
    ),

    TRANSFER(
            "/accounts/transfer",
            TransferRequest.class,
            BaseModel.class
    ),

    CUSTOMER_ACCOUNTS(
            "/customer/accounts",
            BaseModel.class,
            CreateAccountResponse.class
    ),

    CUSTOMER_PROFILE(
            "/customer/profile",
            ChangeUsernameRequest.class,
            UserProfileResponse.class
    );


    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;
}
