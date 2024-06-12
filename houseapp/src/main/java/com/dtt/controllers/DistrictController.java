/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.District;
import com.dtt.services.DistrictService;
import com.dtt.services.ProvinceService;
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
public class DistrictController {

    @Autowired
    private DistrictService districtSvc;

    @Autowired
    private ProvinceService provinceSvc;

    @Autowired
    private Environment env;

    @GetMapping("/districts")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("provinces", this.provinceSvc.getProvinces());
        m.addAttribute("districts", this.districtSvc.getDistricts(params));
        int pageSize = Integer.parseInt(env.getProperty("districts.PAGE_SIZE").toString());
        int count = this.districtSvc.countDistrict();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "districts";
    }

    @GetMapping("/district")
    public String create(Model model) {
        model.addAttribute("district", new District());
        model.addAttribute("provinces", this.provinceSvc.getProvinces());
        return "district";
    }

    @PostMapping("/district")
    public String create(@ModelAttribute(value = "district") District d) {
        this.districtSvc.addOrUpdateDistrict(d);
        return "redirect:/districts";

    }

    @GetMapping("/districts/{districtId}")
    public String update(Model model, @PathVariable(value = "districtId") int id) {
        model.addAttribute("district", this.districtSvc.getDistrictById(id));
        model.addAttribute("provinces", this.provinceSvc.getProvinces());
        return "district";
    }
}
