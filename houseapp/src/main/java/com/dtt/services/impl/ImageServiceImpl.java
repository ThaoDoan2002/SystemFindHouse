/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtt.pojo.Image;
import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import com.dtt.pojo.User;
import com.dtt.repositories.ImageRepository;
import com.dtt.services.ImageService;
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
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imgRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Image> getImages(Map<String, String> params) {
        return this.imgRepo.getImages(params);
    }

    @Override
    public int countImage() {
        return this.imgRepo.countImage();
    }

    @Override
    public void addOrUpdateImage(Image img, String curImg) {
        if (img.getId() == null && img.getFile().isEmpty()) {
            img.setImage("https://res.cloudinary.com/dzrgeifj0/image/upload/v1718824549/housep_s59vxy.jpg");
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
        this.imgRepo.addOrUpdateImage(img);
    }

    @Override
    public Image getImageById(int id) {
        return this.imgRepo.getImageById(id);
    }

    @Override
    public void deleteImage(int id) {
        this.imgRepo.deleteImage(id);
    }

    @Override
    public void addOrUpdateImage(Image img) {

        if (!img.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(img.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                img.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ImageProfileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.imgRepo.addOrUpdateImage(img);
    }
}
