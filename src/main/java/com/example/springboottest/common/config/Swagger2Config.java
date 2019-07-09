package com.example.springboottest.common.config;


import io.swagger.models.parameters.Parameter;
import lombok.experimental.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger2Config {

    private String defaultToken = "";
    @Bean
    public Docket createAdminApi() {

        ParameterBuilder tokenPar = new ParameterBuilder();
        List parameters =  new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌-Bearer").modelRef( new ModelRef("string"))
                .parameterType("header").required(false).build();
        tokenPar.defaultValue(defaultToken);
        parameters.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台模块")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboottest.admin.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .build();
    }
}
