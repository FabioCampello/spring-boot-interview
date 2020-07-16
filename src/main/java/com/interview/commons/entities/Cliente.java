package com.interview.commons.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.interview.api.cliente.dto.ClienteCadastroRequestDTO;
import com.interview.commons.exception.ApiException;
import com.interview.commons.util.ValidacoesUtil;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "tb_cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "nm_completo")
	private String nmCompleto;
	
	@Getter
	@Setter
	@Column(name = "ds_sexo")
	private String dsSexo;
	
	@Getter
	@Setter
	@Column(name = "dt_nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3", locale = "pt-BR")
	private Date dtNascimento; 
	
	@Getter
	@Setter
	@Column(name = "num_idade")
	private Integer numIdade;
	
	@Getter
	@Setter
	@ManyToMany
	@JoinTable(
		name = "cliente_cidade", 
		joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "cidade_id", referencedColumnName = "id"), 
		uniqueConstraints = @UniqueConstraint(columnNames = {"cliente_id", "cidade_id" })
	)
	private List<Cidade> cidades;
	
	public Cliente() {
	}
	
	public Cliente montaClienteCadastro(ClienteCadastroRequestDTO clienteCadastroRequestDTO) throws ApiException {
		Cliente cliente = new Cliente();
		cliente.setNmCompleto(clienteCadastroRequestDTO.getNmCompleto().toUpperCase());
		cliente.setDsSexo(clienteCadastroRequestDTO.getDsSexo());
		try {
			cliente.setDtNascimento(ValidacoesUtil.StringParaDate(clienteCadastroRequestDTO.getDtNascimento()));
		} catch (ParseException e) {
			throw new ApiException("Data de nascimento inválida.");
		}
		cliente.setNumIdade(ValidacoesUtil.validaParamConvertStringToInteger(clienteCadastroRequestDTO.getNumIdade(), "Idade inválida."));
		
		return cliente;
	}

}
