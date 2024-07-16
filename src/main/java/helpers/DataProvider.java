package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

/**
 * Класс {@code DataProvider} предоставляет данные для тестовых методов.
 * Он содержит методы для генерации потоков аргументов, которые используются в тестах для передачи различных
 * наборов данных.
 *
 * @author SergeyTrbv
 */
public class DataProvider {

    /**
     * Метод {@code providerChecking} возвращает поток аргументов с информацией о тестируемом продукте.
     * Каждый набор аргументов включает URL сайта, заголовок страницы, название раздела каталога,
     * название категории товаров, список брендов и список моделей товаров.
     *
     * @return поток аргументов с информацией о тестируемом продукте
     */
    public static Stream<Arguments> providerChecking() {
        return Stream.of(
                Arguments.of(
                        "https://www.citilink.ru/",
                        "Ситилинк - интернет-магазин техники, электроники, товаров для дома и ремонта",
                        "Каталог товаров",
                        "Смартфоны и планшеты",
                        "Смартфоны",
                        List.of("APPLE"),
                        List.of("iPhone")
                )
                ,
                Arguments.of(
                        "https://www.citilink.ru/",
                        "Ситилинк - интернет-магазин техники, электроники, товаров для дома и ремонта",
                        "Каталог товаров",
                        "Красота и здоровье",
                        "Массажеры",
                        List.of("BEURER", "ZENET"),
                        List.of("BEURER", "ZENET")
                )
        );
    }
}