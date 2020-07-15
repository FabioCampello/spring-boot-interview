package com.interview.api.cidade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CidadeCadastroRequestDTO {

	private Long id;
	private String nmCidade;
	private String cdEstado;

	public CidadeCadastroRequestDTO() {
	}

}
