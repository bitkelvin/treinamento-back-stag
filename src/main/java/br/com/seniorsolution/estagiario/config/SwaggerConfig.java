package br.com.seniorsolution.estagiario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackageClasses = EstagiarioEntity.class)
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfig {
    
    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENCE_TEXT = "License";
    private static final String TITLE = "Estagiarios REST API";
    private static final String DESC = "RESTful API para estagiários";
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESC)
                .license(LICENCE_TEXT)
                .contact(new Contact("Senior Solution", "http://www.seniorsolution.com.br", "comercial@seniorsolution.com.br "))
                .version(SWAGGER_API_VERSION)
                .build();
    }
    
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
                
    }

}