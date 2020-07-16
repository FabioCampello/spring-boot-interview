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

	/**
	 * CADASTRA CLIENTE.
	 * 
	 * @param ClienteCadastroRequestDTO
	 * @return void
	 */
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
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), ClienteController.class.getSimpleName(), "cadastroCliente", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}

	/**
	 * RECUPERA CLIENTE PELO NOME
	 * 
	 * @param nmCliente
	 * @return ClienteResponseDTO
	 */
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
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), ClienteController.class.getSimpleName(), "consultarClientePeloNome", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}

	/**
	 * RECUPERA CLIENTE PELO ID
	 * 
	 * @param nmCliente
	 * @return ClienteResponseDTO
	 */
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
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), ClienteController.class.getSimpleName(), "consultarClientePeloId", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}
	
	/**
	 * REMOVENDO CLIENTE PELO O ID.
	 * 
	 * @param idCliente
	 * @return ClienteResponseDTO
	 */
	@Override
	public ResponseEntity<?> removeCliente(String idCliente) {
		try {
			ClienteResponseDTO clienteResponseDTO = clienteService.removeCliente(idCliente);
			if (isEmpty(clienteResponseDTO)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(clienteResponseDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.CODIGO.toString(), idCliente);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), ClienteController.class.getSimpleName(), "removeCliente", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}
	
	/**
	 *  ATUALIZA O NOME DO CLIENTE.
	 *  
	 * @param nmCliente
	 * @param idCliente
	 * @return
	 */
	@Override
	public ResponseEntity<?> atualizaNomeCliente(String nmCliente, String idCliente) {
		try {
			clienteService.atualizaNomeCliente(nmCliente, idCliente);
			return ResponseEntity.ok().build();
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.NOME.toString(), nmCliente);
			parametros.put(Constantes.CODIGO.toString(), idCliente);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), ClienteController.class.getSimpleName(), "atualizaNomeCliente", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	} 
	
}
