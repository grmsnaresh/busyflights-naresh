package com.travix.medusa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.travix.medusa.common.converter.IATAConverter;
import com.travix.medusa.common.converter.LocalDateConverter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Customization for Spring web.
 *
 * @author grmsnaresh
 *
 */
@Configuration
public class BusyFlightsConfig extends WebMvcConfigurerAdapter {

	/**
	 * Allows Rest endpoints to convert String parameters into the expected java Objects.
	 */
	@Override
	public void addFormatters(final FormatterRegistry registry) {
		registry.addConverter(new IATAConverter());
		registry.addConverter(new LocalDateConverter());
	}

	@Bean
	public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder builder) {
		final ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper;
	}

	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any()).build();
		}


	}
}


