/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Room;
import com.dtt.pojo.User;
import com.dtt.repositories.RoomRepository;
import com.dtt.services.RoomService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public List<Room> getRooms(Map<String, String> params) {
        return this.roomRepo.getRooms(params);
    }

    @Override
    public int countRoom() {
        return this.roomRepo.countRoom();
    }

    @Override
    public void addOrUpdateRoom(Room r) {
        this.roomRepo.addOrUpdateRoom(r);
    }

    @Override
    public Room getRoomById(int id) {
        return this.roomRepo.getRoomById(id);
    }

    @Override
    public void deleteRoom(int id) {
        this.roomRepo.deleteRoom(id);
    }

    @Override
    public List<User> getUsers() {
        return this.roomRepo.getUsers();
    }

    @Override
    public List<Room> getRooms() {
        return this.roomRepo.getRooms();
    }

}
