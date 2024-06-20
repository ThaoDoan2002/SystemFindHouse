/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.components.MailSevice;
import com.dtt.pojo.User;
import com.dtt.services.UserService;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author doant
 */
@Controller
@PropertySource("classpath:configs.properties")
public class UserController {

    @Autowired
    private UserService userSvc;

    @Autowired
    private Environment env;


    @GetMapping("/users")
    public String list(Model m, @RequestParam Map<String, String> params) {

        m.addAttribute("users", this.userSvc.getUsers(params));
        int pageSize = Integer.parseInt(env.getProperty("users.PAGE_SIZE").toString());
        int count = this.userSvc.countUser();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "users";
    }

    @GetMapping("/add/user")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/add/user")
    public String create(@ModelAttribute(value = "user") User u, @RequestParam(required = false, name = "currentAvatar") String currAv) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.userSvc.addOrUpdateUser(u, currAv);
        return "redirect:/users";

    }

    @GetMapping("/update/users/{userId}")
    public String update(Model model, @PathVariable(value = "userId") int id) {
        model.addAttribute("user", this.userSvc.getUserById(id));
        return "user";
    }

}
