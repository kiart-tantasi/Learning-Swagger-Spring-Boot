package com.example.springbootswaggergradle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// SOURCE: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

@Configuration
//@Import(SpringDataRestConfiguration.class) // For Spring Data Rest
//@Import(BeanValidatorPluginsConfiguration.class) // Bean Validations
public class SpringFoxConfig {

    // Docket for OAUTH

    @Bean
    public Docket api() {
        ArrayList<Response> responseBuilderArrayList = new ArrayList<>();
        responseBuilderArrayList.add(new ResponseBuilder().code("403").description("NOPE !").build());
        responseBuilderArrayList.add(new ResponseBuilder().code("404").description("NOT FOUND !").build());
        responseBuilderArrayList.add(new ResponseBuilder().code("500").description("SERVER OUT !").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoTwo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootswaggergradle.employee"))
                .paths(PathSelectors.any())
                .build()

                // Custom Methods Response Messages
                // globally overriding response messages of HTTP methods
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responseBuilderArrayList);

                // SECURITY
//                .securitySchemes(Arrays.asList(securityScheme()))
//                .securityContexts(Arrays.asList(securityContext()));
    }

    // API INFO
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEMO SWAGGER REST API")
                .description("This is demo SWAGGER API.")
                .version("0.0.1")
                .build();
    }

    private ApiInfo apiInfoTwo() {
        return new ApiInfo(
                "TITLE",
                "Description",
                "0.0.1",
                "https://www.google.com",
                new Contact("Keith Tantasi", "https://www.google.com", "keith@tantasi.com"),
                "License",
                "https://www.google.com",
                Collections.emptyList()
        );
    }

    // ----------------------------------- SECURITY CONFIG ----------------------------------- //
//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId("CLIENT_ID")
//                .clientSecret("CLIENT_SECRET")
//                .scopeSeparator(" ")
//                .useBasicAuthenticationWithAccessCodeGrant(true)
//                .build();
//    }
//    private SecurityScheme securityScheme() {
//        GrantType grantType = new AuthorizationCodeGrantBuilder()
//                .tokenEndpoint(new TokenEndpoint("AUTH_SERVER" + "/token", "oauthtoken"))
//                .tokenRequestEndpoint(
//                        new TokenRequestEndpoint("AUTH_SERVER" + "/authorize", "CLIENT_ID", "CLIENT_SECRET")
//                )
//                .build();
//
//        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
//                .grantTypes(Arrays.asList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//        return oauth;
//    }
//    private AuthorizationScope[] scopes() {
//        AuthorizationScope[] scopes = {
//                new AuthorizationScope("read", "for read operations"),
//                new AuthorizationScope("write", "for write operations"),
//                new AuthorizationScope("foo", "Access foo API") };
//        return scopes;
//    }
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(
//                        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
//                .forPaths(PathSelectors.regex("/foos.*"))
//                .build();
//    }
}


