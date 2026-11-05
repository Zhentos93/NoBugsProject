package automated_testing.practice_6_2_homework_ApiTestsJunior;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ChangeUsernameJuniorTest {
    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    @Test
    public void shouldAllowAuthorizedUserToChangeNameToTwoWordNameWithLettersOnly() {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "%s",
                          "password": "Strong1!",
                          "role": "USER"
                        }
                        """.formatted(username))
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        // проверяем, что токен не null и сохраняем token пользователя
        String userAuthHeader = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "%s",
                          "password": "Strong1!"
                        }
                        """.formatted(username))
                .when()
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .header("Authorization", Matchers.notNullValue())
                .extract()
                .header("Authorization");

        // проверяем изменение name
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "name": "Sasha Pshe"
                        }
                        """)
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем, что новое имя сохранилось в профиле пользователя
        given()
                .header("Authorization", userAuthHeader)
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo("Sasha Pshe"))
                .body("username", Matchers.equalTo(username))
                .body("role", Matchers.equalTo("USER"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sasha", "Sasha Pshe Java", "Sasha Pshe1", "Sasha Pshe%"})
    public void shouldPreventAuthorizedUserFromChangingNameToInvalidName(String invalidName) {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "%s",
                          "password": "Strong1!",
                          "role": "USER"
                        }
                        """.formatted(username))
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        // проверяем, что токен не null и сохраняем token пользователя
        String userAuthHeader = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "%s",
                          "password": "Strong1!"
                        }
                        """.formatted(username))
                .when()
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .header("Authorization", Matchers.notNullValue())
                .extract()
                .header("Authorization");

        // проверяем ранее сгенерированное name и сохраняем перед следующей проверкой
        String nameBefore = given()
                .header("Authorization", userAuthHeader)
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("name");

        // проверяем, что при изменении name получим BAD_REQUEST
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "name": "%s"
                        }
                        """.formatted(invalidName))
                .when()
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        // проверяем, что name не изменилось
        String nameAfter = given()
                .header("Authorization", userAuthHeader)
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("name");

        Assertions.assertEquals(nameBefore, nameAfter);
    }
}