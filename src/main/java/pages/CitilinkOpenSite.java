package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс {@code CitilinkOpenSite} предназначен для открытия веб-сайта и проверки заголовка страницы.
 * Он предоставляет метод для перехода на указанный URL и ожидания, пока заголовок страницы не
 * станет равным ожидаемому значению.
 *
 * @author SergeyTrbv
 */
public class CitilinkOpenSite {

    /**
     * Метод {@code openSite} открывает указанный URL и ожидает, пока заголовок страницы не
     * станет равным указанному значению.
     *
     * @param startUrl     URL, который нужно открыть
     * @param title        ожидаемый заголовок страницы
     * @param typeNextPage класс следующей страницы
     * @param <T>          тип следующей страницы, расширяющий {@code BasePage}
     * @return объект следующей страницы
     */
    @Step("Переход на сайт: {startUrl}")
    public <T extends BasePage> T openSite(String startUrl, String title, Class<T> typeNextPage) {
        open(startUrl);
        Selenide.Wait().withTimeout(Duration.ofSeconds(15)).until(driver -> driver.getTitle().equals(title));
        ;
        return typeNextPage.cast(page(typeNextPage));
    }
}
