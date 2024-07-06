package pages;


import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code CitilinkSetParameters} расширяет функциональность базового класса {@code BasePage}
 * и предоставляет методы для настройки параметров на странице сайта Citilink.
 *
 * @author SergeyTrbv
 */
public class CitilinkSetParameters extends BasePage {

    /**
     * Константа для формирования XPath выражения для элемента span, содержащего текст фильтра.
     */
    private static final String SPAN_TEXT = "//span[@data-meta-name=\"FilterDropdown__title\" and text()=\"";

    /**
     * Константа для формирования XPath для закрытия элемента span.
     */
    private static final String COMMON_TAIL = "\"]";

    /**
     * Константа для формирования начальной части XPath выражения для чекбокса продукта.
     */
    private static final String HEAD_CHECKBOX_PRODUCT = "//input[@id='";

    /**
     * Константа для формирования конечной части XPath выражения для чекбокса продукта.
     */
    private static final String TAIL_CHECKBOX_PRODUCT = "']";

    /**
     * Метод {@code scrollToSection} прокручивает страницу до указанного раздела фильтра.
     *
     * @param brandSection название раздела фильтра, до которого нужно прокрутить страницу.
     * @return текущий объект {@code CitilinkSetParameters} для возможности цепочечного вызова методов.
     */
    @Step("Пролистать до раздела {brandSection}")
    public CitilinkSetParameters scrollToSection(String brandSection) {
        SelenideElement element = $x(SPAN_TEXT + brandSection + COMMON_TAIL);
        element.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView({behavior: 'smooth', " +
                "block: 'center', inline: 'center'});", element);
        return this;
    }

    /**
     * Метод {@code clickCheckbox} выбирает чекбокс в указанном разделе и переходит на следующую страницу.
     *
     * @param brand        название бренда или параметра, который нужно выбрать.
     * @param typeNextPage класс типа следующей страницы, на которую будет совершен переход.
     * @param <T>          тип класса, расширяющего {@code BasePage}.
     * @return объект типа {@code T}, представляющий следующую страницу.
     */
    @Step("В разделе {brandSection} выбираем: {brand}")
    public <T extends BasePage> T clickCheckbox(String brand, Class<T> typeNextPage) {
        $x(HEAD_CHECKBOX_PRODUCT + brand.toLowerCase() + TAIL_CHECKBOX_PRODUCT).click();
        return typeNextPage.cast(page(typeNextPage));
    }

}
