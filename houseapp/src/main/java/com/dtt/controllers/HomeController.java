/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.repositories.UserRepository;
import com.dtt.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author doant
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userSvc;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute
    public void commonAttr(Model model, Principal p) {
        if (p != null) {
            model.addAttribute("userLogin", this.userSvc.getUserByUsername(p.getName()));
        }

    }
}
