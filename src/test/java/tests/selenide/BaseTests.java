package tests.selenide;

import allure.CustomAllureSelenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Базовый класс для всех тестов, содержащий общие настройки и методы, которые будут выполняться перед и после тестов.
 *
 * @author SergeyTrbv
 */
public class BaseTests {

    /**
     * Метод, выполняющийся один раз перед всеми тестами в классе. Настраивает Selenide для использования
     * Allure с дополнительными параметрами.
     */
    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide().
                screenshots(Properties.testsProperties.screenshotsPower()).
                savePageSource(Properties.testsProperties.screenshotsSavePageSource()));
    }

    /**
     * Метод, выполняющийся перед каждым тестом. Настраивает параметры Selenide и браузера на основе свойств,
     * определенных в конфигурации.
     */
    @BeforeEach
    public void options() {
        Configuration.timeout = Properties.testsProperties.pageLoadTimeout();
        Configuration.browser = Properties.testsProperties.browser();
        Configuration.holdBrowserOpen = Properties.testsProperties.holdBrowserOpen();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Properties.testsProperties.argumentsForBrowser());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, Properties.testsProperties.pageLoadStrategy());
        Configuration.browserCapabilities = capabilities;
    }

    /**
     * Метод, выполняющийся после каждого теста. Закрывает текущее окно браузера.
     */
    @AfterEach
    public void close() {
        com.codeborne.selenide.Selenide.closeWindow();
    }
}
