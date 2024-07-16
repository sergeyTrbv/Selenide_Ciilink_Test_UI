package pages;

import com.codeborne.selenide.Condition;
import helpers.Assertions;
import helpers.CustomCookieHelper;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code SearchInCatalog} предназначен для поиска и навигации по каталогу товаров на веб-сайте.
 * Он расширяет базовый класс {@code BasePage} и предоставляет методы для взаимодействия с элементами интерфейса,
 * такими как меню, категории товаров и проверка заголовков страниц.
 *
 * @author SergeyTrbv
 */
public class SearchInCatalog extends BasePage {

    /**
     * Объект String с шаблоном начала XPath для элемента "Меню" на главной странице.
     */
    private static final String HEAD_MENU = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']" +
            "//span[contains(text(), '";

    /**
     * Объект String с шаблоном начала XPath для категории элемента в сплывшем меню.
     */
    private static final String HEAD_PRODUCT_IN_MENU = "//div[@class='PopupScrollContainer']//span[@color='None' " +
            "and text()='";

    /**
     * Объект String с шаблоном XPath для элемента, указающего на категорию товара на странице
     */
    private static final String CHECK_PAGE_NAME = "//div[@data-meta-name='SubcategoryPageTitle']//h1[@color='Main']";

    /**
     * Объект String с шаблоном конца XPath для элемента "Меню" на главной странице.
     */
    private static final String TAIL_MENU = "')]";
    /**
     * Объект String с шаблоном конца XPath для категории элемента в открывшемся каталоге.
     */
    private static final String TALE_XPATH = "']";

    /**
     * Объект типа CustomCookieHelper для взаимодействия с кнопкой куки.
     */
    private CustomCookieHelper customCookieHelper;

    /**
     * Конструктор класса {@code SearchInCatalog}.
     * Инициализирует объект {@code CustomCookieHelper} для управления куки.
     */
    public SearchInCatalog() {
        this.customCookieHelper = new CustomCookieHelper();
    }


    /**
     * Метод {@code openCatalog} нажимает на кнопку каталога с указанным названием.
     *
     * @param catalogButton название кнопки каталога
     * @return текущий объект {@code SearchInCatalog}
     */
    @Step("Нажимаем на меню: {catalogButton}")
    public SearchInCatalog openCatalog(String catalogButton) {
        customCookieHelper.clickCookieIfNeeded();
        String catalogButtonLocator = HEAD_MENU + catalogButton + TAIL_MENU;
        $x(catalogButtonLocator).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return this;
    }

    /**
     * Метод {@code indicateButtonInMenu} наводит курсор на указанный элемент меню.
     *
     * @param productNameInMenu название продукта в меню
     * @return текущий объект {@code SearchInCatalog}
     */
    @Step("Наводим курсор на: {productNameInMenu}")
    public SearchInCatalog indicateButtonInMenu(String productNameInMenu) {
        String productMenuLocator = HEAD_PRODUCT_IN_MENU + productNameInMenu + TALE_XPATH;
        $x(productMenuLocator).shouldBe(Condition.visible, Duration.ofSeconds(10)).hover();
        return this;
    }

    /**
     * Метод {@code openProductInCatalog} выбирает продукт в каталоге из сплывающего меню.
     *
     * @param productNameInCatalog название продукта в каталоге
     * @return текущий объект {@code SearchInCatalog}
     */
    @Step("В сплывающем меню выбираем: {productNameInCatalog}")
    public SearchInCatalog openProductInCatalog(String productNameInCatalog) {
        String productNameLocator = HEAD_PRODUCT_IN_MENU + productNameInCatalog + TALE_XPATH;
        $x(productNameLocator).shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }

    /**
     * Метод {@code checkPage} проверяет заголовок страницы и переходит на следующую страницу.
     *
     * @param productNameInCatalog ожидаемый заголовок страницы
     * @param typeNextPage         класс следующей страницы
     * @param <T>                  тип следующей страницы, расширяющий {@code BasePage}
     * @return объект следующей страницы
     */
    @Step("Проверяем что перешли на страницу: {productNameInCatalog}")
    public <T extends BasePage> T checkPage(String productNameInCatalog, Class<T> typeNextPage) {
        $x(CHECK_PAGE_NAME).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(productNameInCatalog));
        String actualTitle = $x(CHECK_PAGE_NAME).getText();
        System.out.println("Тестируемый товар: " + actualTitle);

        Assertions.assertEquals(productNameInCatalog, actualTitle, "Заголовок страницы " +
                "не соответствует ожидаемому");

        return typeNextPage.cast(page(typeNextPage));
    }
}

