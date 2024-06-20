/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import com.dtt.repositories.TypeImageRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author doant
 */
@Repository
@Transactional
public class TypeImageRepositoryImpl implements TypeImageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Typeimage> getTypeImages() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Typeimage.findAll");
        return q.getResultList();
    }

    @Override
    public void addOrUpdateTypeImage(Typeimage t) {
        Session s = this.factory.getObject().getCurrentSession();
        if (t != null && t.getId() != null) {
            s.update(t);
        } else {
            s.save(t);
        }
    }

    @Override
    public Typeimage getTypeImageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Typeimage.findById");
        q.setParameter("id", id);

        return (Typeimage) q.getSingleResult();
    }

    @Override
    public void deleteTypeImage(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Typeimage t = this.getTypeImageById(id);

        if (t != null) {

            s.delete(t);
        }
    }



}
