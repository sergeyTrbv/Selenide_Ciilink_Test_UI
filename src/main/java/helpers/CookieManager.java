package helpers;

import static com.codeborne.selenide.Selenide.$x;

public class CookieManager {
    private static final String COOCKIE = "//button[ @data-meta-disabled=\"false\"]/span[text()='Я согласен']";
    private static CookieManager instance;
    private boolean cookieClicked = false;

    private CookieManager() {}

    public static synchronized CookieManager getInstance() {
        if (instance == null) {
            instance = new CookieManager();
        }
        return instance;
    }

    public void clickCookieIfNeeded() {
        if (!cookieClicked) {
            $x(COOCKIE).click();
            cookieClicked = true;
        }
    }
}
