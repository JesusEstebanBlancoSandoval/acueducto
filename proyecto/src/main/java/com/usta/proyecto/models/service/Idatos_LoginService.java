package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.Datos_Login_Entity;
import com.usta.proyecto.entities.RolEntity;

import java.util.List;

public interface Idatos_LoginService {

    public List<Datos_Login_Entity> findAll();

    public void save(Datos_Login_Entity datosLogin);

    public void remove(Long id);

    public void changeState(Long id);

    public Datos_Login_Entity updateDatosLogin(Datos_Login_Entity datosLogin);


    public Datos_Login_Entity findOne(Long id);
}
