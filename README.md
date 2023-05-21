# procon34_CYBER_WARS_backend

## 環境変数

> .env
>
> ```dotenv:.env
> DATABASE=「DATABASE NAME」
> DB_HOST=「DB HOST」
> DB_PORT=「DB PORT」
> DB_USER=「DB USER」
> OPENAI_API_KEY=「OPENAI API KEY」
> ```

## 実行方法

```
gradle clean build
./gradlew bootRun -Pargs="--spring.datasource.password=「Database Password」"
```

## 開発環境

- [Ubuntu 22.04.2 LTS](https://jp.ubuntu.com/)
- [OpenJDK 19.0.2](https://openjdk.org/)
- [Gradle 8.1.1](https://gradle.org/)
- [Spring Boot CLI 3.0.6](https://spring.io/)
- [PHP 8.1.2](https://www.php.net/)
