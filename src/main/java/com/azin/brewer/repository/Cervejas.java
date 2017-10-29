package com.azin.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azin.brewer.model.Cerveja;

public interface Cervejas extends JpaRepository<Cerveja, Long> {

}
