package com.interview.api.cliente.controller.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.interview.api.cidade.controller.impl.CidadeController;
import com.interview.api.cliente.controller.ClienteAPI;
import com.interview.api.cliente.dto.ClienteCadastroRequestDTO;
import com.interview.api.cliente.dto.ClienteResponseDTO;
import com.interview.api.cliente.service.ClienteService;
import com.interview.commons.enums.Constantes;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.LogUtil;

@RestController
public class ClienteController implements ClienteAPI {
	
	@Autowired
	private ClienteService clienteService;

	@Override
	public ResponseEntity<?> cadastroCliente(ClienteCadastroRequestDTO clienteCadastroRequestDTO) {
		try {
			clienteService.cadastro(clienteCadastroRequestDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.NOME.toString().toUpperCase(), clienteCadastroRequestDTO.getNmCompleto());
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "cadastroCliente", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}

	@Override
	public ResponseEntity<?> consultarClientePeloNome(String nmCliente) {
		try {
			List<ClienteResponseDTO> clienteResponseDTO = clienteService.consultarClientePeloNome(nmCliente);
			if(isEmpty(clienteResponseDTO)) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(clienteResponseDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.NOME.toString().toUpperCase(), nmCliente);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "consultarClientePeloNome", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}

	@Override
	public ResponseEntity<?> consultarClientePeloId(String idCliente) {
		try {
			ClienteResponseDTO clienteResponseDTO = clienteService.consultarClientePeloId(idCliente);
			if(isEmpty(clienteResponseDTO)) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(clienteResponseDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.CODIGO.toString().toUpperCase(), idCliente);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "consultarClientePeloId", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}
	
}
