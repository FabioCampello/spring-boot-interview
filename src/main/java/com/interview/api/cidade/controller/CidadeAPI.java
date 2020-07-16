package com.interview.api.cidade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.interview.api.cidade.dto.CidadeCadastroRequestDTO;
import com.interview.api.cidade.dto.CidadeResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "CIDADE" })
@CrossOrigin(origins = "*")
@RequestMapping("/cidade")
public interface CidadeAPI {

	@ApiOperation(
		value = "CADASTRA UMA NOVA CIDADE.", 
		notes = "Cadastra uma nova cidade.", 
		nickname = "cadastroCidade"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Registro salvo com sucesso."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<?> cadastroCidade(@RequestBody CidadeCadastroRequestDTO cidadeCadastroRequestDTO);
	
	@ApiOperation(
		value = "RECUPERA CIDADE PELO NOME.", 
		notes = "Recupera cidade pelo nome.", 
		nickname = "consultarCidadePeloNome" 
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Registro recuperado com sucesso.", response = CidadeResponseDTO.class),
		@ApiResponse(code = 204, message = "Cidade não encontrada."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="nmCidade", value="Nome da cidade", required=true, dataType="string", paramType="query")
	})
	@GetMapping(value = "/nome")
	ResponseEntity<?> consultarCidadePeloNome(@RequestParam("nmCidade") String nmCidade);
	
	@ApiOperation(
		value = "RECUPERA CIDADE PELO ID DO ESTADO.", 
		notes = "Recupera cidade pelo id do estado.", 
		nickname = "consultarCidadePeloIdEstado" 
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Registro recuperado com sucesso.", response = CidadeResponseDTO.class),
		@ApiResponse(code = 204, message = "Cidade não encontrada."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="idEstado", value="Codigo do estado", required=true, dataType="string", paramType="query")
	})
	@GetMapping(value = "/estado")
	ResponseEntity<?> consultarCidadePeloIdEstado(@RequestParam("idEstado") String idEstado);

	@ApiOperation(
		value = "RECUPERA CIDADE PELO NOME DO ESTADO.", 
		notes = "Recupera cidade pelo nome do estado.", 
		nickname = "consultarCidadePeloNomeEstado" 
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Registro recuperado com sucesso.", response = CidadeResponseDTO.class),
		@ApiResponse(code = 204, message = "Cidade não encontrada."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="nmEstado", value="Nome do estado", required=true, dataType="string", paramType="query")
	})
	@GetMapping(value = "/nomeEstado")
	ResponseEntity<?> consultarCidadePeloNomeEstado(@RequestParam("nmEstado") String nmEstado);
	
}
