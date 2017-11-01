package com.azin.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azin.brewer.model.Cerveja;
import com.azin.brewer.repository.helpers.CervejasQueries;

public interface Cervejas extends JpaRepository<Cerveja, Long> , CervejasQueries {

}
