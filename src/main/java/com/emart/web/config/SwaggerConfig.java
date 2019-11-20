package com.emart.web.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Bruno Okafor 2019-11-20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {

		final List<ResponseMessage> globalResponses = Arrays.asList(
				new ResponseMessageBuilder()
						.code(HttpStatus.OK.value())
						.message("Request processed successfully")
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Bad Request, Check request details")
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.UNAUTHORIZED.value())
						.message("Unauthorised request, invalid credential")
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.CONFLICT.value())
						.message("Error due to conflicting business logic")
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.NOT_FOUND.value())
						.message("Request resource not found")
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Oops, Internal Server Error")
						.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalResponses)
				.globalResponseMessage(RequestMethod.POST, globalResponses)
				.globalResponseMessage(RequestMethod.DELETE, globalResponses)
				.globalResponseMessage(RequestMethod.PATCH, globalResponses)
				.select().apis(RequestHandlerSelectors.basePackage("com.emart.web.controller"))
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("EMART API SERVICE")
				.description("Api Documentation")
				.version("1.0.0")
				.build();
	}
}
