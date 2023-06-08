package com.usta.proyecto.models.service;

import com.usta.proyecto.models.dao.PersonasDAO;
import com.usta.proyecto.entities.PersonasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonasServiceImplement implements IPersonasService{
    @Autowired
    private PersonasDAO PersonasDAO;


    @Override
    @Transactional(readOnly = true)
    public List<PersonasEntity> findAll(){
        return (List<PersonasEntity>) PersonasDAO.findAll();
    }


    @Override
    @Transactional
    public void save(PersonasEntity personas){
        PersonasDAO.save(personas);

    }


    @Override
    @Transactional
    public void remove(Long id){
        PersonasDAO.deleteById(id);
    }

    @Override
    @Transactional
    public PersonasEntity updatePersonas(PersonasEntity personas){
        return PersonasDAO.save(personas);
    }

    @Override
    @Transactional
    public void changeState(Long id){
        PersonasDAO.changeState(id);
    }
    @Override
    @Transactional(readOnly = true)
    public PersonasEntity findOne(Long id){
        return PersonasDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonasEntity> selectOnePer(){
        return (List<PersonasEntity>) PersonasDAO.selectOnePer();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonasEntity findLastCorreo(){
        return PersonasDAO.findLastCorreo();

    }

}
