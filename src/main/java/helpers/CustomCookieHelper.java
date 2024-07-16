package helpers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс {@code CookieManager} управляет взаимодействием с кнопкой согласия на использование cookies на веб-странице.
 *
 * @author SergeyTrbv
 */
public class CustomCookieHelper {

    /**
     * Объект String с шаблоном XPath для кнопки согласия на использование cookies.
     */
    private static final String COOCKIE = "//button[ @data-meta-disabled=\"false\"]/span[text()='Я согласен']";

//    /**
//     * Единственный экземпляр класса CookieManager.
//     */
//    private static CustomCookieHelper instance;
//
//    /**
//     * Флаг, указывающий, была ли уже нажата кнопка согласия на использование cookies.
//     */
//    private boolean cookieClicked = false;
//
//    /**
//     * Приватный конструктор для предотвращения создания экземпляров извне класса.
//     */
//    private CustomCookieHelper() {
//    }
//
//    /**
//     * Метод {@code getInstance} возвращает единственный экземпляр класса CookieManager.
//     * Если экземпляр еще не создан, он будет создан в этом методе.
//     *
//     * @return единственный экземпляр класса CookieManager.
//     */
//    public static synchronized CustomCookieHelper getInstance() {
//        if (instance == null) {
//            instance = new CustomCookieHelper();
//        }
//        return instance;
//    }
//    /**
//     * Метод {@code clickCookieIfNeeded} нажимает кнопку согласия на использование cookies, если она еще не была нажата.
//     * После нажатия кнопки флаг {@code cookieClicked} устанавливается в {@code true}.
//     */
//    public void clickCookieIfNeeded() {
//        if (!cookieClicked) {
//
////            $x(COOCKIE).click();
//            $x(COOCKIE).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
//
//            cookieClicked = true;
//        }
//    }


    @Step("Соглашаемся с cookie11")
    public void clickCookieIfNeeded() {
        $x(COOCKIE).shouldBe(Condition.visible,Duration.ofSeconds(10)).click();

//        WebElement cookieButon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COOKIE_XPATH)));
//        cookieButon.click();
    }

}
