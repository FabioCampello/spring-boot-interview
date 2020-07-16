package com.interview.api.cidade.service;

import com.interview.api.cidade.dto.CidadeCadastroRequestDTO;
import com.interview.api.cidade.dto.CidadeResponseDTO;
import com.interview.commons.exception.ApiException;

public interface CidadeService {
	
	void cadastro(CidadeCadastroRequestDTO cidadeDto) throws ApiException;
	
	CidadeResponseDTO consultarCidadePeloNome(String nmCidade) throws ApiException;
	
	CidadeResponseDTO consultarCidadePeloIdEstado(String idEstado) throws ApiException; 

}
