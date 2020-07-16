package com.interview.api.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.interview.commons.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	/**
	 * CONSULTA CLIENTE PELO NOME.
	 * 
	 * @param nmCliente
	 * @return Cliente
	 */
	@Query(value = "SELECT * FROM TB_CLIENTE c WHERE c.nm_completo LIKE CONCAT('%', :nmCliente,'%')", nativeQuery = true)
	public List<Cliente> consultarClientePeloNome(@Param("nmCliente") String nmCliente);

	/**
	 * RECUPERA CLIENTE PELO ID
	 * 
	 * @param idCliente
	 * @return Cliente
	 */
	@Query(value = "SELECT * FROM TB_CLIENTE c WHERE c.id = :idCliente", nativeQuery = true)
	public Cliente consultarClientePeloId(@Param("idCliente") Long idCliente);

}
