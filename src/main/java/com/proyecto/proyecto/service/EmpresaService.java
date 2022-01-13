/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto.service;

import com.proyecto.proyecto.entity.EmpresaEntity;
import com.proyecto.proyecto.helper.RandomHelper;
import com.proyecto.proyecto.repository.EmpresaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author a023325596p
 */
@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository oEmpresaRepository;

    private final String[] NOMBRE = {"Barco", "Blackpearl", "Digesto", "Alamont", "Exeter", "Zorro", "Jet", "Baza", "Bambu", "Coral", "Digi"};
    private final String[] LUGAR = {"Valencia", "Castellon", "Alicante", "Utiel", "Requena", "Denia"};

    public EmpresaEntity generateRandomEmpresa() {
        EmpresaEntity oEmpresaEntity = new EmpresaEntity();
        oEmpresaEntity.setNombre(generateNombre());
        oEmpresaEntity.setEmail(generateEmail(oEmpresaEntity.getNombre()));
        oEmpresaEntity.setDireccion(generateDireccion());
        oEmpresaEntity.setTelefono(generateTelefono(600000000, 999999999));

        return oEmpresaEntity;
    }

    private String generateNombre() {
        return NOMBRE[RandomHelper.getRandomInt(0, NOMBRE.length - 1)].toLowerCase();
    }

    private String generateDireccion() {
        return LUGAR[RandomHelper.getRandomInt(0, LUGAR.length - 1)].toLowerCase();
    }

    private String generateEmail(String nombre) {
        List<String> list = new ArrayList<>();
        list.add(nombre);
        return getFromList(list) + "@gmail.com";

    }

    private int generateTelefono(int minValue, int maxValue) {
        return Math.round(ThreadLocalRandom.current().nextInt(minValue, maxValue));
    }

    private String getFromList(List<String> list) {
        int randomNumber = RandomHelper.getRandomInt(0, list.size() - 1);
        String value = list.get(randomNumber);
        list.remove(randomNumber);
        return value;
    }

    public EmpresaEntity getRandomEmpresa() {
        EmpresaEntity oEmpresaEntity = null;
        int iPosicion = RandomHelper.getRandomInt(0, (int) oEmpresaRepository.count() - 1);
        Pageable oPageable = PageRequest.of(iPosicion, 1);
        Page<EmpresaEntity> empresaPage = oEmpresaRepository.findAll(oPageable);
        List<EmpresaEntity> empresaList = empresaPage.getContent();
        oEmpresaEntity = oEmpresaRepository.getById(empresaList.get(0).getId());
        return oEmpresaEntity;
    }

}
