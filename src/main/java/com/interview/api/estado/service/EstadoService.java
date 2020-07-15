package com.interview.api.estado.service;

import java.util.List;

import com.interview.api.estado.dto.EstadoDTO;
import com.interview.commons.exception.ApiException;

public interface EstadoService {

	List<EstadoDTO> recuperaListaEstados() throws ApiException;

}
