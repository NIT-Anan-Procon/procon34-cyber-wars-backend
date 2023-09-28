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
- [Spring Boot CLI 3.1.4](https://spring.io/projects/spring-boot)
- [Spring Dependency Management 1.1.3](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/)
- [MySQL Connector Java 8.0.33](https://www.mysql.com/jp/products/connector/)
- [MyBatis 3.0.2](https://blog.mybatis.org/)
- [Lombok 1.18.28](https://projectlombok.org/)
- [Jakarta Persistence 3.1.0](https://jakarta.ee/specifications/persistence/)
- [Apache Commons Text 1.10.0](https://commons.apache.org/proper/commons-text/)
- [Apache Commons IO 2.13.0](https://commons.apache.org/proper/commons-io/)
- [checkstyle 10.12.3](https://checkstyle.sourceforge.io/)
- [spotless 6.21.0](https://plugins.gradle.org/plugin/com.diffplug.gradle.spotless)
- [MariaDB 10.6.12](https://mariadb.org/)
- [PHP 8.1.2](https://www.php.net/)
