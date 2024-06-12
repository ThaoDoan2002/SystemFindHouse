/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.services.LandlordService;
import com.dtt.services.UserService;
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
public class ApiLandlordController {

    @Autowired
    private LandlordService landlordSvc;

    @DeleteMapping("/api/landlords/{landlordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "landlordId") int id) {
        this.landlordSvc.deleteLandlord(id);
    }
}