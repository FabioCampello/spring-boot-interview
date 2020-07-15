package com.interview.api.estado.controller.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.interview.api.estado.controller.EstadoAPI;
import com.interview.api.estado.dto.EstadoDTO;
import com.interview.api.estado.service.EstadoService;
import com.interview.commons.enums.Constantes;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.LogUtil;

@RestController
public class EstadoController implements EstadoAPI {
	
	@Autowired
	private EstadoService estadoService;

	@Override
	public ResponseEntity<?> recuperaListaEstados() {
		try {
			List<EstadoDTO> listaEstadosResposeDTO = estadoService.recuperaListaEstados();

			if (isEmpty(listaEstadosResposeDTO)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			return ResponseEntity.status(HttpStatus.OK).body(listaEstadosResposeDTO);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			String idLog = UUID.randomUUID().toString();
			Map<String, String> parametros = new HashMap<>();
			LogUtil.logGenerico(idLog, Constantes.ERRO.toString(), EstadoController.class.getSimpleName(), "recuperaListaEstados", parametros, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body(Constantes.ERROR_LOG.toString() + idLog);
		}
	}

}
