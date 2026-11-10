package automated_testing.practice_7_2_homework_ApiTestsMiddle;

import automated_testing_api_middle.generators.RandomData;
import automated_testing_api_middle.models.ChangeUsernameRequest;
import automated_testing_api_middle.models.CreateUserRequest;
import automated_testing_api_middle.models.UserProfileResponse;
import automated_testing_api_middle.models.UserRole;
import automated_testing_api_middle.requests.AdminCreateUserRequester;
import automated_testing_api_middle.requests.ChangeUsernameRequester;
import automated_testing_api_middle.requests.GetProfileRequester;
import automated_testing_api_middle.specs.RequestSpecs;
import automated_testing_api_middle.specs.ResponseSpecs;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ChangeUsernameMiddleTest extends BaseMiddleTest {

    @Test
    public void shouldAllowAuthorizedUserToChangeNameToTwoWordNameWithLettersOnly() {

        // подготавливаем данные нового пользователя
        CreateUserRequest user = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(RequestSpecs.adminSpec(), ResponseSpecs.entityWasCreated())
                .post(user);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // подготавливаем запрос на изменение имени в профиле пользователя
        ChangeUsernameRequest changeUsernameRequest = ChangeUsernameRequest.builder()
                .name(RandomData.getFullName())
                .build();

        // проверяем успешное изменение имени в профиле пользователя
        new ChangeUsernameRequester(userSpec, ResponseSpecs.requestReturnsOK())
                .put(changeUsernameRequest);

        // запрашиваем актуальные данные профиля после изменения имени
        UserProfileResponse profile = new GetProfileRequester(userSpec, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // проверяем данные профиля после успешного изменения имени
        softly.assertThat(profile.getUsername()).isEqualTo(user.getUsername());
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
        CreateUserRequest user = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        // проверяем создание админом пользователя
        new AdminCreateUserRequester(RequestSpecs.adminSpec(), ResponseSpecs.entityWasCreated())
                .post(user);

        // проверяем авторизацию созданного пользователя и сохраняем спецификацию с его токеном
        RequestSpecification userSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // запрашиваем и сохраняем исходные данные профиля до попытки изменить имя
        UserProfileResponse profileBeforeRequest = new GetProfileRequester(userSpec, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // подготавливаем запрос с невалидным именем
        ChangeUsernameRequest changeUsernameRequest = ChangeUsernameRequest.builder()
                .name(invalidName)
                .build();

        // проверяем неуспешное изменение имени в профиле пользователя
        new ChangeUsernameRequester(userSpec, ResponseSpecs.requestReturnsBadRequest())
                .put(changeUsernameRequest);

        // повторно запрашиваем данные профиля после отклонённого запроса
        UserProfileResponse profileAfterRequest = new GetProfileRequester(userSpec, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        // проверяем, что данные профиля не изменились
        softly.assertThat(profileAfterRequest.getUsername()).isEqualTo(profileBeforeRequest.getUsername());
        softly.assertThat(profileAfterRequest.getRole()).isEqualTo(profileBeforeRequest.getRole());
        softly.assertThat(profileAfterRequest.getName()).isEqualTo(profileBeforeRequest.getName());
    }
}