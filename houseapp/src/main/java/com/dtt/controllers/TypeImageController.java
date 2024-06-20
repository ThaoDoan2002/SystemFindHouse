/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Typeimage;
import com.dtt.services.TypeImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author doant
 */
@Controller
public class TypeImageController {

    @Autowired
    TypeImageService typeImgSvc;

    @GetMapping("/type-images")
    public String list(Model m) {
        m.addAttribute("typeImages", this.typeImgSvc.getTypeImages());
        return "typeImages";
    }

    @GetMapping("/add/type-image")
    public String create(Model model) {
        model.addAttribute("typeImage", new Typeimage());
        return "typeImage";
    }

    @PostMapping("/add/type-image")
    public String create(@ModelAttribute(value = "typeImage") Typeimage t) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.typeImgSvc.addOrUpdateTypeImage(t);
        return "redirect:/type-images";

    }

    @GetMapping("/update/type-images/{typeImageId}")
    public String update(Model model, @PathVariable(value = "typeImageId") int id) {
        model.addAttribute("typeImage", this.typeImgSvc.getTypeImageById(id));
        return "typeImage";
    }

}
