package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.RolEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IrolService {

    public List<RolEntity> findAll();

    public void save(RolEntity rol);

    public void remove(Long id);

    public RolEntity updateRol(RolEntity rol);


    public RolEntity findOne(Long id);
}
