package helpers;

import org.aeonbits.owner.Config;
/**
 * Интерфейс `TestsProperties` предназначен для загрузки конфигурационных свойств из нескольких
 * источников и предоставления доступа к ним.
 *
 * Этот интерфейс расширяет интерфейс `Config`, который является частью API MicroProfile Config,
 * что позволяет получать конфигурационные свойства.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"})
public interface TestsProperties extends Config {


    @Config.Key("page.load.timeout")
    long pageLoadTimeout();

    @Config.Key("browser")
    String browser();

    @Config.Key("hold.Browser.Open")
    boolean holdBrowserOpen();

    @Config.Key("start.Fullscreen")
    boolean startFullscreen();

    @Config.Key("page.Load.Strategy")
    String pageLoadStrategy();

    @Config.Key("arguments.for.browser")
    String argumentsForBrowser();

    @Config.Key("screenshots.power")
    boolean screenshotsPower();

    @Config.Key("screenshots.save.Page.Source")
    boolean screenshotsSavePageSource();
}
