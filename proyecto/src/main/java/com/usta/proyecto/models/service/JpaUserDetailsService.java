package com.usta.proyecto.models.service;

import com.usta.proyecto.entities.Datos_Login_Entity;
import com.usta.proyecto.entities.RolEntity;
import com.usta.proyecto.models.dao.Datos_LoginDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("JpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private Datos_LoginDAO datos_loginDao;

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Datos_Login_Entity usuario = datos_loginDao.SelectCorreo(username);

        if(usuario==null){
            logger.error("Error login: No existe el usuario '"+username+"'");
            throw new UsernameNotFoundException("Username "+username+" no existe en el sistema");
        }

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        for (RolEntity authority: usuario.getAuthorities()){
            logger.info("Rol: ".concat(authority.getNombre_rol()));
            authorityList.add(new SimpleGrantedAuthority(authority.getNombre_rol()));
        }
        if(authorityList.isEmpty()){
            logger.error("Error login: Usuario '"+username+"' No tiene roles asignados");
            throw new UsernameNotFoundException("Error login: Usuario '"+username+"' No tiene roles asignados");
        }
        return new User(usuario.getCorreo(),usuario.getPassword(),usuario.getEstado(),true,true,true,authorityList);
    }

}
