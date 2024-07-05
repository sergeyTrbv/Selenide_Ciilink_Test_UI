package pages;

import helpers.CookieManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CitilinkMainPage extends BasePage {
    private static final String HEAD_MENU = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']//span[contains(text(), '";
    private static final String TAIL_MENU = "')]";
    private static final String HEAD_PRODUCT_IN_MENU = "//div[@class='PopupScrollContainer']//span[@color='None' " +
            "and text()='";
    private static final String TALE_PRODUCT_IN_MENU = "']";
    private static final String HEAD_PRODUCT_IN_CATALOG = "//div[@class='rcs-inner-container']//span[text()='";
    private static final String TALE_PRODUCT_IN_CATALOG = "']";
    private static final String CHECK_PAGE_NAME = "//div[@data-meta-name='SubcategoryPageTitle']//h1[@color='Main']";


    @Step("Нажимаем на меню: {catalogButton}")
    public CitilinkMainPage openCatalog(String catalogButton) {
        CookieManager.getInstance().clickCookieIfNeeded();
        $x(HEAD_MENU + catalogButton + TAIL_MENU).click();
        return this;
    }

    @Step("Наводим курсор на: {productNameInMenu}")
    public CitilinkMainPage indicateButtonInMenu(String productNameInMenu) {
        $x(HEAD_PRODUCT_IN_MENU + productNameInMenu + TALE_PRODUCT_IN_MENU).hover();
        return this;
    }

    @Step("В сплывающем меню выбираем: {productNameInCatalog}")
    public CitilinkMainPage openProductInCatalog(String productNameInCatalog) {
        $x(HEAD_PRODUCT_IN_CATALOG + productNameInCatalog + TALE_PRODUCT_IN_CATALOG).click();
        return this;
    }

    @Step("Проверяем что перешли на страницу: {productNameInCatalog}")
    public <T extends BasePage> T checkPage(String productNameInCatalog, Class<T> typeNextPage) {

        $x(CHECK_PAGE_NAME).shouldBe(visible).shouldHave(text(productNameInCatalog));
        String actualTitle = $x(CHECK_PAGE_NAME).getText();
        System.out.println("Тестируемый товар: " + actualTitle);
        Assertions.assertEquals(productNameInCatalog, actualTitle, "Заголовок страницы не соответствует ожидаемому");
        return typeNextPage.cast(page(typeNextPage));
    }


}

