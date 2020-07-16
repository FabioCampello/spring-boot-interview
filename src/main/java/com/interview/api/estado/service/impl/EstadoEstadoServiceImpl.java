package com.interview.api.estado.service.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.api.estado.dto.EstadoDTO;
import com.interview.api.estado.repository.EstadoRepository;
import com.interview.api.estado.service.EstadoService;
import com.interview.commons.entities.Estado;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.ValidacoesUtil;

@Service
public class EstadoEstadoServiceImpl implements EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	/**
	 * RECUPERA LISTA DE UNIDADES FEDERATIVAS.
	 */
	@Override
	public List<EstadoDTO> recuperaListaEstados() throws ApiException {
		List<Estado> listaEstados = estadoRepository.findAll();
		
		if(isEmpty(listaEstados)) {
			return Collections.emptyList();
		}
		List<EstadoDTO> listaEstadosResposeDTO = new ArrayList<>();
		listaEstados.forEach( uf -> {			
			EstadoDTO estado = new EstadoDTO();
			BeanUtils.copyProperties(uf, estado);
			listaEstadosResposeDTO.add(estado);
		});
		return listaEstadosResposeDTO;
		
	}

	/**
	 * RECUPERA ESTADO POR ID
	 * 
	 * @param cdEstado
	 * @return
	 * @throws ApiException
	 */
	@Override
	public Optional<Estado> recuperaEstadoById(String cdEstado) throws ApiException {
		ValidacoesUtil.validaParametroString(cdEstado, "Informe o estado.");
		Optional<Estado> estado = estadoRepository.findById(ValidacoesUtil.validaParamConvertStringToLong(cdEstado, "Estado inválido."));
		if(isEmpty(estado)) {
			throw new ApiException("Unidade da federação não encontrada.");
		}
		return estado;
	}

}
