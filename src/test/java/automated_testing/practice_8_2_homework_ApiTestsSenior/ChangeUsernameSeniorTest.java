package automated_testing.practice_8_2_homework_ApiTestsSenior;

import automated_testing_api_senior.generators.RandomModelGenerator;
import automated_testing_api_senior.models.ChangeUsernameRequest;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.UserProfileResponse;
import automated_testing_api_senior.models.UserRole;
import automated_testing_api_senior.models.comparison.ModelAssertions;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.steps.AdminSteps;
import automated_testing_api_senior.specs.RequestSpecs;
import automated_testing_api_senior.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ChangeUsernameSeniorTest extends BaseSeniorTest {

    @Test
    public void shouldAllowAuthorizedUserToChangeNameToTwoWordNameWithLettersOnly() {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // подготавливаем запрос на изменение имени в профиле пользователя
        ChangeUsernameRequest changeUsernameRequest = RandomModelGenerator.generate(ChangeUsernameRequest.class);

        // проверяем успешное изменение имени в профиле пользователя
        new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK())
                .put(changeUsernameRequest);

        // запрашиваем актуальные данные профиля после изменения имени
        UserProfileResponse profile = getProfile(userSpec);

        // проверяем данные профиля после успешного изменения имени
        softly.assertThat(profile.getUsername()).isEqualTo(userRequest.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo(UserRole.USER.toString());
        ModelAssertions.assertThatModels(changeUsernameRequest, profile).match();
    }

    public static Stream<Arguments> invalidNames() {
        return Stream.of(
                Arguments.of("Sasha"),
                Arguments.of("Sasha Pshe Java"),
                Arguments.of("Sasha Pshe1"),
                Arguments.of("Sasha Pshe%")
        );
    }

    @MethodSource("invalidNames")
    @ParameterizedTest
    public void shouldPreventAuthorizedUserFromChangingNameToInvalidName(String invalidName) {
        // проверяем создание админом пользователя
        CreateUserRequest userRequest = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        // запрашиваем и сохраняем исходные данные профиля до попытки изменить имя
        UserProfileResponse profileBeforeRequest = getProfile(userSpec);

        // подготавливаем запрос с невалидным именем
        ChangeUsernameRequest changeUsernameRequest = ChangeUsernameRequest.builder()
                .name(invalidName)
                .build();

        // проверяем неуспешное изменение имени в профиле пользователя
        new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsBadRequest())
                .put(changeUsernameRequest);

        // повторно запрашиваем данные профиля после отклонённого запроса
        UserProfileResponse profileAfterRequest = getProfile(userSpec);

        // проверяем, что данные профиля не изменились
        softly.assertThat(profileAfterRequest.getUsername()).isEqualTo(profileBeforeRequest.getUsername());
        softly.assertThat(profileAfterRequest.getRole()).isEqualTo(profileBeforeRequest.getRole());
        softly.assertThat(profileAfterRequest.getName()).isEqualTo(profileBeforeRequest.getName());
    }

    private UserProfileResponse getProfile(RequestSpecification userSpec) {
        return new CrudRequester(
                userSpec,
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);
    }
}
