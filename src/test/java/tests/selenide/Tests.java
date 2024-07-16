package tests.selenide;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CheckingTheElements;
import pages.SearchInCatalog;
import pages.CitilinkOpenSite;
import pages.CitilinkSetParameters;

/**
 * Класс {@code Tests} содержит тесты, которые проверяют функциональность поисковика Citilink.
 * Он расширяет базовый класс {@link BaseTests}.
 *
 * @author SergeyTrbv
 */
public class Tests extends BaseTests {
    private CitilinkOpenSite citilinkOpenSite = new CitilinkOpenSite();

    /**
     * Метод выполняет параметризованный тест для проверки поисковика Citilink.
     * Тест включает в себя последовательность действий от поиска в Google до проверки продуктов на сайте Citilink.
     *
     * @param startUrl Сайт Ситилинка.
     */
    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("helpers.DataProvider#providerChecking")
    public void selenideTestCitilinkProductStepsAll(String startUrl, String title,
                                                    String catalogButton,
                                                    String productNameInMenu, String productNameInCatalog,
                                                    String brandSection, String brand, String product) {

        citilinkOpenSite.openSite(startUrl, title, SearchInCatalog.class)
                .openCatalog(catalogButton)

                .indicateButtonInMenu(productNameInMenu)
                .openProductInCatalog(productNameInCatalog)
                .checkPage(productNameInCatalog, CitilinkSetParameters.class)
                .scrollToSection(brandSection)
                .clickCheckbox(brand, CheckingTheElements.class)
                .checkIPhoneProducts(product);
    }

}
