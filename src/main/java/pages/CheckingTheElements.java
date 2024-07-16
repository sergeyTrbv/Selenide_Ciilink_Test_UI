package pages;

import com.codeborne.selenide.*;
import helpers.LoadingHelper;
import io.qameta.allure.Step;
import helpers.Assertions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code CheckingTheElements} предназначен для проверки наличия товаров на страницах каталога и перехода
 * к следующим страницам, если они доступны.
 * Он расширяет базовый класс {@code BasePage} и предоставляет методы для проверки соответствия товаров ожидаемым
 * брендам и навигации по страницам.
 *
 * @author SergeyTrbv
 */
public class CheckingTheElements extends BasePage {

    /**
     * Объект String с шаблоном XPath для определения кнопки "Следующая", для перехода на следующую страницу.
     */
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";

    /**
     * Объект String с шаблоном XPath определения элемента заголовка в каталоге.
     */
    private static final String TITLE_ELEMENTS_IN_CATALOG = "//div[@data-meta-name='ProductVerticalSnippet']" +
            "//a[@data-meta-name='Snippet__title']";

    /**
     * Текущая страница каталога.
     */
    private int currentPage = 1;

    /**
     * Объект типа {@code LoadingHelper} для ожидания загрузки элементов на странице.
     */
    private LoadingHelper loadingHelper;

    /**
     * Конструктор класса {@code CheckingTheElements}.
     * Инициализирует объект {@code LoadingHelper} для управления ожиданием загрузки элементов.
     */
    public CheckingTheElements() {
        this.loadingHelper = new LoadingHelper();
    }

    /**
     * Метод {@code checkProducts} проверяет результаты поиска на наличие товаров, соответствующих указанным
     * брендам, и переходит к следующим страницам, если они доступны.
     *
     * @param compareBrands список брендов для сравнения с товарами на странице
     */
    @Step("Проверяем результаты поиска на наличие товара: {product}")
    public void checkProducts(List<String> compareBrands) {
        boolean hasNextPage = true;
        while (hasNextPage) {
            checkPageConditions(compareBrands);
            hasNextPage = navigateToNextPage();
        }
    }

    /**
     * Метод {@code checkPageConditions} проверяет текущую страницу на наличие товаров, соответствующих
     * указанным брендам.
     *
     * @param compareBrands список брендов для сравнения с товарами на странице
     */
    @Step("Проверяем текущую страницу на наличие товара: {product}")
    private void checkPageConditions(List<String> compareBrands) {
        ElementsCollection productElementsTitle = $$x(TITLE_ELEMENTS_IN_CATALOG);
        for (SelenideElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");
            boolean containsBrand = compareBrands.stream().anyMatch(b -> Pattern.compile(Pattern.quote(b),
                    Pattern.CASE_INSENSITIVE).matcher(title).find());

            Assertions.assertTrue(containsBrand,
                    "На странице " + currentPage + " название производителя не соответствует " +
                            "ни одному из ожидаемых брендов: " + compareBrands);

        }
        System.out.println("На странице " + currentPage + " товар соответствует параметрам");
    }

    /**
     * Метод {@code navigateToNextPage} переходит на следующую страницу, если она доступна.
     *
     * @return true, если переход на следующую страницу был успешным, иначе false
     */
    @Step("Переходиv на следующую страницу, если она доступна")
    private boolean navigateToNextPage() {
        SelenideElement nextButton = $x(NEXT_BUTTON);

        if (nextButton.exists() && nextButton.isDisplayed() && !nextButton.has(Condition.
                attribute("disabled"))) {
            JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
            js.executeScript("arguments[0]." +
                    "scrollIntoView({behavior: 'smooth', block: 'center'});", nextButton);
            js.executeScript("arguments[0].click();", nextButton);
            currentPage++;
            loadingHelper.loading();
            return true;
        } else {
            return false;
        }
    }
}

