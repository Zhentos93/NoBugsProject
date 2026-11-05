package automated_testing.practice_7_1_practice_ApiTestsMiddle;

import automated_testing.practice_7_2_homework_ApiTestsMiddle.BaseMiddleTest;
import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.CreateUserResponse;
import automated_testing_api_middle.models.UserRole;
import automated_testing_api_middle.requests.AdminCreateUserRequester;
import automated_testing_api_middle.specs.RequestSpecs;
import automated_testing_api_middle.specs.ResponseSpecs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreateUserMiddleTest extends BaseMiddleTest {
    @Test
    public void adminCanCreateUserWithCorrectData() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        CreateUserResponse createUserResponse = new AdminCreateUserRequester(RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(createUserRequest).extract().as(CreateUserResponse.class);

        softly.assertThat(createUserRequest.getUsername()).isEqualTo(createUserResponse.getUsername());
        softly.assertThat(createUserRequest.getPassword()).isNotEqualTo(createUserResponse.getPassword());
        softly.assertThat(createUserRequest.getRole()).isEqualTo(createUserResponse.getRole());
    }

    public static Stream<Arguments> userInvalidData() {
        return Stream.of(
                // username field validation
                Arguments.of("   ", "Password33$", "USER", "username",
                        "Username cannot be blank"),
                Arguments.of("ab", "Password33$", "USER", "username",
                        "Username must be between 3 and 15 characters"),
                Arguments.of("abc$", "Password33$", "USER", "username",
                        "Username must contain only letters, digits, dashes, underscores, and dots"),
                Arguments.of("abc%", "Password33$", "USER", "username",
                        "Username must contain only letters, digits, dashes, underscores, and dots")
        );
    }

    @MethodSource("userInvalidData")
    @ParameterizedTest
    public void adminCanNotCreateUserWithInvalidData(
            String username,
            String password,
            String role,
            String errorKey,
            String errorValue) {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        new AdminCreateUserRequester(RequestSpecs.adminSpec(),
                ResponseSpecs.requestReturnsBadRequest(errorKey, errorValue))
                .post(createUserRequest);
    }
}
