package com.azin.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azin.brewer.model.Estilo;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long> {
	
	Optional<Estilo> findByNomeIgnoreCase(String nome); 
}
