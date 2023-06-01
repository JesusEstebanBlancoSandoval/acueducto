package com.usta.proyecto.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "datos_login")
public class Datos_Login_Entity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_login")
    private Long id_datos_login;

    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "correo", unique = true)
    private String correo;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "id_persona")
    @OneToOne
    private PersonasEntity id_persona;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdUsuario", referencedColumnName = "id_datos_login")
    private List<RolEntity> authorities;

    /********************* METODOS *******************/

    /**
     * Constructor
     **/
    public Datos_Login_Entity() {
        this.authorities = new ArrayList<RolEntity>();
    }



    /**
     * 1. Metodo añadir Autoridad
     **/
    public void addAuthority(RolEntity authority) {
        this.authorities.add(authority);
    }


    public Long getId_datos_login() {
        return id_datos_login;
    }

    public void setId_datos_login(Long id_datos_login) {
        this.id_datos_login = id_datos_login;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public PersonasEntity getId_persona() {
        return id_persona;
    }

    public void setId_persona(PersonasEntity id_persona) {
        this.id_persona = id_persona;
    }

    public List<RolEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RolEntity> authorities) {
        this.authorities = authorities;
    }
}
