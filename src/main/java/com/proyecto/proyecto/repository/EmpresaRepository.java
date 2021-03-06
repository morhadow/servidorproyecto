/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.repository;

import com.proyecto.proyecto.entity.EmpresaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author javav
 */
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    public Page<EmpresaEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

    public Page<EmpresaEntity> findById(Long longEmpresa, Pageable oPageable);

    public Page<EmpresaEntity> findByIdAndNombreIgnoreCaseContaining(Long longEmpresa, String strFilter, Pageable oPageable);

    
}
