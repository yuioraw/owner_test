package yuioraw;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import yuioraw.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());  // листенер аллюра

        String browser = System.getProperty("browser");
        String version = System.getProperty("version");
        String login = System.getProperty("login","user1");
        String password = System.getProperty("password","1234");
        String url = System.getProperty("url");
        String remoteUrl = "https://" + login + ":" + password + "@" + url;

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.remote = remoteUrl;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true); //видели что происходит
        capabilities.setCapability("enableVideo", true); // видео записывает
        Configuration.browserCapabilities = capabilities;
        // в блоке DesiredCapabilities конфигурируем удаленный запуск
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}