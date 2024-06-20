/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Comment;
import com.dtt.services.CommentService;
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
public class CommentController {

    @Autowired
    private CommentService cmtSvc;
    @Autowired
    private UserService userSvc;


    @Autowired
    private Environment env;

    @GetMapping("/comments")
    public String list(Model m, @RequestParam Map<String, String> params) {

        m.addAttribute("cmts", this.cmtSvc.getComments(params));

        int pageSize = Integer.parseInt(env.getProperty("posts.PAGE_SIZE").toString());
        int count = this.cmtSvc.countComment();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "comments";
    }

    @GetMapping("/add/comment")
    public String create(Model model) {

        model.addAttribute("users", this.userSvc.getUsers());
        model.addAttribute("cmt", new Comment());
        return "comment";
    }

    @PostMapping("/add/comment")
    public String create(@ModelAttribute(value = "cmt") Comment cmt) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.cmtSvc.addOrUpdateComment(cmt);
        return "redirect:/comments";

    }

    @GetMapping("/update/comments/{cmtId}")
    public String update(Model model, @PathVariable(value = "cmtId") int id) {
        model.addAttribute("users", this.userSvc.getUsers());

        model.addAttribute("cmt", this.cmtSvc.getCommentById(id));
        return "comment";
    }
}
