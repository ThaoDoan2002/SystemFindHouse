/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Province;
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
public class ProvinceController {

    @Autowired
    private Environment env;

    @Autowired
    private ProvinceService proSvc;

    @GetMapping("/provinces")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("provinces", this.proSvc.getProvinces(params));
        int pageSize = Integer.parseInt(env.getProperty("provinces.PAGE_SIZE").toString());
        int count = this.proSvc.countProvince();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "provinces";
    }

    @GetMapping("/province")
    public String create(Model model) {
        model.addAttribute("province", new Province());
        return "province";
    }

    @PostMapping("/province")
    public String create(@ModelAttribute(value = "province") Province p) {
        this.proSvc.addOrUpdateProvince(p);
        return "redirect:/provinces";

    }

    @GetMapping("/provinces/{provinceId}")
    public String update(Model model, @PathVariable(value = "provinceId") int id) {
        model.addAttribute("province", this.proSvc.getProvinceById(id));
        return "province";
    }
}
