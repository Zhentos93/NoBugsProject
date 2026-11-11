package automated_testing.practice_8_2_homework_ApiTestsSenior;

import automated_testing_api_senior.generators.RandomData;
import automated_testing_api_senior.generators.RandomModelGenerator;
import automated_testing_api_senior.models.ChangeUsernameRequest;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.models.UserProfileResponse;
import automated_testing_api_senior.models.UserRole;
import automated_testing_api_senior.models.comparison.ModelAssertions;
import automated_testing_api_senior.requests.skelethon.Endpoint;
import automated_testing_api_senior.requests.skelethon.requesters.CrudRequester;
import automated_testing_api_senior.requests.steps.AccountSteps;
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
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // подготавливаем запрос на изменение имени в профиле пользователя
        ChangeUsernameRequest changeUsernameRequest = RandomModelGenerator.generate(ChangeUsernameRequest.class);

        // проверяем успешное изменение имени в профиле пользователя
        new CrudRequester(userSpec, Endpoint.CUSTOMER_PROFILE, ResponseSpecs.requestReturnsOK())
                .put(changeUsernameRequest);

        // запрашиваем актуальные данные профиля после изменения имени
        UserProfileResponse profile = AccountSteps.getProfile(userSpec);

        // проверяем данные профиля после успешного изменения имени
        softly.assertThat(profile.getUsername()).isEqualTo(user.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo(UserRole.USER.toString());
        ModelAssertions.assertThatModels(changeUsernameRequest, profile).match();
    }

    public static Stream<Arguments> invalidNames() {
        return Stream.of(
                Arguments.of(RandomData.getOneWordName()),
                Arguments.of(RandomData.getThreeWordName()),
                Arguments.of(RandomData.getNameWithDigit()),
                Arguments.of(RandomData.getNameWithSpecialCharacter())
        );
    }

    @MethodSource("invalidNames")
    @ParameterizedTest
    public void shouldPreventAuthorizedUserFromChangingNameToInvalidName(String invalidName) {
        // проверяем создание админом пользователя
        CreateUserRequest user = AdminSteps.createUser();

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // запрашиваем и сохраняем исходные данные профиля до попытки изменить имя
        UserProfileResponse profileBeforeRequest = AccountSteps.getProfile(userSpec);

        // подготавливаем запрос с невалидным именем
        ChangeUsernameRequest changeUsernameRequest = ChangeUsernameRequest.builder()
                .name(invalidName)
                .build();

        // проверяем неуспешное изменение имени в профиле пользователя
        new CrudRequester(userSpec, Endpoint.CUSTOMER_PROFILE, ResponseSpecs.requestReturnsBadRequest())
                .put(changeUsernameRequest);

        // повторно запрашиваем данные профиля после отклонённого запроса
        UserProfileResponse profileAfterRequest = AccountSteps.getProfile(userSpec);

        // проверяем, что данные профиля не изменились
        softly.assertThat(profileAfterRequest.getUsername()).isEqualTo(profileBeforeRequest.getUsername());
        softly.assertThat(profileAfterRequest.getRole()).isEqualTo(profileBeforeRequest.getRole());
        softly.assertThat(profileAfterRequest.getName()).isEqualTo(profileBeforeRequest.getName());
    }
}