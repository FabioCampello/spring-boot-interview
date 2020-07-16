package com.interview.api.cliente.service;

import java.util.List;

import com.interview.api.cliente.dto.ClienteCadastroRequestDTO;
import com.interview.api.cliente.dto.ClienteResponseDTO;
import com.interview.commons.exception.ApiException;

public interface ClienteService {

	void cadastro(ClienteCadastroRequestDTO clienteDto) throws ApiException;

	List<ClienteResponseDTO> consultarClientePeloNome(String nmCliente) throws ApiException;

	ClienteResponseDTO consultarClientePeloId(String idCliente) throws ApiException;

}
