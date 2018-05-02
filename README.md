# Тестовое задание для ЦБ</br>
Использованы:
- Maven
- Flyway
- Hibernate
- Spring orm + mvc
- HSQLDB | PostgreSQL 10.3
- Tomcat 8.5

## Запуск:
- По умолчанию, при запуске приложение поднимает in-memory БД на основе HSQLDB
- Flyway интегрирован с Spring и срабатывает при старте (создает таблицы и наполняет данными)

## Перед запуском:</br>
Если необходимо, замените в db/db.properties данные БД
````
database.driver
database.url
database.username
database.password
````
И добавьте в pom.xml подходящий драйвер.