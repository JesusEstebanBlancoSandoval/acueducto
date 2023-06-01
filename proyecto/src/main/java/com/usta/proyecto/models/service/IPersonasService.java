package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.PersonasEntity;
import com.usta.proyecto.entities.RegistrosEntity;

import java.util.List;

public interface IPersonasService {
    public List<PersonasEntity> findAll();

    public void save(PersonasEntity personas);

    public void remove(Long id);

    public PersonasEntity updatePersonas(PersonasEntity personas);


    public void changeState(Long id);

    public PersonasEntity findOne(Long id);

    public List<PersonasEntity> selectOnePer();


}
