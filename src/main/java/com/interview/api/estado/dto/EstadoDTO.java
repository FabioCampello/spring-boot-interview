package com.interview.api.estado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstadoDTO {
	
	private Long id;
	private String nmEstado;
	private String dsSigla;
	
	public EstadoDTO() {
	}

}
