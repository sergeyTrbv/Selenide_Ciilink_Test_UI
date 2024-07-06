package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import helpers.Assertions;


import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code CheckingTheElements} расширяет BasePage и предназначен для проверки наличия товаров
 * на страницах каталога.
 *
 * @author SergeyTrbv
 */
public class CheckingTheElements extends BasePage {

    /**
     * Объект String с шаблоном XPath для определения карточки товаров на странице.
     */
    private static final String PRODUCT_CARD = "//div[contains(@class, 'e1l56t9a0')]";

    /**
     * Объект String с шаблоном XPath для определения кнопки "Следующая", для перехода на следующую страницу.
     */
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";

    /**
     * Объект String с шаблоном XPath определения элемента заголовка в каталоге
     */
    private static final String TITLE_ELEMENTS_IN_CATALOG = "//a[contains(@class, 'app-catalog-9gnskf') " +
            "and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]";


    /**
     * Метод {@code checkIPhoneProducts} для проверки наличия товаров, соответствующих заданному параметру,
     * на всех страницах каталога.
     *
     * @param product строка, представляющая товар, который нужно найти (например, "iPhone").
     */
    @Step("Проверяем результаты поиска на наличие товара: {product}")
    public void checkIPhoneProducts(String product) {
        int pageNumber = 1;
        boolean hasNextPage = true;
        while (hasNextPage) {
            checkPageForProduct(product, pageNumber);
            hasNextPage = navigateToNextPage();
            pageNumber++;
        }
    }

    /**
     * Метод {@code checkPageForProduct} для проверки наличия товаров, соответствующих заданному параметру,
     * на текущей странице.
     *
     * @param product    строка, представляющая товар, который нужно найти (например, "iPhone").
     * @param pageNumber номер текущей страницы.
     */
    @Step("Проверяем текущую страницу на наличие товара: {product}")
    private void checkPageForProduct(String product, int pageNumber) {

        ElementsCollection productElementsTitle = $$x(PRODUCT_CARD + TITLE_ELEMENTS_IN_CATALOG);
        if (productElementsTitle.isEmpty()) {
            System.out.println("На странице " + pageNumber + " товары \"" + product + "\" не найдены.");
            return;
        }

        productElementsTitle.shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (SelenideElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");

            boolean iphoneFound = false;
            if (title != null && title.contains(product)) {
                iphoneFound = true;
            }
            Assertions.assertTrue(iphoneFound, "На странице " + pageNumber +
                    " не найдено продуктов \"" + product + "\"");

        }
        System.out.println("На странице " + pageNumber + " все товары соответствуют параметрам :\"" + product + "\"");
    }

    /**
     * Метод {@code navigateToNextPage} переходит на следующую страницу, если она доступна.
     *
     * @return {@code true}, если переход на следующую страницу был успешным, иначе {@code false}.
     */
    @Step("Переходиv на следующую страницу, если она доступна")
    private boolean navigateToNextPage() {
        SelenideElement nextButton = $x(NEXT_BUTTON);
        if (nextButton.exists() && nextButton.isEnabled()) {
            nextButton.shouldBe(Condition.visible).scrollIntoView(true);
            Selenide.executeJavaScript("arguments[0].click();", nextButton);
            Selenide.sleep(1000); // Ждем загрузки следующей страницы
            return true;
        } else {
            return false;
        }
    }
}

