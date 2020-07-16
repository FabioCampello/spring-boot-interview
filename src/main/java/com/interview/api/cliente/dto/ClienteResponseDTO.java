package com.interview.api.cliente.dto;

import com.interview.commons.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteResponseDTO {

	private String id;
	private String nmCompleto;
	private String dsSexo;
	private String dtNascimento;
	private String numIdade;

	public ClienteResponseDTO() {
	}
	
	public ClienteResponseDTO montaClienteResponse(Cliente cliente) {
		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		clienteResponseDTO.setId(cliente.getId().toString());
		clienteResponseDTO.setNmCompleto(cliente.getNmCompleto().toString());
		clienteResponseDTO.setDsSexo(cliente.getDsSexo().toString());
		clienteResponseDTO.setDtNascimento(cliente.getDtNascimento().toString());
		clienteResponseDTO.setNumIdade(cliente.getNumIdade().toString());
		
		return clienteResponseDTO;
	}

}
