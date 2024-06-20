/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Typeimage;
import com.dtt.repositories.TypeImageRepository;
import com.dtt.services.TypeImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class TypeImageServiceImpl implements TypeImageService{
    @Autowired
    TypeImageRepository typeImgRepo;
    
        
    @Override
    public List<Typeimage> getTypeImages() {
        return this.typeImgRepo.getTypeImages();
    }

    @Override
    public void addOrUpdateTypeImage(Typeimage t) {
        this.typeImgRepo.addOrUpdateTypeImage(t);
    }

    @Override
    public Typeimage getTypeImageById(int id) {
        return this.typeImgRepo.getTypeImageById(id);
    }

    @Override
    public void deleteTypeImage(int id) {
        typeImgRepo.deleteTypeImage(id);
    }
    
}
