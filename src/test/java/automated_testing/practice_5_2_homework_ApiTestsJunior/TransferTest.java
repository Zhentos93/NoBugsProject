package automated_testing.practice_5_2_homework_ApiTestsJunior;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TransferTest {
    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    @Test
    public void shouldAllowAuthorizedUserToTransferMinimumAmountBetweenOwnAccounts() {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        // проверяем создание авторизованного пользователя
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

        // проверяем создание аккаунта с которого будем переводить
        Integer senderAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем создание аккаунта на который будем переводить
        Integer receiverAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем пополнение аккаунта с которого будем переводить (счёт)
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 100
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем перевод денег между своими аккаунтами
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "senderAccountId": %d,
                          "receiverAccountId": %d,
                          "amount": 0.01
                        }
                        """.formatted(senderAccountId, receiverAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldAllowAuthorizedUserToTransferMinimumAmountToAnotherUsersAccount() {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);
        String anotherUsername = "katya" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        // проверяем создание авторизованного пользователя-отправителя
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

        // проверяем создание аккаунта пользователя-отправителя (счет)
        Integer senderAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем пополнение аккаунта пользователя-отправителя (счет)
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 100
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем создание пользователя-получателя
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
                        """.formatted(anotherUsername))
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        // проверяем, что токен не null и сохраняем token пользователя
        String anotherUserAuthHeader = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "%s",
                          "password": "Strong1!"
                        }
                        """.formatted(anotherUsername))
                .when()
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .header("Authorization", Matchers.notNullValue())
                .extract()
                .header("Authorization");

        // проверяем создание аккаунта пользователя-получателя (счет)
        Integer receiverAccountId = given()
                .header("Authorization", anotherUserAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем перевод денег с аккаунта отправителя на аккаунт получателя
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "senderAccountId": %d,
                          "receiverAccountId": %d,
                          "amount": 0.011
                        }
                        """.formatted(senderAccountId, receiverAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @ParameterizedTest
    @ValueSource(strings = {"9999.99", "10000"})
    public void shouldAllowAuthorizedUserToTransferMaximumAmount(String amount) {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        // проверяем создание авторизованного пользователя
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

        // проверяем создание аккаунта с которого будем переводить
        Integer senderAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем создание аккаунта на который будем переводить
        Integer receiverAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем пополнение аккаунта с которого будем переводить (счёт) на 10000
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 5000
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 5000
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем перевод денег между своими аккаунтами (счетами) на 9999.99 и 10000
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "senderAccountId": %d,
                          "receiverAccountId": %d,
                          "amount": %s
                        }
                        """.formatted(senderAccountId, receiverAccountId, amount))
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldPreventAuthorizedUserFromTransferringZeroAmount() {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        // проверяем создание авторизованного пользователя
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

        // проверяем создание аккаунта с которого будем переводить
        Integer senderAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем создание аккаунта на который будем переводить
        Integer receiverAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем пополнение аккаунта с которого будем переводить (счёт)
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 100
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем перевод денег между своими аккаунтами
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "senderAccountId": %d,
                          "receiverAccountId": %d,
                          "amount": 0
                        }
                        """.formatted(senderAccountId, receiverAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.0099", "10000.01"})
    public void shouldPreventAuthorizedUserFromTransferringAmountOutsideAllowedRange(String amount) {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        // проверяем создание авторизованного пользователя
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

        // проверяем создание аккаунта с которого будем переводить
        Integer senderAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем создание аккаунта на который будем переводить
        Integer receiverAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем пополнение аккаунта с которого будем переводить (счёт) на 10000
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 5000
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 5000
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 5000
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем перевод денег между своими аккаунтами (счетами) на 9999.99 и 10000
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "senderAccountId": %d,
                          "receiverAccountId": %d,
                          "amount": %s
                        }
                        """.formatted(senderAccountId, receiverAccountId, amount))
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldPreventAuthorizedUserFromTransferringAmountDueToInsufficientFunds() {
        // проверяем создание админом пользователя
        String username = "sasha" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8);

        // проверяем создание авторизованного пользователя
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

        // проверяем создание аккаунта с которого будем переводить
        Integer senderAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем создание аккаунта на который будем переводить
        Integer receiverAccountId = given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("http://localhost:4111/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .getInt("id");

        // проверяем пополнение аккаунта с которого будем переводить (счёт)
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "id": %d,
                          "balance": 5000
                        }
                        """.formatted(senderAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверяем перевод денег между своими аккаунтами
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "senderAccountId": %d,
                          "receiverAccountId": %d,
                          "amount": 5100
                        }
                        """.formatted(senderAccountId, receiverAccountId))
                .when()
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}