package yuioraw;

import yuioraw.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.CredentialsConfig;
import config.ProjectConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    @Step("Конфигурируем браузер и удаленный запуск")
    static void beforeAllMethod() {

        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
        ProjectConfig browserConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());

        String login = config.login();
        String password = config.password();
        String url = browserConfig.remoteDriverUrl();
        String browser = browserConfig.browser();
        String browserVersion = browserConfig.browserVersion();
        String browserSize = browserConfig.browserSize();
        String remoteUrl = "https://" + login + ":" + password + "@" + url;

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion; // available versions: chrome - 90, 91 opera - 76, 77 firefox - 88, 89
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.remote = remoteUrl;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}