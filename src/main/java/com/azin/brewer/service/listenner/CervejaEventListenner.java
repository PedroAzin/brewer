package com.azin.brewer.service.listenner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.azin.brewer.service.event.CervejaSalvaEvent;
import com.azin.brewer.storage.FotoStorage;

@Component
public class CervejaEventListenner {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition="#evento.temFoto()")
	public void cervejaSalva(CervejaSalvaEvent evento) {
		fotoStorage.salvar(evento.getCerveja().getFoto());
	}
	
	
}
