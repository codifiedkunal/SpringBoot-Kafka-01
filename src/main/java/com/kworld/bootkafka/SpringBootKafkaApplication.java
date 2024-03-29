package com.kworld.bootkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class SpringBootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaApplication.class, args);
	}


	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"My kafka api",
				"learn kafka.",
				"1.0",
				"Terms of service",
				new Contact("KK", "kunal.kworld", "kunal@kworld.com"),
				"License of API", "API license URL", Collections.emptyList());
	}

}
