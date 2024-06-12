/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author doant
 */
@RestController
public class ApiProvinceController {
    @Autowired
    private ProvinceService provinceSvc;

    @DeleteMapping("/api/provinces/{provinceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "provinceId") int id) {
        this.provinceSvc.deleteProvince(id);
    }
}
