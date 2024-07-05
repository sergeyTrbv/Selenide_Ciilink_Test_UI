package tests.selenide;

import allure.CustomAllureSelenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTests {

    @BeforeAll
    public static void setup(){
        SelenideLogger.addListener("AllureSelenide",new CustomAllureSelenide().
                screenshots(true).
                savePageSource(true));
    }


    @BeforeEach
    public void options() {
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true; // открывать/закрывать веб-страницу

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
        Configuration.browserCapabilities = capabilities;

/*
System.setProperty("webdriver.chrome.driver', System.getenv("CHROME_DRIVER"));
WebDriver driver;
driver = new ChromeDriver(options);
setWebDriver(driver);
*/
    }
}
