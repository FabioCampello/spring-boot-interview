package com.interview.api.cidade.controller.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.interview.api.cidade.controller.CidadeAPI;
import com.interview.api.cidade.dto.CidadeCadastroRequestDTO;
import com.interview.api.cidade.dto.CidadeResponseDTO;
import com.interview.api.cidade.service.CidadeService;
import com.interview.commons.enums.Constantes;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.LogUtil;

@RestController
public class CidadeController implements CidadeAPI {
	
	@Autowired
	private CidadeService cidadeService;
	
	/**
	 * CADASTRA CIDADE.
	 * 
	 * @param CidadeCadastroRequestDTO
	 * @return void
	 */
	@Override
	public ResponseEntity<?> cadastroCidade(CidadeCadastroRequestDTO cidadeCadastroRequestDTO) {
		try {
			cidadeService.cadastro(cidadeCadastroRequestDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.NOME.toString().toUpperCase(), cidadeCadastroRequestDTO.getNmCidade());
			parametros.put(Constantes.CODIGO.toString().toUpperCase(), cidadeCadastroRequestDTO.getCdEstado().toString());
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "cadastroCidade", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}
	
	/**
	 * RECUPERA CIDADE PELO NOME.
	 * 
	 * @param nmCidade
	 * @return CidadeResponseDTO
	 */
	@Override
	public ResponseEntity<?> consultarCidadePeloNome(String nmCidade) {
		try {
			CidadeResponseDTO cidadeResponseDTO = cidadeService.consultarCidadePeloNome(nmCidade);
			if(isEmpty(cidadeResponseDTO)) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(cidadeResponseDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.NOME.toString().toUpperCase(), nmCidade);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "consultarCidadePeloNome", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}
	
	/**
	 * RECUPERA CIDADE PELO ID DO ESTADO
	 * 
	 * @param idEstado
	 * @return CidadeResponseDTO
	 */
	@Override
	public ResponseEntity<?> consultarCidadePeloIdEstado(String idEstado) {
		try {
			CidadeResponseDTO cidadeResponseDTO = cidadeService.consultarCidadePeloIdEstado(idEstado);
			if(isEmpty(cidadeResponseDTO)) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(cidadeResponseDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.CODIGO.toString().toUpperCase(), idEstado);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "consultarCidadePeloEstado", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}
	
	/**
	 * RECUPERA CIDADE PELO NOME DO ESTADO
	 * 
	 * @param nmEstado
	 * @return CidadeResponseDTO
	 */
	@Override
	public ResponseEntity<?> consultarCidadePeloNomeEstado(String nmEstado) {
		try {
			CidadeResponseDTO cidadeResponseDTO = cidadeService.consultarCidadePeloNomeEstado(nmEstado);
			if(isEmpty(cidadeResponseDTO)) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(cidadeResponseDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			parametros.put(Constantes.NOME.toString().toUpperCase(), nmEstado);
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), CidadeController.class.getSimpleName(), "consultarCidadePeloEstado", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_LOG + idLog);
		}
	}

}
