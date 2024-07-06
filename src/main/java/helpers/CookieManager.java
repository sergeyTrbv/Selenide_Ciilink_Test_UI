package helpers;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс {@code CookieManager} управляет взаимодействием с кнопкой согласия на использование cookies на веб-странице.
 *
 * @author SergeyTrbv
 */
public class CookieManager {

    /**
     * Объект String с шаблоном XPath для кнопки согласия на использование cookies.
     */
    private static final String COOCKIE = "//button[ @data-meta-disabled=\"false\"]/span[text()='Я согласен']";

    /**
     * Единственный экземпляр класса CookieManager.
     */
    private static CookieManager instance;

    /**
     * Флаг, указывающий, была ли уже нажата кнопка согласия на использование cookies.
     */
    private boolean cookieClicked = false;

    /**
     * Приватный конструктор для предотвращения создания экземпляров извне класса.
     */
    private CookieManager() {
    }

    /**
     * Метод {@code getInstance} возвращает единственный экземпляр класса CookieManager.
     * Если экземпляр еще не создан, он будет создан в этом методе.
     *
     * @return единственный экземпляр класса CookieManager.
     */
    public static synchronized CookieManager getInstance() {
        if (instance == null) {
            instance = new CookieManager();
        }
        return instance;
    }
    /**
     * Метод {@code clickCookieIfNeeded} нажимает кнопку согласия на использование cookies, если она еще не была нажата.
     * После нажатия кнопки флаг {@code cookieClicked} устанавливается в {@code true}.
     */
    public void clickCookieIfNeeded() {
        if (!cookieClicked) {
            $x(COOCKIE).click();
            cookieClicked = true;
        }
    }
}
