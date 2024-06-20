/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Image;
import com.dtt.pojo.Imageprofile;
import com.dtt.repositories.ImageRepository;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author doant
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Image> getImages(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Image.findAll");
        return q.getResultList();
    }

    @Override
    public int countImage() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Image");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateImage(Image img) {
        Session s = this.factory.getObject().getCurrentSession();
        if (img != null && img.getId() != null && img.getId() > 0) {
            s.update(img);
        } else {
            s.save(img);
        }
    }

    @Override
    public Image getImageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Image.class, id);
    }

    @Override
    public void deleteImage(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Image i = this.getImageById(id);
        s.delete(i);
    }

}
