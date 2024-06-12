/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;
import com.dtt.repositories.UserRepository;
import com.dtt.services.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return this.userRepo.getUsers(params);
    }

    @Override
    public int countUser() {
        return this.userRepo.countUser();
    }

    @Override
    public void addOrUpdateUser(User u, String currAv) {

        if (u.getId() == null && u.getFile().isEmpty()) {
            u.setAvatar("https://res.cloudinary.com/dzrgeifj0/image/upload/v1711094956/lt2_uhupqc.jpg");
        } else if (u.getId() != null && u.getFile().isEmpty()) {
            u.setAvatar(currAv);
        }

        if (u.getId() == null) {
            if (u.getRole().equals("LANDLORD")) {
                u.setIsActive(false);
            } else {
                u.setIsActive(true);
            }
        }

        if (u.getId() != null) {
            User existU = userRepo.getUserById(u.getId());
            if (!u.getPassword().equals(existU.getPassword())) {
                String hashedPassword = passwordEncoder.encode(u.getPassword());
                u.setPassword(hashedPassword);
            }
            if (!u.getRole().equals(existU.getRole())) {
                if (u.getRole().equals("LANDLORD")) {
                    u.setIsActive(false);
                }
            }

        }

        // Băm mật khẩu
        if (u.getId() == null) {
            String hashedPassword = passwordEncoder.encode(u.getPassword());
            u.setPassword(hashedPassword);
        }

        if (!u.getFile().isEmpty() && u.getId() == null || u.getId() != null && !u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.userRepo.addOrUpdateUser(u);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        this.userRepo.deleteUser(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);

    }
//
//    @Override
//    public List<Landlord> getLandlords() {
//        return this.userRepo.getLandlords();
//    }

}
