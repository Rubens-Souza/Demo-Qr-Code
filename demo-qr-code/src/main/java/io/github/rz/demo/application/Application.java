package io.github.rz.demo.application;


import java.time.Duration;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de configuração padrão do Spring Boot
 * 
 */
@Configuration
@ComponentScan(basePackages = { "io.github.rz" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Application extends SpringBootServletInitializer {

	/**
	 * Parâmetro que define o timeout da conexão com host
	 */
	@Value("${rest.connect-timeout:1}")
	private int restConnectTimeout;

	/**
	 * Parâmetro que define o timeout da requisição
	 */
	@Value("${rest.read-timeout:35}")
	private int restReadTimeout;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#
	 * configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	/**
	 * Classe de inicialização padrão
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		configureApplication(new SpringApplicationBuilder()).run(args);
	}

	/**
	 * Configuração do modo da aplicação
	 * 
	 * @param builder
	 * @return
	 */
	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		Locale.setDefault(new Locale("pt", "BR"));
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
	}

	/**
	 * Configuração das propriedades de desserialização JSON do Jackson Provider.
	 * 
	 * @return Jackson2ObjectMapperBuilderCustomizer
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}

	/**
	 * Configuração das propriedades das requisições REST
	 * 
	 * @param restTemplateBuilder
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder //
				.setConnectTimeout(Duration.ofSeconds(restConnectTimeout)) //
				.setReadTimeout(Duration.ofSeconds(restReadTimeout)) //
				.build();
	}
}