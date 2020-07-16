package com.interview.api.cidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.interview.commons.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	/**
	 * VERIFICA SE JÁ EXISTE CIDADE CADASTRADA NO ESTADO.
	 * 
	 * @param nmCidade
	 * @param id
	 * @return Cidade
	 */
	@Query(value = "SELECT * FROM TB_CIDADE c WHERE c.nm_cidade = :nmCidade AND c.id_estado = :cdEstado", nativeQuery = true)
	public Cidade validaSeJaExisteCidadeCadastradaNoEstado(@Param("nmCidade") String nmCidade, @Param("cdEstado") Long id);
	
	/**
	 * CONSULTA CIDADE PELO NOME.
	 * 
	 * @param nmCidade
	 * @return Cidade
	 */
	@Query(value = "SELECT * FROM TB_CIDADE c WHERE c.nm_cidade = :nmCidade", nativeQuery = true)
	public Cidade consultarCidadePeloNome(@Param("nmCidade") String nmCidade);
	
	/**
	 * RECUPERA CIDADE PELO ID DO ESTADO
	 * 
	 * @param idEstado
	 * @return Cidade
	 */
	@Query(value = "SELECT * FROM TB_CIDADE c WHERE c.id_estado = :idEstado", nativeQuery = true)
	public Cidade consultarCidadePeloIdEstado(@Param("idEstado") Long idEstado);
	
	/**
	 * RECUPERA CIDADE PELO NOME DO ESTADO
	 * 
	 * @param nmEstado
	 * @return Cidade
	 */
	@Query(value = "SELECT * FROM TB_CIDADE c, TB_ESTADO e WHERE c.id_estado = e.id AND e.nm_estado = :nmEstado", nativeQuery = true)
	public Cidade consultarCidadePeloNomeEstado(@Param("nmEstado") String nmEstado);

}
