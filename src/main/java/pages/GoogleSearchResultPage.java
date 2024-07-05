package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchResultPage extends BasePage {

    private static final String SEARCH_RESULTS = "//div[@id='rso']//div[@data-snc or @class='g']//a[@href]//h3";
    private static int page=1;

    @Step("Переходим по ссылке с текстом: {linkName}")
    public <T extends BasePage> T goLinkByName(String linkName, Class<T> typeNextPage){
        $$x(SEARCH_RESULTS)
                .find(text(linkName))
                .click();
        switchTo().window(page);
        page++;
        return typeNextPage.cast(page(typeNextPage));
    }
}