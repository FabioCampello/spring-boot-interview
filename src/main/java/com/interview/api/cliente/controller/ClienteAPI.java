package com.interview.api.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.interview.api.cliente.dto.ClienteCadastroRequestDTO;
import com.interview.api.cliente.dto.ClienteResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "CLIENTE" })
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")
public interface ClienteAPI {

	@ApiOperation(
		value = "CADASTRA UM NOV CLIENTE.", 
		notes = "Cadastra um nov cliente.", 
		nickname = "cadastroCliente"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Registro salvo com sucesso."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<?> cadastroCliente(@RequestBody ClienteCadastroRequestDTO clienteCadastroRequestDTO);
	
	@ApiOperation(
		value = "RECUPERA CLIENTE PELO NOME.", 
		notes = "Recupera cliente pelo nome.", 
		nickname = "consultarClientePeloNome" 
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Registro recuperado com sucesso.", response = ClienteResponseDTO.class),
		@ApiResponse(code = 204, message = "Cliente não encontrada."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="nmCliente", value="Nome do cliente", required=true, dataType="string", paramType="query")
	})
	@GetMapping(value = "/nome")
	ResponseEntity<?> consultarClientePeloNome(@RequestParam("nmCliente") String nmCliente);
	
	@ApiOperation(
		value = "RECUPERA CLIENTE PELO ID.", 
		notes = "Recupera cliente pelo id.", 
		nickname = "consultarClientePeloId" 
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Registro recuperado com sucesso.", response = ClienteResponseDTO.class),
		@ApiResponse(code = 204, message = "Cliente não encontrada."),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="idCliente", value="Codigo do cliente", required=true, dataType="string", paramType="query")
	})
	@GetMapping(value = "/id")
	ResponseEntity<?> consultarClientePeloId(@RequestParam("idCliente") String idCliente);
	
	@ApiOperation(
		value = "REMOVE REGISTRO DE CLIENTE.", 
		notes = "Remove registro de cliente.", 
		nickname = "Remove registro de cliente."
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Registro removido com sucesso.", response = ClienteResponseDTO.class),
		@ApiResponse(code = 400, message = "Não foi possível prosseguir, existe crítica de negócio.") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="idCliente", value="Código do cliente", required=true, dataType="string", paramType="query")
	})
	@DeleteMapping(value = "/remove")
	public ResponseEntity<?> removeCliente(@RequestParam("idCliente") String idCliente);
		
}
