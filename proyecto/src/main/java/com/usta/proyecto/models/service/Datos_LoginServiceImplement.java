package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.Datos_Login_Entity;
import com.usta.proyecto.entities.RolEntity;
import com.usta.proyecto.models.dao.Datos_LoginDAO;
import com.usta.proyecto.models.dao.RolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Datos_LoginServiceImplement implements Idatos_LoginService {


    @Autowired
    private Datos_LoginDAO datosLoginDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Datos_Login_Entity> findAll(){
        return (List<Datos_Login_Entity>) datosLoginDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Datos_Login_Entity datosLogin){
        datosLoginDAO.save(datosLogin);
    }
    @Override
    @Transactional
    public void remove(Long id){
        datosLoginDAO.deleteById(id);
    }
    @Override
    @Transactional
    public Datos_Login_Entity updateDatosLogin(Datos_Login_Entity datosLogin){
        return datosLoginDAO.save(datosLogin);
    }

    @Override
    @Transactional
    public void changeState(Long id){
        datosLoginDAO.changeState(id);}

    @Override
    @Transactional(readOnly = true)
    public Datos_Login_Entity findOne(Long id){
        return datosLoginDAO.findById(id).orElse(null);
    }



}
