package com.interview.api.estado.service;

import java.util.List;
import java.util.Optional;

import com.interview.api.estado.dto.EstadoDTO;
import com.interview.commons.entities.Estado;
import com.interview.commons.exception.ApiException;

public interface EstadoService {

	List<EstadoDTO> recuperaListaEstados() throws ApiException;
	
    Optional<Estado> recuperaEstadoById(String cdEstado) throws ApiException;

}
