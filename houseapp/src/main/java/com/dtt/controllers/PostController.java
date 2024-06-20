/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Post;
import com.dtt.services.PostService;
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
public class PostController {

    @Autowired
    private PostService postSvc;
    @Autowired
    private UserService userSvc;

    @Autowired
    private Environment env;

    @GetMapping("/posts")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("users", this.userSvc.getUsers());
        m.addAttribute("posts", this.postSvc.getPosts(params));
        int pageSize = Integer.parseInt(env.getProperty("posts.PAGE_SIZE").toString());
        int count = this.postSvc.countPost();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "posts";
    }

    @GetMapping("/add/post")
    public String create(Model model) {
        model.addAttribute("users", this.userSvc.getUsers());
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("/add/post")
    public String create(@ModelAttribute(value = "post") Post p) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.postSvc.addOrUpdatePost(p);
        return "redirect:/posts";

    }

    @GetMapping("/update/posts/{postId}")
    public String update(Model model, @PathVariable(value = "postId") int id) {
        model.addAttribute("users", this.userSvc.getUsers());
        model.addAttribute("post", this.postSvc.getPostById(id));
        return "post";
    }
}
