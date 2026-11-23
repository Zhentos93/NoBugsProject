package automated_testing.practice_14_2_homework_UiTestsMiddle;

import automated_testing.practice_8_2_homework_ApiTestsSenior.BaseSeniorTest;
import automated_testing_api_senior.configs.Config;
import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_api_senior.specs.RequestSpecs;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BaseUiMiddleTest extends BaseSeniorTest {
    private static final String REMOTE_PROPERTY = "uiRemote";
    private static final String BASE_URL_PROPERTY = "uiBaseUrl";
    private static final String BROWSER_PROPERTY = "browser";
    private static final String BROWSER_VERSION_PROPERTY = "browserVersion";
    private static final String BROWSER_SIZE_PROPERTY = "browserSize";
    private static final String SELENOID_OPTIONS = "selenoid:options";
    private static final String ENABLE_VNC = "enableVNC";
    private static final String ENABLE_LOG = "enableLog";
    private static final String ROOT_PATH = "/";
    private static final String AUTH_TOKEN_KEY = "authToken";
    private static final String SET_LOCAL_STORAGE_ITEM = "localStorage.setItem(arguments[0], arguments[1]);";

    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = Config.getProperty(REMOTE_PROPERTY);
        Configuration.baseUrl = Config.getProperty(BASE_URL_PROPERTY);
        Configuration.browser = Config.getProperty(BROWSER_PROPERTY);
        Configuration.browserVersion = Config.getProperty(BROWSER_VERSION_PROPERTY);
        Configuration.browserSize = Config.getProperty(BROWSER_SIZE_PROPERTY);

        Configuration.browserCapabilities.setCapability(
                SELENOID_OPTIONS, Map.of(ENABLE_VNC, true, ENABLE_LOG, true)
        );
    }

    public void authAsUser(String username, String password) {
        Selenide.open(ROOT_PATH);
        String userAuthHeader = RequestSpecs.getUserAuthHeader(username, password);
        executeJavaScript(SET_LOCAL_STORAGE_ITEM, AUTH_TOKEN_KEY, userAuthHeader);
    }

    public void authAsUser(CreateUserRequest createUserRequest) {
        authAsUser(createUserRequest.getUsername(), createUserRequest.getPassword());
    }
}