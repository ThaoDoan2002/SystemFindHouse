/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Follow;
import com.dtt.services.FollowService;
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
public class FollowController {

    @Autowired
    private FollowService followSvc;

    @Autowired
    private Environment env;

    @GetMapping("/follows")
    public String list(Model m, @RequestParam Map<String, String> params) {

        m.addAttribute("follows", this.followSvc.getFollows(params));

        int pageSize = Integer.parseInt(env.getProperty("posts.PAGE_SIZE").toString());
        int count = this.followSvc.countFollow();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "follows";
    }

    @GetMapping("/add/follow")
    public String create(Model model) {
        model.addAttribute("users", this.followSvc.getFollowers());
        model.addAttribute("follow", new Follow());

        return "follow";
    }

    @PostMapping("/add/follow")
    public String create(@ModelAttribute(value = "follow") Follow fl) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.followSvc.addOrUpdateFollow(fl);

        return "redirect:/follows";

    }

    @GetMapping("/update/follows/{flId}")
    public String update(Model model, @PathVariable(value = "flId") int id) {
        model.addAttribute("users", this.followSvc.getFollowers());
        model.addAttribute("follow", this.followSvc.getFollowById(id));
        return "follow";
    }
}
