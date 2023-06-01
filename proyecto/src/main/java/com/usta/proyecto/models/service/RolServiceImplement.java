package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.RolEntity;
import com.usta.proyecto.models.dao.RolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RolServiceImplement implements IrolService{

    @Autowired
    private RolDAO rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<RolEntity> findAll(){
        return (List<RolEntity>) rolDao.findAll();
    }

    @Override
    @Transactional
    public void save(RolEntity rol){
        rolDao.save(rol);
    }
    @Override
    @Transactional
    public void remove(Long id){
        rolDao.deleteById(id);
    }
    @Override
    @Transactional
    public RolEntity updateRol(RolEntity rol){
        return rolDao.save(rol);
    }
    //no existe changeState por el momento
    @Override
    @Transactional(readOnly = true)
    public RolEntity findOne(Long id){
        return rolDao.findById(id).orElse(null);
    }


}
