package com.interview.api.cidade.dto;

import com.interview.commons.entities.Cidade;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CidadeResponseDTO {

	private Long id;
	private String nmCidade;
	private Long idEstado;

	public CidadeResponseDTO() {
	}
	
	public CidadeResponseDTO montaResponsePorNome(Cidade cidade) {
		CidadeResponseDTO cidadeResponseDTO = new CidadeResponseDTO();
		cidadeResponseDTO.setId(cidade.getId());
		cidadeResponseDTO.setNmCidade(cidade.getNmCidade());
		cidadeResponseDTO.setIdEstado(cidade.getEstado().getId());
		
		return cidadeResponseDTO;
	}

}
