package com.interview.api.cidade.service.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.api.cidade.dto.CidadeCadastroRequestDTO;
import com.interview.api.cidade.dto.CidadeResponseDTO;
import com.interview.api.cidade.repository.CidadeRepository;
import com.interview.api.cidade.service.CidadeService;
import com.interview.api.estado.service.EstadoService;
import com.interview.commons.entities.Cidade;
import com.interview.commons.entities.Estado;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.ValidacoesUtil;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	/**
	 * CADASTRA CIDADE.
	 * 
	 * @param CidadeCadastroRequestDTO
	 * @return void
	 */
	@Override
	public void cadastro(CidadeCadastroRequestDTO cidadeCadastroRequestDTO) throws ApiException {
		ValidacoesUtil.validaParametroString(cidadeCadastroRequestDTO.getNmCidade(), "Informe o nome da cidade.");
		Estado estado = estadoService.recuperaEstadoById(cidadeCadastroRequestDTO.getCdEstado()).get();
		if(!isEmpty(cidadeRepository.validaSeJaExisteCidadeCadastradaNoEstado(cidadeCadastroRequestDTO.getNmCidade().toUpperCase(), estado.getId()))) {
			throw new ApiException("Cidade já cadastrada nesse estado.");
		}
		Cidade cidade = new Cidade();
		cidadeRepository.save(cidade.montaCidade(cidadeCadastroRequestDTO));
	}
	
	/**
	 * RECUPERA CIDADE PELO NOME
	 * 
	 * @param nmCidade
	 * @return CidadeResponseDTO
	 */
	@Override
	public CidadeResponseDTO consultarCidadePeloNome(String nmCidade)  throws ApiException {
		ValidacoesUtil.validaParametroString(nmCidade, "Nome da cidade inválido.");
		Cidade cidade = cidadeRepository.consultarCidadePeloNome(nmCidade.toUpperCase());
		if(isEmpty(cidade)) {
			throw new ApiException("Registro não encontrado.");
		}
		CidadeResponseDTO cidadeResponseDTO = new CidadeResponseDTO();
		return cidadeResponseDTO.montaResponsePorNome(cidade);
	}
	
	/**
	 * RECUPERA CIDADE PELO ID DO ESTADO
	 * 
	 * @param nmCidade
	 * @return CidadeResponseDTO
	 */
	@Override
	public CidadeResponseDTO consultarCidadePeloIdEstado(String idEstado) throws ApiException {
		ValidacoesUtil.validaParametroString(idEstado, "Estado inválido.");
		Cidade cidade = cidadeRepository.consultarCidadePeloIdEstado(ValidacoesUtil.validaParamConvertStringToLong(idEstado, "Estado inválido."));
		if(isEmpty(cidade)) {
			throw new ApiException("Registro não encontrado.");
		}
		CidadeResponseDTO cidadeResponseDTO = new CidadeResponseDTO();
		return cidadeResponseDTO.montaResponsePorNome(cidade);
	}
	
	/**
	 * RECUPERA CIDADE PELO NOME DO ESTADO
	 * 
	 * @param nmEstado
	 * @return CidadeResponseDTO
	 */
	@Override
	public CidadeResponseDTO consultarCidadePeloNomeEstado(String nmEstado) throws ApiException {
		ValidacoesUtil.validaParametroString(nmEstado, "Nome estado inválido.");
		Cidade cidade = cidadeRepository.consultarCidadePeloNomeEstado(nmEstado);
		if(isEmpty(cidade)) {
			throw new ApiException("Registro não encontrado.");
		}
		CidadeResponseDTO cidadeResponseDTO = new CidadeResponseDTO();
		return cidadeResponseDTO.montaResponsePorNome(cidade);
	}
	
	/**
	 * RECUPERA CIDADE PELO ID
	 * 
	 * @param idCidade
	 * @return CidadeResponseDTO
	 */
	public CidadeResponseDTO consultaCidadePeloId(String idCidade, String path) throws ApiException {
		Optional<Cidade> cidade = cidadeRepository.findById(ValidacoesUtil.validaParamConvertStringToLong(idCidade, "Código da cidade inválido."));
		if(isEmpty(cidade)) {
			if(path.equals("CLIENTE")) {
				throw new ApiException("Registro de cidade não encontrado.");
			}
			throw new ApiException("Registro não encontrado.");
		}
		CidadeResponseDTO cidadeResponseDTO = new CidadeResponseDTO();
		return cidadeResponseDTO.montaResponsePorNome(cidade.get());
	}
	
}
