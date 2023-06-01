package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.PagosEntity;


import java.util.List;

public interface IpagosService {
    public List<PagosEntity> findAll();

    public void save(PagosEntity personas);

    public void remove(Long id);

    public PagosEntity updatePagos(PagosEntity personas);


    public void changeState(Long id);

    public PagosEntity findOne(Long id);
}
