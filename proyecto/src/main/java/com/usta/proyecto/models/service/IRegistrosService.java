package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.RegistrosEntity;


import java.util.List;

public interface IRegistrosService {
    public List<RegistrosEntity> findAll();

    public void save(RegistrosEntity registros);

    public void remove(Long id);

    public RegistrosEntity updateRegistro(RegistrosEntity registros);


    public void changeState(Long id);

    public RegistrosEntity findOne(Long id);

}
