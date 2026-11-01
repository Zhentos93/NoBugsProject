package automated_testing.practice_7_2_homework_ApiTestsMiddle;

import automated_testing.generators.RandomData;
import automated_testing.models.ChangeUsernameRequest;
import automated_testing.models.CreateUserRequest;
import automated_testing.models.UserProfileResponse;
import automated_testing.models.UserRole;
import automated_testing.requests.AdminCreateUserRequester;
import automated_testing.requests.ChangeUsernameRequester;
import automated_testing.requests.GetProfileRequester;
import automated_testing.specs.RequestSpecs;
import automated_testing.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ChangeUsernameMiddleTest extends BaseTest {

    @Test
    public void shouldAllowAuthorizedUserToChangeNameToTwoWordNameWithLettersOnly() {

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // подготавливаем запрос на изменение имени в профиле пользователя
        ChangeUsernameRequest changeUsernameRequest = ChangeUsernameRequest.builder()
                .name("Sasha Pshe")
                .build();

        // проверяем успешное изменение имени в профиле пользователя
        new ChangeUsernameRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .put(ChangeUsernameRequest.builder()
                        .name("Sasha Pshe")
                        .build());

        // запрашиваем актуальные данные профиля после изменения имени
        UserProfileResponse profile = new GetProfileRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // проверяем данные профиля после успешного изменения имени
        softly.assertThat(profile.getUsername()).isEqualTo(userRequest.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo(UserRole.USER.toString());
        softly.assertThat(profile.getName()).isEqualTo(changeUsernameRequest.getName());
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

        // подготавливаем данные нового пользователя
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(
                userRequest.getUsername(),
                userRequest.getPassword());

        // запрашиваем и сохраняем исходные данные профиля до попытки изменить имя
        UserProfileResponse profileBeforeRequest = new GetProfileRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // подготавливаем запрос с невалидным именем
        ChangeUsernameRequest changeUsernameRequest = ChangeUsernameRequest.builder()
                .name(invalidName)
                .build();

        // проверяем неуспешное изменение имени в профиле пользователя
        new ChangeUsernameRequester(
                userSpec,
                ResponseSpecs.requestReturnsBadRequest())
                .put(changeUsernameRequest);

        // повторно запрашиваем данные профиля после отклонённого запроса
        UserProfileResponse profileAfterRequest = new GetProfileRequester(
                userSpec,
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // проверяем, что данные профиля не изменились
        softly.assertThat(profileAfterRequest.getUsername()).isEqualTo(profileBeforeRequest.getUsername());
        softly.assertThat(profileAfterRequest.getRole()).isEqualTo(profileBeforeRequest.getRole());
        softly.assertThat(profileAfterRequest.getName()).isEqualTo(profileBeforeRequest.getName());
    }
}
