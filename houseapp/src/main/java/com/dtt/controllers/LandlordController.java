/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;

import com.dtt.services.LandlordService;

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
public class LandlordController {

    @Autowired
    private Environment env;
    @Autowired
    private LandlordService landlordSvc;


    @GetMapping("/landlords")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("landlords", this.landlordSvc.getLandlords(params));

        int pageSize = Integer.parseInt(env.getProperty("landlords.PAGE_SIZE").toString());
        int count = this.landlordSvc.countLandlord();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "landlords";
    }

    @GetMapping("/add/landlord")
    public String create(Model model) {
        model.addAttribute("landlord", new Landlord());
//        model.addAttribute("provinces", this.provinceSvc.getProvinces());
        model.addAttribute("users", this.landlordSvc.getUsers());
        return "landlord";
    }

    @PostMapping("/add/landlord")
    public String create(Model model, @ModelAttribute(value = "landlord") Landlord l) {
        this.landlordSvc.addOrUpdateLandlord(l);

        return "redirect:/landlords";
    }

    @GetMapping("/update/landlords/{landlordId}")
    public String update(Model model, @PathVariable(value = "landlordId") int id) {
        Landlord lan = this.landlordSvc.getLandlordById(id);
//        model.addAttribute("provinces", this.provinceSvc.getProvinces());
        if (lan != null) {
            model.addAttribute("landlord", lan);
//            if (lan.getProvinceId() != null && lan.getDistrictId() != null) {
//                model.addAttribute("districts", this.districtSvc.getDistrictsByProvince(lan.getProvinceId().getId()));
//                System.out.println((this.districtSvc.getDistrictsByProvince(lan.getProvinceId().getId()).toString()));
//                model.addAttribute("wards", this.wardsSvc.getWardsByDistrict(lan.getDistrictId().getId()));
//            }

        } else {
            System.out.println("Null");
        }
        return "landlord";
    }
}
