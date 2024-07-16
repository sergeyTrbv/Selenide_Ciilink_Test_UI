package helpers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс {@code CustomCookieHelper} управляет взаимодействием с кнопкой согласия на использование cookies
 * на веб-странице.
 * Он предоставляет метод для клика по кнопке согласия, если она присутствует и видима на странице.
 *
 * @author SergeyTrbv
 */
public class CustomCookieHelper {

    /**
     * Объект String с шаблоном XPath для кнопки согласия на использование cookies.
     */
    private static final String COOCKIE = "//button[ @data-meta-disabled=\"false\"]/span[text()='Я согласен']";

    /**
     * Метод {@code clickCookieIfNeeded} проверяет наличие и видимость кнопки согласия на использование cookies
     * и кликает по ней, если она доступна.
     */
    @Step("Соглашаемся с cookie11")
    public void clickCookieIfNeeded() {
        $x(COOCKIE).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }
}
