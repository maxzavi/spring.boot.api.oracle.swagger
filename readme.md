# Api spring boot 

Using Lombok, Oracle, Jdbc, docker and swagger

Use **PathVariable** and **RequestParam** annotations

```java
    @GetMapping("/{sku}")
    public ResponseEntity<Product> get(@PathVariable("sku") String sku, @RequestParam(value="brand", required = false) String brand){
```

Only in *dev*, use application.properties, in *prod* with container user environment variables: **SPRING__DATASOURCE__URL** **SPRING__DATASOURCE__USERNAME** **SPRING__DATASOURCE__PASSWORD**:

```properties
spring.datasource.url=jdbc:oracle:thin:@{host}:{ip}:{sid}
spring.datasource.username={username}
spring.datasource.password={password}
```

Maven compile skip test, without application.properties for build docker image

```cmd
mvn compile -DskipTests
```
## Testing

Use **apicall.test** file with **Rest Client** extension for VSC

## Docker

Add dockerfile, using FROM **eclipse-temurin:17-jre-alpine**

```cmd
docker build -t mzavaletav/apiprice:1.0 .
```
run

```cmd
docker run -e SPRING__DATASOURCE__URL=jdbc:oracle:thin:@{{host}}:{{port}}:{{sid}} -e SPRING__DATASOURCE__USERNAME={{username}} -e SPRING__DATASOURCE__PASSWORD={{password}}  -p 8080:8080 mzavaletav/apiprice:1.0
```

## OpenAPIDefinition

Tutorial in https://www.youtube.com/watch?v=0vqgWQIVfMI

Add dependency maven

```xml
		<!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui -->
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.9</version>
		</dependency>
```

view in http://localhost:8080/swagger-ui/index.html

In main class, use **OpenAPIDefinition** annotation

```java
@OpenAPIDefinition(info = @Info(title = "Price API Intercorp", version = "1.0.0"))
public class PrecioApplication {
```

In RestController, use **Tag** annotation

```java
@Tag(name = "Price", description = "Price API")
```