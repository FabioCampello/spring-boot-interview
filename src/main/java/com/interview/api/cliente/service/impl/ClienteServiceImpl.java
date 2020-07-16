package com.interview.api.cliente.service.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.api.cliente.dto.ClienteCadastroRequestDTO;
import com.interview.api.cliente.dto.ClienteResponseDTO;
import com.interview.api.cliente.repository.ClienteRepository;
import com.interview.api.cliente.service.ClienteService;
import com.interview.commons.entities.Cliente;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.ValidacoesUtil;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * CADASTRA CLIENTE.
	 * 
	 * @param ClienteCadastroRequestDTO
	 * @return void
	 */
	@Override
	public void cadastro(ClienteCadastroRequestDTO clienteCadastroRequestDTO) throws ApiException {
		validaClienteCadastro(clienteCadastroRequestDTO);
		Cliente cliente = new Cliente();
		clienteRepository.save(cliente.montaClienteCadastro(clienteCadastroRequestDTO));
	}
	
	private void validaClienteCadastro(ClienteCadastroRequestDTO clienteCadastroRequestDTO) throws ApiException {
		ValidacoesUtil.validaParametroString(clienteCadastroRequestDTO.getNmCompleto(), "Informe o nome do cliente.");
		ValidacoesUtil.validaParametroString(clienteCadastroRequestDTO.getDsSexo(), "Informe o sexo do cliente.");
		ValidacoesUtil.validaParametroString(clienteCadastroRequestDTO.getDtNascimento(), "Informe a data de nascimento do cliente.");
		ValidacoesUtil.validaParametroString(clienteCadastroRequestDTO.getNumIdade(), "Informe a idade do cliente.");
	}
	
	/**
	 * RECUPERA CLIENTE PELO NOME
	 * 
	 * @param nmCliente
	 * @return ClienteResponseDTO
	 */
	@Override
	public List<ClienteResponseDTO> consultarClientePeloNome(String nmCliente)  throws ApiException {
		ValidacoesUtil.validaParametroString(nmCliente, "Nome do cliente inválido.");
		List<Cliente> clientes = clienteRepository.consultarClientePeloNome(nmCliente.toUpperCase());
		if(isEmpty(clientes)) {
			throw new ApiException("Nenhum registro não encontrado.");
		}
		List<ClienteResponseDTO> listaClientesResponseDTO = new ArrayList<>();
		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		clientes.forEach( c -> {
			listaClientesResponseDTO.add(clienteResponseDTO.montaClienteResponse(c));
		});
		return listaClientesResponseDTO;
	}
	
	/**
	 * RECUPERA CLIENTE PELO ID
	 * 
	 * @param nmCliente
	 * @return ClienteResponseDTO
	 */
	@Override
	public ClienteResponseDTO consultarClientePeloId(String idCliente) throws ApiException {
		ValidacoesUtil.validaParametroString(idCliente, "idCliente inválido.");
		Cliente cliente = clienteRepository.consultarClientePeloId(ValidacoesUtil.validaParamConvertStringToLong(idCliente, "idCliente inválido."));
		if(isEmpty(cliente)) {
			throw new ApiException("Registro não encontrado.");
		}
		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		return clienteResponseDTO.montaClienteResponse(cliente);
	}

}
