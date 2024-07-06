package pages;

import helpers.Assertions;
import helpers.CookieManager;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс {@code CitilinkMainPage} представляет главную страницу интернет-магазина Citilink и расширяет
 * базовый класс {@code BasePage}.
 * Он содержит методы для взаимодействия с элементами главной страницы, такими как меню, подменю и
 * проверка заголовка страницы.
 */
public class CitilinkMainPage extends BasePage {

    /**
     * Объект String с шаблоном  начала XPath для элемента "Меню" на главной странице.
     */
    private static final String HEAD_MENU = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']" +
            "//span[contains(text(), '";

    /**
     * Объект String с шаблоном конца XPath для элемента "Меню" на главной странице.
     */
    private static final String TAIL_MENU = "')]";

    /**
     * Объект String с шаблоном начала XPath для категории элемента в сплывшем меню.
     */
    private static final String HEAD_PRODUCT_IN_MENU = "//div[@class='PopupScrollContainer']//span[@color='None' " +
            "and text()='";

    /**
     * Объект String с шаблоном конца XPath для категории элемента в сплывшем меню.
     */
    private static final String TALE_PRODUCT_IN_MENU = "']";

    /**
     * Объект String с шаблоном начала XPath для категории элемента в открывшемся каталоге.
     */
    private static final String HEAD_PRODUCT_IN_CATALOG = "//div[@class='rcs-inner-container']//span[text()='";

    /**
     * Объект String с шаблоном конца XPath для категории элемента в открывшемся каталоге.
     */
    private static final String TALE_PRODUCT_IN_CATALOG = "']";

    /**
     * Объект String с шаблоном XPath для элемента, указающего на категорию товара на странице
     */
    private static final String CHECK_PAGE_NAME = "//div[@data-meta-name='SubcategoryPageTitle']//h1[@color='Main']";

    /**
     * Метод {@code openCatalog} для открытия каталога по названию кнопки.
     *
     * @param catalogButton название кнопки каталога
     * @return текущий объект {@code CitilinkMainPage}
     */
    @Step("Нажимаем на меню: {catalogButton}")
    public CitilinkMainPage openCatalog(String catalogButton) {
        CookieManager.getInstance().clickCookieIfNeeded();
        $x(HEAD_MENU + catalogButton + TAIL_MENU).click();
        return this;
    }

    /**
     * Метод {@code indicateButtonInMenu} для наведения курсора на элемент меню.
     *
     * @param productNameInMenu название продукта в меню
     * @return текущий объект {@code CitilinkMainPage}
     */
    @Step("Наводим курсор на: {productNameInMenu}")
    public CitilinkMainPage indicateButtonInMenu(String productNameInMenu) {
        $x(HEAD_PRODUCT_IN_MENU + productNameInMenu + TALE_PRODUCT_IN_MENU).hover();
        return this;
    }

    /**
     * Метод {@code openProductInCatalog} для выбора продукта в каталоге из сплывающего меню.
     *
     * @param productNameInCatalog название продукта в каталоге
     * @return текущий объект {@code CitilinkMainPage}
     */
    @Step("В сплывающем меню выбираем: {productNameInCatalog}")
    public CitilinkMainPage openProductInCatalog(String productNameInCatalog) {
        $x(HEAD_PRODUCT_IN_CATALOG + productNameInCatalog + TALE_PRODUCT_IN_CATALOG).click();
        return this;
    }

    /**
     * Метод {@code checkPage} для проверки заголовка страницы и перехода на следующую страницу.
     *
     * @param productNameInCatalog ожидаемый заголовок страницы
     * @param typeNextPage         класс следующей страницы
     * @param <T>                  тип следующей страницы, расширяющий {@code BasePage}
     * @return объект следующей страницы
     */
    @Step("Проверяем что перешли на страницу: {productNameInCatalog}")
    public <T extends BasePage> T checkPage(String productNameInCatalog, Class<T> typeNextPage) {

        $x(CHECK_PAGE_NAME).shouldBe(visible).shouldHave(text(productNameInCatalog));
        String actualTitle = $x(CHECK_PAGE_NAME).getText();
        System.out.println("Тестируемый товар: " + actualTitle);
        Assertions.assertEquals(productNameInCatalog, actualTitle, "Заголовок страницы " +
                "не соответствует ожидаемому");
        return typeNextPage.cast(page(typeNextPage));
    }
}

