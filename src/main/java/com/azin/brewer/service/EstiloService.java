package com.azin.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azin.brewer.model.Estilo;
import com.azin.brewer.repository.Estilos;

@Service
public class EstiloService {

	@Autowired
	private Estilos estilos;

	public Estilo salvar(Estilo estilo) {
		if (estilos.findByNomeIgnoreCase(estilo.getNome()).isPresent()) {
			throw new IllegalArgumentException("Estilo com nome ja cadastrado");
		}
		return estilos.save(estilo);
	}

}
