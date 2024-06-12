/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Ward;
import com.dtt.services.WardsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author doant
 */
@RestController
public class ApiWardController {

    @Autowired
    WardsService districtSvc;

    @GetMapping(value = "api/wards/{wardId}")
    public ResponseEntity<List<Ward>> list(@PathVariable(value = "wardId") int id) {
        return new ResponseEntity<>(this.districtSvc.getWardsByDistrict(id), HttpStatus.OK);
    }
}
