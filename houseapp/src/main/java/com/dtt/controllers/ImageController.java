/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Image;
import com.dtt.services.ImageService;
import com.dtt.services.RoomService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class ImageController {

    @Autowired
    private ImageService imgSvc;

    @Autowired
    private Environment env;

    @Autowired
    private RoomService roomSv;

    @GetMapping("/room-images")
    public String list(Model m, @RequestParam Map<String, String> params) {

        m.addAttribute("imgs", this.imgSvc.getImages(params));
        int pageSize = Integer.parseInt(env.getProperty("imageProfile.PAGE_SIZE").toString());
        int count = this.imgSvc.countImage();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "roomImages";
    }

    @GetMapping("/add/room-image")
    public String create(Model model) {
        model.addAttribute("img", new Image());
        model.addAttribute("rooms", this.roomSv.getRooms());
        System.out.println( this.roomSv.getRooms());
        return "roomImage";  // Tên của view template
    }

    @PostMapping("/add/room-image")
    public String create(@ModelAttribute("img") Image img, BindingResult result, @RequestParam(required = false, name = "currImg") String currImg, Model model) {
        this.imgSvc.addOrUpdateImage(img, currImg);
        return "redirect:/room-images";  // Sửa lại đường dẫn redirect
    }

    @GetMapping("/update/room-images/{imgId}")
    public String update(Model model, @PathVariable(value = "imgId") int id) {
        model.addAttribute("img", this.imgSvc.getImageById(id));
        model.addAttribute("rooms", this.roomSv.getRooms());
        return "roomImage";
    }
}
