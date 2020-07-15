package com.interview.api.estado.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "ESTADO" })
@CrossOrigin(origins = "*")
@RequestMapping("/estado")
public interface EstadoAPI {
	
	@ApiOperation(
		value="RECUPERA LISTA DE UNIDADES DA FEDERAÇÃO",
		notes="Recupera lista de unidades da federação.",
		nickname="recuperaListaEstados"
	)
	@GetMapping(value="/uf")
	ResponseEntity<?> recuperaListaEstados();

}
