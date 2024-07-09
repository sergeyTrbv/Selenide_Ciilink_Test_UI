package tests.selenide;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CheckingTheElements;
import pages.CitilinkMainPage;
import pages.CitilinkSetParameters;
import pages.GoogleMainPage;

import static com.codeborne.selenide.Selenide.open;
import static helpers.Properties.testsProperties;

/**
 * Класс {@code Tests} содержит тесты, которые проверяют функциональность поисковика Citilink.
 * Он расширяет базовый класс {@link BaseTests}.
 *
 * @author SergeyTrbv
 */
public class Tests extends BaseTests {

    /**
     * Метод выполняет параметризованный тест для проверки поисковика Citilink.
     * Тест включает в себя последовательность действий от поиска в Google до проверки продуктов на сайте Citilink.
     *
     * @param textToFind           Текст для поиска в Google.
     * @param query                Текст ссылки, по которой нужно перейти на сайт Citilink.
     * @param catalogButton        Название кнопки каталога на сайте Citilink.
     * @param productNameInMenu    Название продукта в меню на сайте Citilink.
     * @param productNameInCatalog Название продукта в каталоге на сайте Citilink.
     * @param brandSection         Название раздела бренда на сайте Citilink.
     * @param brand                Название бренда для проверки.
     * @param product              Название продукта для проверки.
     */
    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("helpers.DataProvider#providerChecking")
    public void selenideTestCitilinkProductStepsAll(String textToFind, String query, String catalogButton,
                                                    String productNameInMenu, String productNameInCatalog,
                                                    String brandSection, String brand, String product) {

        open(testsProperties.googleUrl(), GoogleMainPage.class)
                .search(textToFind)
                .goLinkByName(query, CitilinkMainPage.class)
                .openCatalog(catalogButton)
                .indicateButtonInMenu(productNameInMenu)
                .openProductInCatalog(productNameInCatalog)
                .checkPage(productNameInCatalog, CitilinkSetParameters.class)
                .scrollToSection(brandSection)
                .clickCheckbox(brand, CheckingTheElements.class)
                .checkIPhoneProducts(product);
    }

}
