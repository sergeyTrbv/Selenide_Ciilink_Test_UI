## UI  SELENIDE

### [Задание](https://docs.google.com/document/d/18afX_hNj658ksLWQHeKnJg_fDRroFxzMOlGwt3BRnDE/edit):

- [x] Открыть браузер. Перейти на https://citilink.ru/
- [x] Перейти в Каталог
- [x] Навести курсор на раздел Смартфоны и планшеты
- [x] Перейти в раздел "Смартфоны"
- [x] Убедится что вы перешли в раздел “Смартфоны”
- [x] Задать параметр «Производитель» Apple.
- [x] Дождаться результатов поиска.
- [x] Убедится что в выборку попали только iPhone. Если страниц несколько – проверить все.

### Стек:

- Java
- Junit Jupiter
- Selenide
- PageObject по стилю Selenide

### Требования:

- Тест разбит на шаги.
- Тест должен быть параметризован.
- Необходимо использовать константы через properties файл.
- Все ассерты должны быть переопределены.
- Трай\кетчи не должны быть использованы для реализации логики.
- Если в коде используются циклы, необходимо исключить возможность бесконечного цикла.
- Обязательно использовать JavaDoc для всех методов и переменных. На русском языке с указанием автора.
- Недопустимо использования Thread.sleep и Трай\кетчи. За исключением, создания собственных ожиданий (к примеру каждый 5
  миллисекунд проверяем что что-то случилось, и так не более 10 секунд.). Лучше обойтись явными\неявными ожиданиями.
- XPath не должен содержать индексов, динамических элементов
- Тест должен работать для любого производителя.
- Скриншоты снимаются автоматически на любое взаимодействие с веб страницей

