package com.usta.proyecto.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="roles")
public class RolEntity {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id_rol;

    @NotNull
    @Size(min = 1,max = 30)
    @Column(name = "nombre_rol")
    private String nombre_rol;

    @NotNull
    @JoinColumn(name = "IdUsuario", referencedColumnName = "id_datos_login")
    @ManyToOne(fetch = FetchType.LAZY)
    private Datos_Login_Entity id_datos_login;

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public Datos_Login_Entity getId_datos_login() {
        return id_datos_login;
    }

    public void setId_datos_login(Datos_Login_Entity id_datos_login) {
        this.id_datos_login = id_datos_login;
    }
}
