package com.coachbar.lms.util;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@Profile({ "dev", "test" })
public class SwaggerConfig {

	@Bean
	public Docket mobileApisV1() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfo(
				"Library Management System API Document",
				"APIs developed for management of library operations as a separate RESTful Microservice named LMS (Library Management System).",
				"V1", "urn:tos",
				new Contact("Documentation Support Team", "https://coachbar.com/", "support@coachbar.com"),
				"Licence: Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>()))
				.groupName("LMS - V1").select()
				.apis(RequestHandlerSelectors.basePackage("com.coachbar.lms.rest"))
				.paths(PathSelectors.any()).build();
	}

	
	@Bean
	UiConfiguration uiConfiguration() {
		return UiConfigurationBuilder.builder()
				.displayRequestDuration(true)
				.build();
	}

}