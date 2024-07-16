package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;


/**
 * Класс {@code LoadingHelper} для ожидания загрузки элементов на странице.
 *
 * @author SergeyTrbv
 */
public class LoadingHelper {

    /**
     * Объект String с шаблоном XPath для элемента загрузки.
     */
    private static final String LOADING_ORANGE = "//div[@color='orange']";

    /**
     * Метод {@code loading} ожидает, пока элемент загрузки исчезнет со страницы.
     */
    public void loading() {
        SelenideElement loadingElement = Selenide.$x(LOADING_ORANGE);
//        loadingElement.shouldBe(Condition.visible, Duration.ofSeconds(5));
//        loadingElement.should(Condition.disappear, Duration.ofSeconds(10));

        // Ожидание, что элемент станет видимым в течение 5 секунд
//        loadingElement.shouldBe(Condition.visible, Duration.ofSeconds(5));

        // Ожидание, что элемент исчезнет в течение 10 секунд
        loadingElement.should(Condition.disappear, Duration.ofSeconds(10));

        // Ожидание, что элемент не будет видимым в течение 5 секунд
//        loadingElement.shouldNotBe(visible, Duration.ofSeconds(5));
    }
}

