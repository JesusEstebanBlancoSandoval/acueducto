package com.usta.proyecto.models.dao;

import com.usta.proyecto.entities.PagosEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PagosDAO extends CrudRepository<PagosEntity,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE PagosEntity SET Estado =FALSE WHERE id_pagos=?1")
      public void changeState(Long id);


}
