package com.usta.proyecto.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table (name = "Pagos")
public class PagosEntity implements Serializable {

    private static final long serialVersionUID=1L;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagos")
    private Long id_pagos;


    @NotNull
    @Size(min = 1,max = 4)
    @Column(name = "medicionActual")
    private String medicionActual;

    @NotNull
    @Column(name = "costoaPagar")
    private Integer costoaPagar;

    @NotNull
    @Size(min = 1,max = 4)
    @Column(name = "medicionPasada")
    private String medicionPasada;


    @NotNull
    @Column(name = "costoPasado")
    private Integer costoPasado;

    @Column(name = "deudaTotal")
    private String deudaTotal;

    @NotNull
    @Column(name = "Estado")
    private Boolean Estado;

    @JoinColumn(name = "id_registro")
    @OneToOne
    private RegistrosEntity id_registro;


    public Long getId_pagos() {
        return id_pagos;
    }

    public void setId_pagos(Long id_pagos) {
        this.id_pagos = id_pagos;
    }

    public String getMedicionActual() {
        return medicionActual;
    }

    public void setMedicionActual(String medicionActual) {
        this.medicionActual = medicionActual;
    }

    public Integer getCostoaPagar() {
        return costoaPagar;
    }

    public void setCostoaPagar(Integer costoaPagar) {
        this.costoaPagar = costoaPagar;
    }

    public String getMedicionPasada() {
        return medicionPasada;
    }

    public void setMedicionPasada(String medicionPasada) {
        this.medicionPasada = medicionPasada;
    }

    public Integer getCostoPasado() {
        return costoPasado;
    }

    public void setCostoPasado(Integer costoPasado) {
        this.costoPasado = costoPasado;
    }

    public String getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(String deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public RegistrosEntity getId_registro() {
        return id_registro;
    }

    public void setId_registro(RegistrosEntity id_registro) {
        this.id_registro = id_registro;
    }
}
