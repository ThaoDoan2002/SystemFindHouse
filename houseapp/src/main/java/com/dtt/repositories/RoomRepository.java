/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Room;
import com.dtt.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface RoomRepository {
     List<Room> getRooms(Map<String, String> params);

    int countRoom();

    void addOrUpdateRoom(Room r);

    Room getRoomById(int id);

    void deleteRoom(int id);
    
    List<User> getUsers();
    
    List<Room> getRooms();

}
