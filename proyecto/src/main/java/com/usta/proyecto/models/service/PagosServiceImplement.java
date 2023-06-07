package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.PagosEntity;
import com.usta.proyecto.models.dao.PagosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagosServiceImplement implements IpagosService {
    @Autowired
    private PagosDAO pagosDAO;


    @Override
    @Transactional(readOnly = true)
    public List<PagosEntity> findAll(){
        return (List<PagosEntity>) pagosDAO.findAll();
    }


    @Override
    @Transactional
    public void save(PagosEntity detalles){
        pagosDAO.save(detalles);
    }


    @Override
    @Transactional
    public void remove(Long id){
        pagosDAO.deleteById(id);
    }

    @Override
    @Transactional
    public PagosEntity updatePagos(PagosEntity detalles){
        return pagosDAO.save(detalles);
    }

    @Override
    @Transactional
    public void changeState(Long id){
        pagosDAO.changeState(id);
    }
    @Override
    @Transactional(readOnly = true)
    public PagosEntity findOne(Long id){
        return pagosDAO.findById(id).orElse(null);
}

    @Override
    @Transactional(readOnly = true)
    public List<PagosEntity>selectOnePag(){
        return (List<PagosEntity>)pagosDAO.selectOnePag();
    }


}
