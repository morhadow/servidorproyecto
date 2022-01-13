/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.service;

import com.proyecto.proyecto.entity.TipoUsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author a023325596p
 */
@Service
public class TipoUsuarioService {

    public List<TipoUsuarioEntity> generateUsersType() {
        List<TipoUsuarioEntity> usersTypeList = new ArrayList<>();
        usersTypeList.add(new TipoUsuarioEntity(1L, "administrador"));
        usersTypeList.add(new TipoUsuarioEntity(2L, "usuario"));

        return usersTypeList;
    }
}
