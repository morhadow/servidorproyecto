/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
@Table(name = "tipousuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoUsuarioEntity implements Serializable {

    @Id
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "tipousuario")
    private List<UsuarioEntity> usuarios = new ArrayList<>();

    public TipoUsuarioEntity() {
    }

    public TipoUsuarioEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getUsuarios() {
        return usuarios.size();
    }

}
