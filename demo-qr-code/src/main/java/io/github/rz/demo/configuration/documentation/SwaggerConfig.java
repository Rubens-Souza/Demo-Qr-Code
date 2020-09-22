package io.github.rz.demo.configuration.documentation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuração padrão do Swagger
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${spring.application.host:}")
	private String host;

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${spring.application.description:}")
	private String applicationDescription;

	@Value("${spring.application.version:}")
	private String applicationVersion;

	@Bean
	public Docket api() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo()).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(PathSelectors.any())
				.build();
		if (StringUtils.isNotEmpty(host)) {
			docket.host(host);
		}
		return docket;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(applicationName)
				.description(applicationDescription)
				.version(applicationVersion)
				.build();
	}
}