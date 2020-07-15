package com.interview.api.estado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.commons.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
}
