package com.interview.api.cliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteCadastroRequestDTO {

	private String id;
	private String nmCompleto;
	private String dsSexo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3", locale = "pt-BR")
	private String dtNascimento;
	private String numIdade;
	private String idCidade;
	
	public ClienteCadastroRequestDTO() {
	}

}
