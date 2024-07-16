package tests.selenide;

import allure.CustomAllureSelenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Properties;
import helpers.TestsProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTests {

    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide().
                screenshots(Properties.testsProperties.screenshotsPower()).
                savePageSource(Properties.testsProperties.screenshotsSavePageSource()));
    }

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
/*
System.setProperty("webdriver.chrome.driver', System.getenv("CHROME_DRIVER"));
WebDriver driver;
driver = new ChromeDriver(options);
setWebDriver(driver);
*/
    }
}
