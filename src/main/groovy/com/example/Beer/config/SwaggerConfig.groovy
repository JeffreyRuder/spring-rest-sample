package com.example.Beer.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    Docket api() {
        new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .ignoredParameterTypes(MetaClass.class)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.Beer.controller"))
            .paths(PathSelectors.any())
            .build()
    }

    private static ApiInfo apiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder()
        builder.title("Sample Spring Boot API - Beer")
            .description("Sample REST API for saving and retrieving information about craft beers.")
            .contact(new Contact("Jeff Ruder", "https://github.com/JeffreyRuder", "ruderjt@gmail.com"))
            .version("1.0")
        return builder.build()
    }
}
