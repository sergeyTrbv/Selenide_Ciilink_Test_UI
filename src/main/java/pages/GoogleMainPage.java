package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleMainPage extends BasePage{

    private static final String INPUT_SEARCH = "q";

    @Step("Ищу слово {query}")
    public GoogleSearchResultPage search(String query){
        $(By.name(INPUT_SEARCH)).setValue(query).pressEnter();
        return page(GoogleSearchResultPage.class);
    }
}