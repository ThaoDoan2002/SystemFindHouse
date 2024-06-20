/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Landlordpost;
import com.dtt.services.LandlordPostService;
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
public class LandlordPostController {

    @Autowired
    private Environment env;

    @Autowired
    private LandlordPostService lpostSvc;

    @GetMapping("/landlord-posts")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("lposts", this.lpostSvc.getLandlordPosts(params));
        int pageSize = Integer.parseInt(env.getProperty("teantPosts.PAGE_SIZE").toString());
        int count = this.lpostSvc.countLandlordPost();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "landlordPosts";
    }

    @GetMapping("/add/landlord-post")
    public String create(Model model) {
        model.addAttribute("posts", this.lpostSvc.getPosts());
        model.addAttribute("lpost", new Landlordpost());
        return "landlordPost";
    }

    @PostMapping("/add/landlord-post")
    public String create(@ModelAttribute(value = "lpost") Landlordpost p) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.lpostSvc.addOrUpdateLandlordPost(p);
        System.out.println(p.getId());
        System.out.println("sleep");
        return "redirect:/landlord-posts";

    }

    @GetMapping("/update/landlord-posts/{postId}")
    public String update(Model model, @PathVariable(value = "postId") int id) {
        model.addAttribute("posts", this.lpostSvc.getPosts());
        model.addAttribute("lpost", this.lpostSvc.getLandlordPostById(id));
        return "landlordPost";
    }

}
