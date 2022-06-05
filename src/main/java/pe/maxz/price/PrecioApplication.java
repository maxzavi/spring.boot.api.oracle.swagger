package pe.maxz.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Prices Intercorp", version = "1.0.0"))
public class PrecioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrecioApplication.class, args);
	}

}
