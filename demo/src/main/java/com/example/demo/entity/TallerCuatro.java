package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "taller_cuatro")
public class TallerCuatro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taller_cuatro_seq")
    @SequenceGenerator(name = "taller_cuatro_seq", sequenceName = "Taller_Cuatro_SEQ", allocationSize = 1)
    @Column(name = "id_taller_cuatro")
    private Long idTallerCuatro;

    @Column(name = "usuario", length = 55, nullable = false)
    private String usuario;

    @Column(name = "contrasena", length = 55, nullable = false)
    private String contrasena;

    // Getters y setters
    public Long getIdTallerCuatro() {
        return idTallerCuatro;
    }

    public void setIdTallerCuatro(Long idTallerCuatro) {
        this.idTallerCuatro = idTallerCuatro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
