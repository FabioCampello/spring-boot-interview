package com.interview.commons.versao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/versao")
public class VersaoController {

	@Value(value = "${application.versionAPI}")
	private String versao;
	
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
