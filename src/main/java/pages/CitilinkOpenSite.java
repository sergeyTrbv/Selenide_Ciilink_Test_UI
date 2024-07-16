package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CitilinkOpenSite {

    @Step("Переход на сайт: {startUrl}")
    public <T extends BasePage> T openSite(String startUrl, String title, Class<T> typeNextPage) {

        open(startUrl);

//        $("title").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(text(title));
//        $("title").shouldHave(text(title), Duration.ofSeconds(15));
        Selenide.Wait().withTimeout(Duration.ofSeconds(15)).until(driver -> driver.getTitle().equals(title));
//        $("title").shouldHave(attribute("text", title));
//        $("title").shouldHave(text(title));


        return typeNextPage.cast(page(typeNextPage));
    }
}
//$x(COOCKIE).shouldBe(Condition.visible,Duration.ofSeconds(10)).click();