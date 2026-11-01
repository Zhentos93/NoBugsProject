package automated_testing.practice_7_2_homework_ApiTestsMiddle;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected SoftAssertions softly;

    @BeforeEach
    public void setupTest() {
        this.softly = new SoftAssertions();
    }

    @AfterEach
    public void afterTest() {
        softly.assertAll();
    }
}
