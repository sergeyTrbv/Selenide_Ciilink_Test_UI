package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс {@code GoogleMainPage} представляет главную страницу поисковой системы Google
 * и расширяет функциональность базового класса {@code BasePage}.
 *
 * @author Biryukov Aleksey
 */
public class GoogleMainPage extends BasePage {

    /**
     * Константа для имени поля ввода поискового запроса на главной странице Google.
     */
    private static final String INPUT_SEARCH = "q";


    /**
     * Метод {@code search} выполняет поиск по заданному запросу и переходит на страницу результатов поиска.
     *
     * @param query строка запроса, которую нужно ввести в поисковую строку.
     * @return объект типа {@code GoogleSearchResultPage}, представляющий страницу результатов поиска.
     */
    @Step("Ищу слово {query}")
    public GoogleSearchResultPage search(String query) {
        $(By.name(INPUT_SEARCH)).setValue(query).pressEnter();
        return page(GoogleSearchResultPage.class);
    }
}