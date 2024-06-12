/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.District;
import com.dtt.pojo.Landlord;
import com.dtt.pojo.Province;
import com.dtt.repositories.DistrictRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class DistrictRepositoryImpl implements DistrictRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<District> getDistrictsByProvince(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT d FROM District d WHERE d.provinceId = :provinceId");
        q.setParameter("provinceId", id);
        System.out.println("Hi");
        System.out.println((List<District>) q.getResultList());
        return (List<District>) q.getResultList();
    }

    @Override
    public List<District> getDistricts() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("District.findAll");
        return q.getResultList();
    }

    @Override
    public District getDistrictById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("District.findById");
        q.setParameter("id", id);
        return (District) q.getSingleResult();
    }

    @Override
    public List<District> getDistricts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<District> q = b.createQuery(District.class);
        Root r = q.from(District.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }
        String province = params.get("province");
        if (province != null && !province.isEmpty()) {
            predicates.add(b.equal(r.get("provinceId"), Integer.parseInt(province)));
        }
        q.orderBy(b.desc(r.get("id")));

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("districts.PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);//lay so phan tu
        }
        return query.getResultList();
    }

    @Override
    public int countDistrict() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM District");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateDistrict(District d) {
        Session s = this.factory.getObject().getCurrentSession();
        if (d != null && d.getId() != null) {
            s.update(d);
        } else {
            s.save(d);
        }
    }

    @Override
    public void deleteDistrict(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        District d = this.getDistrictById(id);

        if (d != null) {

            Set<Landlord> landlords = d.getLandlordSet();
            if (landlords != null && !landlords.isEmpty()) {
                for (Landlord landlord : landlords) {
                    landlord.setDistrictId(null);
                }
            }

            s.delete(d);
        }
    }
}
