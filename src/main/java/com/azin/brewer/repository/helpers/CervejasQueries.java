package com.azin.brewer.repository.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.azin.brewer.model.Cerveja;
import com.azin.brewer.repository.filters.CervejaFilter;

public interface CervejasQueries {

	Page<Cerveja> listarPor(CervejaFilter filter,Pageable pageable); 
	
}
