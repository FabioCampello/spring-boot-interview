package com.interview.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration("swaggerConfigProperties")
public class SwaggerConfigProperties {
	
	@Value(value = "${application.versionAPI}")
	private String apiVersao;
	
	@Value("${swagger.titulo}")
    private String titulo;

	@Value("${swagger.descricao}")
	private String descricao;

	@Value("${swagger.termoDeUso}")
	private String termoDeUso;

	@Value("${swagger.licenca}")
	private String licenca;

	@Value("${swagger.licencaUrl}")
	private String licencaUrl;

	@Value("${swagger.contatoNome}")
	private String contatoNome;

	@Value("${swagger.contatoSite}")
	private String contatoSite;
	
	@Value("${swagger.contatoEmail}")
	private String contatoEmail;

}
