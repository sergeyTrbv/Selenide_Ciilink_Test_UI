package pages;

import helpers.LoadingHelper;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code CitilinkSetParameters} предназначен для установки параметров и взаимодействия с чекбоксами на
 * странице каталога товаров.
 * Он расширяет базовый класс {@code BasePage} и предоставляет методы для выбора брендов и ожидания загрузки
 * элементов.
 *
 * @author SergeyTrbv
 */
public class CitilinkSetParameters extends BasePage {

    /**
     * Константа для формирования начальной части XPath выражения для чекбокса продукта.
     */
    private static final String HEAD_CHECKBOX_PRODUCT = "//input[@id='";

    /**
     * Константа для формирования XPath для закрытия элемента.
     */
    private static final String TAIL_XPATH = "']";

    /**
     * Объект типа {@code LoadingHelper} для ожидания загрузки элементов на странице.
     */
    private LoadingHelper loadingHelper;

    /**
     * Конструктор класса {@code CitilinkSetParameters}.
     * Инициализирует объект {@code LoadingHelper} для управления ожиданием загрузки элементов.
     */
    public CitilinkSetParameters() {
        this.loadingHelper = new LoadingHelper();
    }

    /**
     * Метод {@code clickCheckbox} выбирает чекбоксы для указанных брендов и ожидает загрузку страницы после каждого выбора.
     *
     * @param brands       список брендов, для которых нужно выбрать чекбоксы
     * @param typeNextPage класс следующей страницы
     * @param <T>          тип следующей страницы, расширяющий {@code BasePage}
     * @return объект следующей страницы
     */
    @Step("В разделе {brandSection} выбираем: {brand}")
    public <T extends BasePage> T clickCheckbox(List<String> brands, Class<T> typeNextPage) {
        for (String brand : brands) {
            String checkBoxLocator = HEAD_CHECKBOX_PRODUCT + brand.toLowerCase() + TAIL_XPATH;
            $x(checkBoxLocator).click();
            loadingHelper.loading();
        }
        return typeNextPage.cast(page(typeNextPage));
    }
}
