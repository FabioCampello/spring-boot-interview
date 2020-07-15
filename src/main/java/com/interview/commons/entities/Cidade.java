package com.interview.commons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.interview.api.cidade.dto.CidadeCadastroRequestDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_cidade")
public class Cidade implements Serializable {

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
	@Column(name = "nm_cidade")
	private String nmCidade;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	public Cidade() {
	}
	
	public Cidade montaCidade(CidadeCadastroRequestDTO cidadeCadastroRequestDTO) {
		Cidade cidade = new Cidade();
		cidade.setId(cidadeCadastroRequestDTO.getId());
		cidade.setNmCidade(cidadeCadastroRequestDTO.getNmCidade().toUpperCase());
		Estado estado = new Estado(); 
		estado.setId(Long.parseLong(cidadeCadastroRequestDTO.getCdEstado()));
		cidade.setEstado(estado);
		
		return cidade;
	}

}
