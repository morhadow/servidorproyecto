/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.repository;

import com.proyecto.proyecto.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author javav
 */
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByLoginAndPassword(String login, String password);

    Page<UsuarioEntity> findByTipousuarioIdAndNombreIgnoreCaseContainingOrDniIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(
            Long filtertype, String nombre, String dni, String apellido1, String apellido2, Pageable oPageable);

    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrDniIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(
            String nombre, String dni, String apellido1, String apellido2, Pageable oPageable);
}
