package tests.selenide;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CheckingTheElements;
import pages.SearchInCatalog;
import pages.CitilinkOpenSite;
import pages.CitilinkSetParameters;

import java.util.List;

/**
 * Класс {@code Tests} расширяет {@code BaseTests} и содержит тесты для проверки функциональности сайта Citilink.
 *
 * @author SergeyTrbv
 */
public class Tests extends BaseTests {

    /**
     * Объект для взаимодействия с сайтом Citilink.
     */
    private CitilinkOpenSite citilinkOpenSite = new CitilinkOpenSite();

    /**
     * Метод для проверки поисковика Citilink с использованием параметризованного теста.
     *
     * @param startUrl             URL страницы, с которой начинается тест.
     * @param title                Ожидаемый заголовок страницы.
     * @param catalogButton        Кнопка каталога, которую нужно нажать.
     * @param productNameInMenu    Название продукта в меню.
     * @param productNameInCatalog Название продукта в каталоге.
     * @param brands               Список брендов для проверки.
     * @param compareBrands        Список брендов для сравнения.
     */
    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("helpers.DataProvider#providerChecking")
    public void selenideTestCitilinkProductStepsAll(String startUrl, String title,
                                                    String catalogButton,
                                                    String productNameInMenu, String productNameInCatalog,
                                                    List<String> brands, List<String> compareBrands) {

        citilinkOpenSite.openSite(startUrl, title, SearchInCatalog.class)
                .openCatalog(catalogButton)
                .indicateButtonInMenu(productNameInMenu)
                .openProductInCatalog(productNameInCatalog)
                .checkPage(productNameInCatalog, CitilinkSetParameters.class)
                .clickCheckbox(brands, CheckingTheElements.class)
                .checkProducts(compareBrands);
    }
}
