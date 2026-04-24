# P2P Sport Rental — Лабораторные работы

Проект на стеке **Kotlin + Spring Boot**.

Тема: **приложение для аренды спортивного инвентаря по p2p-модели**.

## Что реализовано для ЛР1

- ORM через **Spring Data JPA / Hibernate**
- База данных
- CRUD для объявлений о сдаче инвентаря в аренду
- Минимальный GUI на **Thymeleaf**
- REST API

### Главная сущность

`Listing` — объявление о сдаче спортивного инвентаря в аренду.

Поля:
- `title` — название инвентаря
- `category` — категория
- `ownerName` — владелец
- `city` — город
- `pricePerDay` — цена за день
- `description` — описание
- `status` — статус объявления
- `createdAt` — дата создания

Такой вариант отражает **p2p-модель**, потому что объявление публикует частный владелец, а другой пользователь потенциально может арендовать инвентарь.

## Как запускать локально

По умолчанию используется профиль `local` и встроенная БД **H2**, чтобы проще выполнить ЛР1.

### Нужный софт

- JDK 21
- IntelliJ IDEA
- Gradle 8.x

### Запуск

1. Открыть проект в IntelliJ IDEA.
2. Дождаться загрузки зависимостей.
3. Запустить `SportRentalApplication.kt`.
4. Открыть в браузере:
   - `http://localhost:8080/listings` — GUI
   - `http://localhost:8080/api/listings` — REST API
   - `http://localhost:8080/actuator/health` — healthcheck
   - `http://localhost:8080/node-info` — информация о ноде

## REST API

### Получить все объявления
`GET /api/listings`

### Получить объявление по id
`GET /api/listings/{id}`

### Создать объявление
`POST /api/listings`

Пример тела:
```json
{
  "title": "Горные лыжи Fischer",
  "category": "Ski",
  "ownerName": "Иван",
  "city": "Москва",
  "pricePerDay": 1500,
  "description": "Комплект лыж для начинающих",
  "status": "AVAILABLE"
}
```

### Обновить объявление
`PUT /api/listings/{id}`

### Удалить объявление
`DELETE /api/listings/{id}`

## Подготовка к следующим лабораторным

### ЛР2
Уже подготовлено:
- профиль `postgres`
- переменные окружения для БД
- actuator health endpoint

### ЛР3
Уже подготовлено:
- функциональный тест `ListingRestControllerTest`

### ЛР4
Уже подготовлено:
- страница `/node-info`, которая может показывать имя ноды через переменную `NODE_NAME`

