---
название: Отчётный документ
дата: 04.01.2024
Отчёт составил: Александр Бондаренко
---

# Отчётные документы по итогам тестирования.

### краткое описание
При тестирование приложения aqa-shop были обнаружены критические проблемы.
Такие как отправка SQL иньекций и некорректных данных.

### количество тест-кейсов
Всего было обработано 28 тест кейсов.

### процент успешных и не успешных тест-кейсов
![dp_allure](https://github.com/Feruno/-JavaDiplomProject/assets/60847105/a7136b0d-6687-457a-8d27-c40582c17c0f)

### общие рекомендации
##### общие рекомендации для UI
Для поля "Владелец" сделать ограничение на ввод, например только буквы кириллицы и латиницы.
##### общие рекомендации для API
Для полей "month", "year", "holder", "cvc" нужно ограничение на формат данных передаваемых по API. 
Так как при передачи некорректных данных по API происходит успешное выполнение запроса.
![img_1.png](img_1.png)
