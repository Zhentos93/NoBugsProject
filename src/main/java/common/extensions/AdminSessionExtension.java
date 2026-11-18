package common.extensions;

import automated_testing_api_senior.models.CreateUserRequest;
import automated_testing_ui_senior.pages.BasePage;
import common.annotations.AdminSession;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AdminSessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        // Шаг 1: проверка, есть ли у теста аннотация AdminSession
        AdminSession annotation = extensionContext.getRequiredTestMethod().getAnnotation(AdminSession.class);
        if (annotation != null) { // Шаг 2: если есть, добавляем в local storage токен админа
            BasePage.authAsUser(CreateUserRequest.getAdmin());
        }
    }
}
