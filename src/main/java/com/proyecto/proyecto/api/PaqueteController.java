/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.api;

import com.proyecto.proyecto.entity.PaqueteEntity;
import com.proyecto.proyecto.entity.UsuarioEntity;
import com.proyecto.proyecto.repository.EmpresaRepository;
import com.proyecto.proyecto.repository.PaqueteRepository;
import com.proyecto.proyecto.repository.UsuarioRepository;
import com.proyecto.proyecto.service.PaqueteService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author javav
 */
public class PaqueteController {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    PaqueteRepository oPaqueteRepository;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    EmpresaRepository oEmpresaRepository;

    @Autowired
    PaqueteService oPaqueteService;

    /*@GetMapping("/{id}")
    public ResponseEntity<PaqueteEntity> view(@PathVariable(value = "id") Long id) {
        PaqueteEntity oPaqueteEntity = oPaqueteRepository.getById(id);
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else if (oUsuarioEntity.getTipousuario().getId() == 1) {
            if (oPaqueteRepository.existsById(id)) {
                return new ResponseEntity<PaqueteEntity>(oPaqueteRepository.getById(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else if (oPaqueteRepository.existsById(id) && oUsuarioEntity.getId() == oPaqueteEntity.getEmpresa().getId()) {

            return new ResponseEntity<PaqueteEntity>(oPaqueteRepository.findByPaqueteIdUsuarioView(oUsuarioEntity.getId()), oPaqueteRepository.getById(id).getId(), HttpStatus.OK);

        } else if (oPaqueteRepository.existsById(id) && oUsuarioEntity.getId() != oPaqueteEntity.getEmpresa().getId()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }*/

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        PaqueteEntity oPaqueteEntity = null;
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else if (oUsuarioEntity.getTipousuario().getId() == 1) {

            return new ResponseEntity<Long>(oPaqueteRepository.count(), HttpStatus.OK);

        } else {

            return new ResponseEntity<Long>(oPaqueteRepository.findByPaqueteIdUsuarioCount(oUsuarioEntity.getId()), HttpStatus.OK);
        }

    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PaqueteEntity oPaqueteEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                oPaqueteEntity.setId(null);
                return new ResponseEntity<PaqueteEntity>(oPaqueteRepository.save(oPaqueteEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody PaqueteEntity oPaqueteEntity) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {
                if (oPaqueteRepository.existsById(oPaqueteEntity.getId())) {
                    return new ResponseEntity<PaqueteEntity>(oPaqueteRepository.save(oPaqueteEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oUsuarioEntity.getTipousuario().getId() == 1) {

                if (oPaqueteRepository.existsById(id)) {
                    oPaqueteRepository.deleteById(id);
                    if (oPaqueteRepository.existsById(id)) {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    } else {
                        return new ResponseEntity<Long>(id, HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

}
