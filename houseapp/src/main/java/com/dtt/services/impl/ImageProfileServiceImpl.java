/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import com.dtt.pojo.User;
import com.dtt.repositories.ImageProfileRepository;
import com.dtt.services.ImageProfileService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class ImageProfileServiceImpl implements ImageProfileService {

    @Autowired
    private ImageProfileRepository imgRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Imageprofile> getImageProfiles(Map<String, String> params) {
        return this.imgRepo.getImageProfiles(params);
    }

    @Override
    public int countImageProfile() {
        return this.imgRepo.countImageProfile();
    }

    @Override
    public void addOrUpdateImageProfile(Imageprofile img, String curImg) {
        if (img.getId() == null && img.getFile().isEmpty()) {
            img.setImage("https://res.cloudinary.com/dzrgeifj0/image/upload/v1718816474/house_riu8uw.jpg");
        } else if (img.getId() != null && img.getFile().isEmpty()) {
            img.setImage(curImg);
        }

        if (!img.getFile().isEmpty() && img.getId() == null || img.getId() != null && !img.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(img.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                img.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ImageProfileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.imgRepo.addOrUpdateImageProfile(img);
    }

    @Override
    public Imageprofile getImageProfileById(int id) {
        return this.imgRepo.getImageProfileById(id);
    }

    @Override
    public void deleteImageProfile(int id) {
        this.imgRepo.deleteImageProfile(id);
    }

    @Override
    public List<Typeimage> listTypes() {
        return this.imgRepo.listTypes();
    }

    @Override
    public List<User> listUsers() {
        return this.imgRepo.listUsers();
    }

}
