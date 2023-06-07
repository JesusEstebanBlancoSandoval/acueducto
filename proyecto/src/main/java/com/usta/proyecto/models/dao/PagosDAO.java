package com.usta.proyecto.models.dao;

import com.usta.proyecto.entities.PagosEntity;
import com.usta.proyecto.entities.PersonasEntity;
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


    @Transactional
    @Modifying
    @Query("SELECT pag FROM PagosEntity pag WHERE pag.id_pagos NOT IN (SELECT reg.id_registro FROM RegistrosEntity reg)")
    public List<PagosEntity> selectOnePag();


}
