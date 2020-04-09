package com.workpal.keypairmanagement.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.workpal.keypairmanagement"))
				.paths(PathSelectors.any()).build()
				.apiInfo(getApiInformation());
	}
	
	private ApiInfo getApiInformation() {
	    return new ApiInfo(
	            "Keypair service",
	            "APIs to manage OpenSSH Keypairs",
	            "V1.0",
	            "TERMS OF SERVICE",
	            new Contact("NAME","URL","EMAIL"),
	            "LICENSE",
	            "LICENSE URL",
	            Collections.emptyList()
	    );
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("TITLE", "DESCIPRION", "VERSION", "TERMS OF SERVICE URL",
				new Contact("NAME", "URL", "EMAIL"), "LICENSE", "LICENSE URL", Collections.emptyList());
	}
}
