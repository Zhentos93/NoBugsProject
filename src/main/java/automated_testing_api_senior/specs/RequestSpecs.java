package automated_testing_api_senior.specs;

import automated_testing_api_senior.configs.Config;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.LoginUserRequest;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestSpecs {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String SERVER_PROPERTY = "server";
    private static final String API_VERSION_PROPERTY = "apiVersion";
    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final String BASIC_AUTH_CREDENTIALS_FORMAT = "%s:%s";

    private static final CreateUserRequest ADMIN = CreateUserRequest.getAdmin();
    private static final String ADMIN_AUTH_HEADER = BASIC_AUTH_PREFIX + Base64.getEncoder().encodeToString(
            BASIC_AUTH_CREDENTIALS_FORMAT.formatted(ADMIN.getUsername(), ADMIN.getPassword())
                    .getBytes(StandardCharsets.UTF_8)
    );
    private static final Map<String, String> authHeaders = new HashMap<>(
            Map.of(ADMIN.getUsername(), ADMIN_AUTH_HEADER)
    );

    private RequestSpecs() {
    }

    private static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilters(List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()))
                .setBaseUri(Config.getProperty(SERVER_PROPERTY) + Config.getProperty(API_VERSION_PROPERTY));
    }

    public static RequestSpecification unAuthSpec() {
        return defaultRequestBuilder().build();
    }

    public static RequestSpecification adminSpec() {
        return defaultRequestBuilder()
                .addHeader(AUTHORIZATION_HEADER, authHeaders.get(ADMIN.getUsername()))
                .build();
    }

    public static RequestSpecification authAsUser(String username, String password) {
        String userAuthHeader;

        if (!authHeaders.containsKey(username)) {
            userAuthHeader = new CrudRequester(
                    RequestSpecs.unAuthSpec(),
                    Endpoint.LOGIN,
                    ResponseSpecs.requestReturnsOK())
                    .post(LoginUserRequest.builder().username(username).password(password).build())
                    .extract()
                    .header(AUTHORIZATION_HEADER);

            authHeaders.put(username, userAuthHeader);
        } else {
            userAuthHeader = authHeaders.get(username);
        }

        return defaultRequestBuilder()
                .addHeader(AUTHORIZATION_HEADER, userAuthHeader)
                .build();
    }

    public static String getUserAuthHeader(String username, String password) {
        if (!authHeaders.containsKey(username)) {
            String userAuthHeader = new CrudRequester(
                    RequestSpecs.unAuthSpec(),
                    Endpoint.LOGIN,
                    ResponseSpecs.requestReturnsOK())
                    .post(LoginUserRequest.builder()
                            .username(username)
                            .password(password)
                            .build())
                    .extract()
                    .header(AUTHORIZATION_HEADER);

            authHeaders.put(username, userAuthHeader);
        }

        return authHeaders.get(username);
    }
}
