/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Tenantpost;
import com.dtt.services.TenantPostService;
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
public class TenantPostController {

    @Autowired
    private Environment env;
    @Autowired
    private TenantPostService tpostSvc;

    @GetMapping("/tenant-posts")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("posts", this.tpostSvc.getPosts());
        m.addAttribute("tposts", this.tpostSvc.getTenantPosts(params));
        int pageSize = Integer.parseInt(env.getProperty("teantPosts.PAGE_SIZE").toString());
        int count = this.tpostSvc.countTenantPost();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "tenantPosts";
    }

    @GetMapping("/add/tenant-post")
    public String create(Model model) {
        model.addAttribute("posts", this.tpostSvc.getPosts());
        model.addAttribute("tpost", new Tenantpost());
        return "tenantPost";
    }

    @PostMapping("/add/tenant-post")
    public String create(@ModelAttribute(value = "tpost") Tenantpost p) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.tpostSvc.addOrUpdateTenantPost(p);
        return "redirect:/tenant-posts";

    }

    @GetMapping("/update/tenant-posts/{postId}")
    public String update(Model model, @PathVariable(value = "postId") int id) {
        model.addAttribute("posts", this.tpostSvc.getPosts());
        model.addAttribute("tpost", this.tpostSvc.getTenantPostById(id));
        return "tenantPost";
    }
}
