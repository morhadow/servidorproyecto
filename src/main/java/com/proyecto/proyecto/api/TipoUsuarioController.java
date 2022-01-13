/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.api;

import com.proyecto.proyecto.bean.UsuarioBean;
import com.proyecto.proyecto.entity.TipoUsuarioEntity;
import com.proyecto.proyecto.entity.UsuarioEntity;
import com.proyecto.proyecto.helper.TipoUsuarioHelper;
import com.proyecto.proyecto.repository.TipoUsuarioRepository;
import com.proyecto.proyecto.repository.UsuarioRepository;
import com.proyecto.proyecto.service.TipoUsuarioService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javav
 */
@RestController
@RequestMapping("/tipousuario")
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioRepository oTipoUsuarioRepository;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipoUsuarioService oUserTypeService;

    @Autowired
    HttpSession oHttpSession;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        if (id == null || !(oTipoUsuarioRepository.existsById(id))) {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TipoUsuarioEntity>(oTipoUsuarioRepository.getById(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<Long>(oTipoUsuarioRepository.count(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TipoUsuarioEntity oTipoUsuarioEntity) {
        if (oHttpSession.getAttribute("usuario") == null || ((UsuarioEntity) oHttpSession.getAttribute("usuario"))
                .getTipousuario().getId() != TipoUsuarioHelper.ADMIN) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        }

        if (oTipoUsuarioEntity.getId() == null || !oTipoUsuarioRepository.existsById(oTipoUsuarioEntity.getId())) {
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oTipoUsuarioRepository.save(oTipoUsuarioEntity), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate() {
        List<TipoUsuarioEntity> usersTypeList = oUserTypeService.generateUsersType();

        if (oHttpSession.getAttribute("usuario") == null || ((UsuarioEntity) oHttpSession.getAttribute("usuario"))
                .getTipousuario().getId() != TipoUsuarioHelper.ADMIN) {
            return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
        }

        for (int i = usersTypeList.size() - 1; i >= 0; i--) {
            if (!oTipoUsuarioRepository.existsById(usersTypeList.get(i).getId())) {
                oTipoUsuarioRepository.save(usersTypeList.get(i));
            } else {
                usersTypeList.remove(i);
            }
        }

        return new ResponseEntity<>(oTipoUsuarioRepository.count(), HttpStatus.OK);
    }

}
