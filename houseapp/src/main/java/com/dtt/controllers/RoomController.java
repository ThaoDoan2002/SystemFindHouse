/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Room;
import com.dtt.services.RoomService;
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
public class RoomController {

    @Autowired
    private Environment env;
    @Autowired
    private RoomService roomSvc;

    @GetMapping("/rooms")
    public String list(Model m, @RequestParam Map<String, String> params) {
        m.addAttribute("rooms", this.roomSvc.getRooms(params));
        m.addAttribute("users", this.roomSvc.getUsers());
        int pageSize = Integer.parseInt(env.getProperty("rooms.PAGE_SIZE").toString());
        int count = this.roomSvc.countRoom();
        m.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "rooms";
    }

    @GetMapping("/add/room")
    public String create(Model model) {
        model.addAttribute("users", this.roomSvc.getUsers());
        model.addAttribute("room", new Room());
        return "room";
    }

    @PostMapping("/add/room")
    public String create(@ModelAttribute(value = "room") Room r) {//bindingresult tat ca loi se duoc luu vao bien nay
        this.roomSvc.addOrUpdateRoom(r);
        return "redirect:/rooms";

    }

    @GetMapping("/update/rooms/{roomId}")
    public String update(Model model, @PathVariable(value = "roomId") int id) {
        model.addAttribute("users", this.roomSvc.getUsers());
        model.addAttribute("room", this.roomSvc.getRoomById(id));
        return "room";
    }
}
