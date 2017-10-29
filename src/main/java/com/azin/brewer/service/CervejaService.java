package com.azin.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azin.brewer.model.Cerveja;
import com.azin.brewer.repository.Cervejas;

@Service
public class CervejaService {
	
	@Autowired
	private Cervejas cervejas;


	public Cerveja save(Cerveja cerveja) {
		return cervejas.save(cerveja);
	}
	
	
}
