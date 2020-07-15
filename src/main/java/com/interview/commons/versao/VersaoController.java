package com.interview.commons.versao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "VERSAO" })
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/versao")
public class VersaoController {

	@Value(value = "${application.versionAPI}")
	private String versao;
	
	@ApiOperation(
		value = "RECUPERA A VERSÃO ATUAL DA API", 
		notes = "Recupera a versão atual do API", 
		nickname = "recuparaVersao" 
	)
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> recuparaVersao() {
		return ResponseEntity.ok().body(
			"{\"Sistema\" : \"INTERVIEW\", " + 
			"\"Projeto\" : \"Sistema Springboot Interview\", " + 
			"\"API\" : \"WSINTERVIEW\", " + 
			"\"Versao\" : \"" + versao + "\"}"
		);
	}
	
}
