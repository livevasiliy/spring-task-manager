# Task Manager

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blueviolet)](https://www.postgresql.org/)
[![Liquibase](https://img.shields.io/badge/Liquibase-4.x-orange)](https://www.liquibase.org/)

**Task Manager** — это простое приложение для управления задачами с использованием Spring Boot, PostgreSQL, Liquibase и многопоточности. Это RESTful API позволяет создавать, обновлять, удалять и просматривать задачи.

---

## Содержание

1. [Описание проекта](#описание-проекта)
2. [Технологии](#технологии)
3. [Настройка проекта](#настройка-проекта)
4. [Запуск приложения](#запуск-приложения)
5. [API документация](#api-документация)
6. [Миграции базы данных](#миграции-базы-данных)
7. [Логирование](#логирование)
8. [Дополнительные возможности](#дополнительные-возможности)
9. [Лицензия](#лицензия)

---

## Описание проекта

**Task Manager** — это консольное приложение для управления задачами, которое предоставляет следующие функции:
- Создание, обновление, удаление и просмотр задач.
- Установка дедлайнов и статусов задач.
- Хранение данных в PostgreSQL.
- Использование Liquibase для миграций базы данных.
- Поддержка многопоточности для фоновых задач (например, проверка просроченных задач).

---

## Технологии

Проект использует следующие технологии:
- **Java 21**: Язык программирования.
- **Spring Boot 3.x**: Фреймворк для создания REST API.
- **Spring Data JPA**: Для работы с базой данных через Hibernate.
- **PostgreSQL**: Реляционная база данных.
- **Liquibase**: Инструмент для управления миграциями базы данных.
- **Lombok**: Для автоматической генерации геттеров/сеттеров.
- **Docker**: Для развертывания PostgreSQL.
- **Maven**: Для управления зависимостями.

---

## Настройка проекта

### 1. Предварительные требования
- **Java 21** или выше.
- **Maven** для сборки проекта.
- **Docker** для запуска PostgreSQL.

### 2. Развертывание PostgreSQL в Docker

Запустите PostgreSQL в Docker:

```bash
docker compose up -d
```

### 3. Клонирование репозитория

Склонируйте репозиторий на вашу локальную машину:

```bash
git clone https://github.com/livevasiliy/spring-task-manager.git
cd spring-task-manager
```

### 4. Настройка `application.properties`

Убедитесь, что файл `src/main/resources/application.properties` содержит правильные настройки для подключения к PostgreSQL:

```properties
# Настройки сервера
server.port=8080

# Настройки PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager
spring.datasource.username=homestead
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# Настройки JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Настройки Liquibase
spring.liquibase.change-log=classpath:db/changelog/master.xml
```

---

## Запуск приложения

### 1. Сборка проекта

Выполните сборку проекта с помощью Maven:

```bash
./mvnw clean install
```

### 2. Запуск приложения

Запустите приложение:

```bash
./mvnw spring-boot:run
```

Приложение будет доступно по адресу: `http://localhost:8080`.

---

## API документация

API предоставляет следующие эндпоинты:

### 1. Получить все задачи
```http
GET /api/tasks
```

### 2. Создать новую задачу
```http
POST /api/tasks
Content-Type: application/json

{
    "title": "Learn Spring Boot",
    "description": "Complete the first project",
    "dueDate": "2023-12-01",
    "status": "TODO"
}
```

### 3. Получить задачу по ID
```http
GET /api/tasks/{id}
```

### 4. Обновить задачу
```http
PUT /api/tasks/{id}
Content-Type: application/json

{
    "title": "Learn Spring Boot and Liquibase",
    "description": "Complete the first project with database migrations",
    "dueDate": "2023-12-01",
    "status": "IN_PROGRESS"
}
```

### 5. Удалить задачу
```http
DELETE /api/tasks/{id}
```

---

## Миграции базы данных

Миграции базы данных управляются с помощью **Liquibase**. Все миграции находятся в папке `src/main/resources/db/changelog`.

Пример файла миграции (`21-01-changelog.xml`):

```xml
<changeSet id="1" author="livevasiliy">
    <createTable tableName="tasks">
        <column name="id" type="BIGINT" autoIncrement="true">
            <constraints primaryKey="true" nullable="false"/>
        </column>
        <column name="title" type="VARCHAR(255)">
            <constraints nullable="false"/>
        </column>
        <column name="description" type="TEXT"/>
        <column name="due_date" type="DATE"/>
        <column name="status" type="VARCHAR(50)">
            <constraints nullable="false"/>
        </column>
    </createTable>
</changeSet>
```

---

## Логирование

Для локального окружения включено логирование Liquibase. Вы можете настроить уровень логирования в `application.properties`:

```properties
logging.level.liquibase=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

---

## Дополнительные возможности

1. **Многопоточность**: Фоновая проверка просроченных задач с использованием `@Scheduled`.
2. **Swagger/OpenAPI**: Swagger для документации API.
3. **CI/CD**: CI/CD pipeline с использованием GitHub Actions.
4. **Тесты**: Unit-тесты и Feature для сервисов и контроллеров.

---

## Лицензия

Этот проект распространяется под лицензией MIT. Подробнее см. файл [LICENSE](LICENSE).

---

Если у вас есть вопросы или предложения, не стесняйтесь открывать issue или связаться со мной!
[livevasiliy@yandex.ru](mailto:livevasiliy@yandex.ru)
---
