package com.azin.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.azin.brewer.model.Cerveja;
import com.azin.brewer.repository.Cervejas;
import com.azin.brewer.service.event.CervejaSalvaEvent;

@Service
public class CervejaService {
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private ApplicationEventPublisher publisher;


	public void save(Cerveja cerveja) {
		 cervejas.save(cerveja);
		 
		 publisher.publishEvent(new CervejaSalvaEvent(cerveja));
		
	}
	
	
}
