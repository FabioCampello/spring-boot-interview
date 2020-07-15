package com.interview.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum Constantes {

	// OUTROS
	ERRO("ERRO"),
	ERROR_LOG("Erro inesperado na Api. IdLog= ");

	@Getter @Setter 
	private String descricao;

}
