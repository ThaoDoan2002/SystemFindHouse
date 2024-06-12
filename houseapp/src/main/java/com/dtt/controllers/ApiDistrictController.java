/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.District;
import com.dtt.services.DistrictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author doant
 */
@RestController
public class ApiDistrictController {

    @Autowired
    DistrictService districtSvc;

    @GetMapping(value = "api/districts/{districtId}")
    public ResponseEntity<List<District>> list(@PathVariable(value = "districtId") int id) {
        return new ResponseEntity<>(this.districtSvc.getDistrictsByProvince(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/districts/{districtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "districtId") int id) {
        this.districtSvc.deleteDistrict(id);
    }

}
