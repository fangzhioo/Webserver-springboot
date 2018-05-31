/*
 * @Author: fangzhi 
 * @Date: 2018-05-23 16:25:07 
 * @Last Modified by: fangzhi
 * @Last Modified time: 2018-05-23 16:28:16
 */
package com.fangzhi.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2{

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fangzhi.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("fangzhi webserver API")
                .description("fangzhi webserver API desc")
                .termsOfServiceUrl("http://www.fangzhioo.com/")
                .contact("fangzhi")
                .version("0.0.1")
                .build();
    }
}