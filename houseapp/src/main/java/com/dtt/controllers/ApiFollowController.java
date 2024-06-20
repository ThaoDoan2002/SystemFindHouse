/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.User;
import com.dtt.services.FollowService;
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
public class ApiFollowController {

    @Autowired
    private FollowService followSvc;

    @DeleteMapping("/delete/follows/{flId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "flId") int id) {
        this.followSvc.deleteFollow(id);
    }

    @GetMapping("/follows/users/{userId}")
    public ResponseEntity<List<User>> listLandlords(@PathVariable(value = "userId") int id) {
        System.out.println(id);
        System.out.println(this.followSvc.getLandlords(id));
        return new ResponseEntity<>(this.followSvc.getLandlords(id), HttpStatus.OK);
    }

}
