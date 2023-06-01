package com.usta.proyecto.models.service;

import com.usta.proyecto.models.dao.RegistrosDAO;
import com.usta.proyecto.entities.RegistrosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrosServiceImplement implements IRegistrosService{
    @Autowired
    private RegistrosDAO RegistrosDAO;


    @Override
    @Transactional(readOnly = true)
    public List<RegistrosEntity> findAll(){
        return (List<RegistrosEntity>) RegistrosDAO.findAll();
    }


    @Override
    @Transactional
    public void save(RegistrosEntity registros){
        RegistrosDAO.save(registros);

    }

    @Override
    @Transactional
    public void remove(Long id){
        RegistrosDAO.deleteById(id);
    }

    @Override
    @Transactional
    public RegistrosEntity updateRegistro(RegistrosEntity registros){
        return RegistrosDAO.save(registros);
    }

    @Override
    @Transactional
    public void changeState(Long id){
        RegistrosDAO.changeState(id);
    }
    @Override
    @Transactional(readOnly = true)
    public RegistrosEntity findOne(Long id){
        return RegistrosDAO.findById(id).orElse(null);
    }


}
