### Процедура запуска автотестов

## Для запуска авто тестов нужны следующие программы.
1. Intellij idea
1. Docker

## Для запуска авто тестов нужны следующие действия.
1. Запустить Docker
1. Запустить Intellij idea
1. В терминале Intellij idea выполнить команду docker-compose up ![image](https://github.com/Feruno/-JavaDiplomProject/assets/60847105/f40da2ec-3120-4e77-985a-d9f915107416)
1. Дождаться запуска всех трёх контейнеров. С сообщением  "/usr/sbin/mysqld: ready for connections."
1. Запустить jar файл командой java -jar ./artifacts/aqa-shop.jar или ПКМ по jar фалу и выбрать "Run aqa-shop.jar"
![image](https://github.com/Feruno/-JavaDiplomProject/assets/60847105/db112fd8-897c-4d91-98da-7bba14a5a0ce) 

## Для запуска авто тестов с конкретной базой данных нужны следующие действия.
1. Выполнить все прошлые шаги.
1. Запуск тестов:
   2. Команда для MYSQL ```./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"```
   3. Команда для PostgreSql ```./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"```
1. Запустить allure командой ```./gradlew allureServe```


## Для просмотра данных, ответов на запросы нужны следующие программы.
1. Postman - для просмотра API запросов/ответов.
1. DBeaver - для просмотра таблиц и данных в базе данных.
