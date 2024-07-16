package helpers;

import org.aeonbits.owner.Config;

/**
 * Интерфейс {@code TestsProperties} предоставляет конфигурационные настройки для тестов,
 * такие как таймаут загрузки страницы, настройки браузера и параметры сохранения скриншотов.
 * Настройки загружаются из системных свойств, переменных окружения и файла конфигурации.
 *
 * @author SergeyTrbv
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"})
public interface TestsProperties extends Config {

    /**
     * Возвращает таймаут загрузки страницы в миллисекундах.
     *
     * @return таймаут загрузки страницы
     */
    @Config.Key("page.load.timeout")
    long pageLoadTimeout();

    /**
     * Возвращает имя браузера, который будет использоваться для тестирования.
     *
     * @return имя браузера
     */
    @Config.Key("browser")
    String browser();

    /**
     * Определяет, должен ли браузер оставаться открытым после завершения теста.
     *
     * @return true, если браузер должен оставаться открытым, иначе false
     */
    @Config.Key("hold.Browser.Open")
    boolean holdBrowserOpen();

    /**
     * Определяет, должен ли браузер запускаться в полноэкранном режиме.
     *
     * @return true, если браузер должен запускаться в полноэкранном режиме, иначе false
     */
    @Config.Key("start.Fullscreen")
    boolean startFullscreen();

    /**
     * Возвращает стратегию загрузки страницы, которая будет использоваться браузером.
     *
     * @return стратегия загрузки страницы
     */
    @Config.Key("page.Load.Strategy")
    String pageLoadStrategy();

    /**
     * Возвращает аргументы, которые будут переданы браузеру при запуске.
     *
     * @return аргументы для браузера
     */
    @Config.Key("arguments.for.browser")
    String argumentsForBrowser();

    /**
     * Определяет, должны ли сохраняться скриншоты во время выполнения тестов.
     *
     * @return true, если скриншоты должны сохраняться, иначе false
     */
    @Config.Key("screenshots.power")
    boolean screenshotsPower();

    /**
     * Определяет, должен ли сохраняться исходный код страницы при сохранении скриншотов.
     *
     * @return true, если исходный код страницы должен сохраняться, иначе false
     */
    @Config.Key("screenshots.save.Page.Source")
    boolean screenshotsSavePageSource();
}
