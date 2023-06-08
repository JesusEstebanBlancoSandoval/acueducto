package com.usta.proyecto.models.dao;

import com.usta.proyecto.entities.Datos_Login_Entity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface Datos_LoginDAO extends CrudRepository<Datos_Login_Entity,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Datos_Login_Entity SET estado = false WHERE id_persona = ?1")
    public void changeState(Long id);

    @Transactional
    @Query("SELECT datlog from Datos_Login_Entity datlog Where datlog.correo = ?1")
    public Datos_Login_Entity SelectCorreo(String correo);



}
