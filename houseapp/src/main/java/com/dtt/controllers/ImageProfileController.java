/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.User;
import com.dtt.services.ImageProfileService;
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
public class ImageProfileController {

    @Autowired
    private ImageProfileService imgSvc;

    @Autowired
    private Environment env;

    @GetMapping("/image-profiles")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("users", this.imgSvc.listUsers());
        m.addAttribute("types", this.imgSvc.listTypes());
        m.addAttribute("imgs", this.imgSvc.getImageProfiles(params));
        int pageSize = Integer.parseInt(env.getProperty("imageProfile.PAGE_SIZE").toString());
        int count = this.imgSvc.countImageProfile();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "imageProfiles";
    }

    @GetMapping("/add/image-profile")
    public String create(Model model) {
        model.addAttribute("img", new Imageprofile());
        model.addAttribute("users", this.imgSvc.listUsers());
        model.addAttribute("types", this.imgSvc.listTypes());
        return "imageProfile";
    }

    @PostMapping("/add/image-profile")
    public String create(@ModelAttribute(value = "img") Imageprofile img, @RequestParam(required = false, name = "currImg") String currImg) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.imgSvc.addOrUpdateImageProfile(img, currImg);
        return "redirect:/image-profiles";

    }

    @GetMapping("/image-profiles/{imgId}")
    public String update(Model model, @PathVariable(value = "imgId") int id) {
        model.addAttribute("img", this.imgSvc.getImageProfileById(id));
        model.addAttribute("users", this.imgSvc.listUsers());
        model.addAttribute("types", this.imgSvc.listTypes());
        return "imageProfile";
    }

}
