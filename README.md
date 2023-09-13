# procon34-cyber-wars-backend

## 環境変数

> .env
>
> ```dotenv:.env
> DATABASE=「Database Name」
> DB_HOST=「Database Host」
> DB_PORT=「Database Port」
> DB_USER=「Database User」
> OPENAI_API_KEY=「OpenAI API Key」
> ```

## 実行方法

「Database Password」にデータベースのユーザーパスワードを入れて実行

```
gradle clean build
./gradlew bootRun -Pargs="--spring.datasource.password=「Database Password」"
```

## 開発環境

- [Ubuntu 22.04.2 LTS](https://jp.ubuntu.com/)
- [OpenJDK 17.0.8.1](https://openjdk.org/)
- [Gradle 8.1.1](https://gradle.org/)
- [Spring Boot CLI 3.0.6](https://spring.io/)
- [MySQL Connector Java 8.0.33](https://www.mysql.com/jp/products/connector/)
- [MyBatis 3.0.0](https://blog.mybatis.org/)
- [MariaDB 10.6.12](https://mariadb.org/)
- [PHP 8.1.2](https://www.php.net/)
