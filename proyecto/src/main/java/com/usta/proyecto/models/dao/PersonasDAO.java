package com.usta.proyecto.models.dao;

import com.usta.proyecto.entities.PersonasEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonasDAO extends CrudRepository<PersonasEntity,Long> {


      @Transactional
      @Modifying
      @Query("UPDATE PersonasEntity SET Estado = false WHERE id_persona =?1")
       public void changeState(Long id);



      @Transactional
      @Modifying
      @Query("SELECT cli FROM PersonasEntity cli WHERE cli.id_persona NOT IN (SELECT reg.id_persona FROM RegistrosEntity reg)")
      public List<PersonasEntity> selectOnePer();
}
