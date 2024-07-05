package tests.selenide;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CitilinkMainPage;
import pages.CitilinkSetParameters;
import pages.GoogleMainPage;

import static com.codeborne.selenide.Selenide.open;

public class Tests extends BaseTests{



    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingLaptop")
    public void selenidTestCitilinkProductStepsAll(String textToFind, String query,String catalogButton) {

        open("https://www.google.ru/", GoogleMainPage.class)
                .search(textToFind)
                .goLinkByName(query, CitilinkMainPage.class)
                .openCatalog(catalogButton, CitilinkSetParameters.class);




    }

}
