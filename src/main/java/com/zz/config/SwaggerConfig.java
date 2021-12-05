package com.zz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author zz
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * 配置swagger.Docket的bean实例
     * @param environment
     * @return
     */
    @Bean
    public Docket docket(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("808Bass")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zz.controller"))
                .build();
    }

    /**
     * 配置Swagger信息 = apiInfo
     * @return
     */
    private ApiInfo apiInfo() {

        Contact contact = new Contact("zZeng", "http://localhost:8444/", "zzengx@qq.com");
        return new ApiInfo(
                "Swagger",
                "再小的帆也能远航~~",
                "v1.0",
                "http://terms.service.url/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
