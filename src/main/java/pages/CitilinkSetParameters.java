package pages;


import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CitilinkSetParameters extends BasePage {
    private static final String SPAN_TEXT = "//span[@data-meta-name=\"FilterDropdown__title\" and text()=\"";
    private static final String COMMON_TAIL = "\"]";
    private static final String HEAD_CHECKBOX_PRODUCT = "//input[@id='";
    private static final String TAIL_CHECKBOX_PRODUCT = "']";


    @Step("Пролистать до раздела {brandSection}")
    public CitilinkSetParameters scrollToSection(String brandSection) {
        SelenideElement element = $x(SPAN_TEXT + brandSection + COMMON_TAIL);
        element.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
        return this;
    }

    @Step("В разделе {brandSection} выбираем: {brand}")
    public <T extends BasePage> T clickCheckbox(String brand, Class<T> typeNextPage) {
        $x(HEAD_CHECKBOX_PRODUCT + brand.toLowerCase() + TAIL_CHECKBOX_PRODUCT).click();
        return typeNextPage.cast(page(typeNextPage));
    }

}
