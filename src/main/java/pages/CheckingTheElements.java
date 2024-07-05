package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


import static com.codeborne.selenide.Selenide.*;

public class CheckingTheElements extends BasePage {

    private static final String PRODUCT_CARD = "//div[contains(@class, 'e1l56t9a0')]";
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";
    private static final String TITLE_ELEMENTS_IN_CATALOG = "//a[contains(@class, 'app-catalog-9gnskf') " +
            "and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]";


    @Step("Проверяем результаты поиска на наличие товара: {product}")
    public void checkIPhoneProducts(String product) {
        int pageNumber = 1;
        boolean hasNextPage = true;
        while (hasNextPage) {
            checkPageForIPhone(product,pageNumber);
            hasNextPage = navigateToNextPage();
            pageNumber++;
        }
    }

    @Step("Проверяем текущую страницу на наличие товара: {product}")
    private void checkPageForIPhone(String product, int pageNumber) {

        ElementsCollection productElementsTitle = $$x(PRODUCT_CARD + TITLE_ELEMENTS_IN_CATALOG);
        if (productElementsTitle.isEmpty()) {
            System.out.println("На странице " + pageNumber + " товары \"" + product + "\" не найдены.");
            return;
        }

        productElementsTitle.shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (SelenideElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");


            boolean iphoneFound = false;
            if (title != null && title.contains(product)) {
                iphoneFound = true;
            }
            Assertions.assertTrue(iphoneFound, "На странице " + pageNumber + " не найдено продуктов \"" + product + "\"");

        }
        System.out.println("На странице " + pageNumber + " все товары соответствуют параметрам :\"" + product + "\"");
    }


    @Step("Переходиv на следующую страницу, если она доступна")
    private boolean navigateToNextPage() {
        SelenideElement nextButton = $x(NEXT_BUTTON);
        if (nextButton.exists() && nextButton.isEnabled()) {
            nextButton.shouldBe(Condition.visible).scrollIntoView(true);
            Selenide.executeJavaScript("arguments[0].click();", nextButton);
            Selenide.sleep(1000); // Ждем загрузки следующей страницы
            return true;
        } else {
            return false;
        }
    }

}

