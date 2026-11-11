package automated_testing.practice_8_1_practice_ApiTestsSenior;

import automated_testing.practice_8_2_homework_ApiTestsSenior.BaseSeniorTest;
import automated_testing_api_senior.generators.RandomModelGenerator;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.CreateUserResponse;
import automated_testing_api_senior.models.comparison.ModelAssertions;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.skelethon.requesters.ValidatedCrudRequester;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_api_senior.specs.ResponseSpecs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreateUserSeniorTest extends BaseSeniorTest {
    @Test
    public void adminCanCreateUserWithCorrectData() {
        CreateUserRequest createUserRequest =
                RandomModelGenerator.generate(CreateUserRequest.class);

        CreateUserResponse createUserResponse = new ValidatedCrudRequester<CreateUserResponse>
                (RequestSpecs.adminSpec(),
                        Endpoint.ADMIN_USER,
                        ResponseSpecs.entityWasCreated())
                .post(createUserRequest);

        ModelAssertions.assertThatModels(createUserRequest, createUserResponse).match();
    }

    public static Stream<Arguments> userInvalidData() {
        return Stream.of(
                // username field validation
                Arguments.of("   ", "Password33$", "USER", "username", "Username cannot be blank"),
                Arguments.of("ab", "Password33$", "USER", "username", "Username must be between 3 and 15 characters"),
                Arguments.of("abc$", "Password33$", "USER", "username", "Username must contain only letters, digits, dashes, underscores, and dots"),
                Arguments.of("abc%", "Password33$", "USER", "username", "Username must contain only letters, digits, dashes, underscores, and dots")
        );

    }

    @MethodSource("userInvalidData")
    @ParameterizedTest
    public void adminCanNotCreateUserWithInvalidData(String username, String password, String role, String errorKey, String errorValue) {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        new CrudRequester(RequestSpecs.adminSpec(),
                Endpoint.ADMIN_USER,
                ResponseSpecs.requestReturnsBadRequest(errorKey, errorValue))
                .post(createUserRequest);
    }
}
