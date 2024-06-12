/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.District;
import com.dtt.pojo.Ward;
import com.dtt.repositories.WardsRepository;
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
public class WardsRepositoryImpl implements WardsRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public List<Ward> getWardsByDistrict(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Ward.findByDistrictId");
        q.setParameter("districtId", id);
        return q.getResultList();
    }

    @Override
    public List<Ward> getWards() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Ward.findAll");
        return q.getResultList();
    }

    @Override
    public Ward getWardById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Ward.findById");
        q.setParameter("id", id);
        return (Ward) q.getSingleResult();
    }
}
