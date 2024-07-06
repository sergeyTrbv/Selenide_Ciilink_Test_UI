package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code GoogleSearchResultPage} представляет страницу результатов поиска Google и расширяет
 * базовый класс {@link BasePage}.
 * Он содержит метод для перехода по ссылке с заданным текстом.
 *
 * @author Biryukov Aleksey
 */
public class GoogleSearchResultPage extends BasePage {

    /**
     * Константа, представляющая XPath для элементов результатов поиска.
     */
    private static final String SEARCH_RESULTS = "//div[@id='rso']//div[@data-snc or @class='g']//a[@href]//h3";
    /**
     * Переменная, хранящая текущую страницу для переключения окон.
     */
    private static int page = 1;

    /**
     * Метод {@code goLinkByName} для перехода по ссылке с заданным текстом.
     *
     * @param linkName     Текст ссылки, по которой нужно перейти.
     * @param typeNextPage Класс типа следующей страницы, к которой нужно перейти.
     * @param <T>          Тип следующей страницы, который должен расширять {@link BasePage}.
     * @return Экземпляр следующей страницы.
     */
    @Step("Переходим по ссылке с текстом: {linkName}")
    public <T extends BasePage> T goLinkByName(String linkName, Class<T> typeNextPage) {
        $$x(SEARCH_RESULTS)
                .find(text(linkName))
                .click();
        switchTo().window(page);
        page++;
        return typeNextPage.cast(page(typeNextPage));
    }
}