package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.Assertions;
import helpers.CustomCookieHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code SearchInCatalog} представляет главную страницу интернет-магазина Citilink и расширяет
 * базовый класс {@code BasePage}.
 * Он содержит методы для взаимодействия с элементами главной страницы, такими как меню, подменю и
 * проверка заголовка страницы.
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
     * Объект String с шаблоном начала XPath для категории элемента в открывшемся каталоге.
     */
    private static final String HEAD_PRODUCT_IN_CATALOG = "//div[@class='rcs-inner-container']//span[text()='";

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


    private CustomCookieHelper customCookieHelper;

    public SearchInCatalog() {
        this.customCookieHelper = new CustomCookieHelper();
    }
    /**
     * Метод {@code openCatalog} для открытия каталога по названию кнопки.
     *
     * @param catalogButton название кнопки каталога
     * @return текущий объект {@code CitilinkMainPage}
     */
    @Step("Нажимаем на меню: {catalogButton}")
    public SearchInCatalog openCatalog(String catalogButton) {

//        CustomCookieHelper.getInstance().clickCookieIfNeeded();
        customCookieHelper.clickCookieIfNeeded();

        String catalogButtonLocator = HEAD_MENU + catalogButton + TAIL_MENU;

//        WebDriverWait wait = new WebDriverWait(Selenide.webdriver().driver().getWebDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable($x(buttonLocator)));
//        $x(buttonLocator).click();

        $x(catalogButtonLocator).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return this;
    }

    /**
     * Метод {@code indicateButtonInMenu} для наведения курсора на элемент меню.
     *
     * @param productNameInMenu название продукта в меню
     * @return текущий объект {@code CitilinkMainPage}
     */
    @Step("Наводим курсор на: {productNameInMenu}")
    public SearchInCatalog indicateButtonInMenu(String productNameInMenu) {

        String productMenuLocator = HEAD_PRODUCT_IN_MENU + productNameInMenu + TALE_XPATH;

//        $x(HEAD_PRODUCT_IN_MENU + productNameInMenu + TALE_XPATH).hover();

        $x(productMenuLocator).shouldBe(Condition.visible, Duration.ofSeconds(10)).hover();
        return this;
    }

    /**
     * Метод {@code openProductInCatalog} для выбора продукта в каталоге из сплывающего меню.
     *
     * @param productNameInCatalog название продукта в каталоге
     * @return текущий объект {@code CitilinkMainPage}
     */
    @Step("В сплывающем меню выбираем: {productNameInCatalog}")
    public SearchInCatalog openProductInCatalog(String productNameInCatalog) {

        String productNameLocator = HEAD_PRODUCT_IN_MENU + productNameInCatalog + TALE_XPATH;

//        $x(HEAD_PRODUCT_IN_CATALOG + productNameInCatalog + TALE_XPATH).click();

        $x(productNameLocator).shouldBe(visible, Duration.ofSeconds(10)).click();

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

        $x(CHECK_PAGE_NAME).shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(productNameInCatalog));

        String actualTitle = $x(CHECK_PAGE_NAME).getText();
        System.out.println("Тестируемый товар: " + actualTitle);

        Assertions.assertEquals(productNameInCatalog, actualTitle, "Заголовок страницы " +
                "не соответствует ожидаемому");

        return typeNextPage.cast(page(typeNextPage));
    }
}

