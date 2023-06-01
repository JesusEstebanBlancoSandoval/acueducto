package com.usta.proyecto.models.dao;

import com.usta.proyecto.entities.RegistrosEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RegistrosDAO extends CrudRepository<RegistrosEntity,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE RegistrosEntity SET Estado = false WHERE id_registro =?1")
    public void changeState(Long id);

    @Transactional
    @Modifying
    @Query("SELECT reg FROM RegistrosEntity reg WHERE reg.id_registro NOT IN(SELECT pag.id_registro FROM PagosEntity pag)")
    public List<RegistrosEntity> selectOneReg();
}
