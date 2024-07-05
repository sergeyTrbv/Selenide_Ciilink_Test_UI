package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class CitilinkMainPage extends BasePage{


        private static final String A_TEXT = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']//span[contains(text(), '";
        private static final String COMMON_TAIL = "')]";




        @Step("Перейти в раздел{text}")
        public <T extends BasePage> T openCatalog(String text, Class<T> typeNextPage) {
            $x(A_TEXT + text + COMMON_TAIL).click();
            return typeNextPage.cast(page(typeNextPage));
        }
    }

