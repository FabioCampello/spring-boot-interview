package com.interview.api.cidade.service.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.api.cidade.dto.CidadeCadastroRequestDTO;
import com.interview.api.cidade.dto.CidadeResponseDTO;
import com.interview.api.cidade.repository.CidadeRepository;
import com.interview.api.cidade.service.CidadeService;
import com.interview.api.estado.repository.EstadoRepository;
import com.interview.commons.entities.Cidade;
import com.interview.commons.entities.Estado;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.ValidacoesUtil;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	/**
	 * CADASTRA CIDADE.
	 * 
	 * @param CidadeCadastroRequestDTO
	 * @return void
	 */
	public void cadastro(CidadeCadastroRequestDTO cidadeCadastroRequestDTO) throws ApiException {
		ValidacoesUtil.validaParametroString(cidadeCadastroRequestDTO.getNmCidade(), "Informe o nome da cidade.");
		Long cdEstado = ValidacoesUtil.validaIdConvertStringToLong(cidadeCadastroRequestDTO.getCdEstado(), "Estado inválido.");
		Optional<Estado> estado = estadoRepository.findById(cdEstado);
		if(isEmpty(estado)) {
			throw new ApiException("Unidade da federação não encontrada.");
		}
		if(!isEmpty(cidadeRepository.validaSeJaExisteCidadeCadastradaNoEstado(cidadeCadastroRequestDTO.getNmCidade().toUpperCase(), cdEstado))) {
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
	public CidadeResponseDTO consultarCidadePeloNome(String nmCidade)  throws ApiException {
		ValidacoesUtil.validaParametroString(nmCidade, "Nome da cidade inválido.");
		Cidade cidade = cidadeRepository.consultarCidadePeloNome(nmCidade.toUpperCase());
		if(isEmpty(cidade)) {
			throw new ApiException("Registro não encontrado.");
		}
		CidadeResponseDTO cidadeResponseDTO = new CidadeResponseDTO();
		return cidadeResponseDTO.montaResponsePorNome(cidade);
	}
	
}
