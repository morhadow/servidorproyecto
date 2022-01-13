/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.repository;

import com.proyecto.proyecto.entity.PaqueteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author javav
 */
public interface PaqueteRepository extends JpaRepository<PaqueteEntity, Long> {
    
        
            Long findByPaqueteIdUsuarioCount(Long id_usuario);
            
                PaqueteEntity findByPaqueteIdUsuarioView(Long id_usuario, Long id_paquete);



}
