/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.Province;
import com.dtt.pojo.User;
import com.dtt.repositories.ProvinceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class ProvinceRepositoryImpl implements ProvinceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<Province> getProvinces() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Province.findAll");
        return q.getResultList();
    }

    @Override
    public List<Province> getProvinces(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Province> q = b.createQuery(Province.class);
        Root r = q.from(Province.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("provinces.PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);//lay so phan tu
        }
        return query.getResultList();
    }

    @Override
    public int countProvince() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Province");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateProvince(Province p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p != null && p.getId() != null && p.getId() > 0) {
            s.update(p);
        } else {
            s.save(p);
        }
    }

    @Override
    public Province getProvinceById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Province.class, id);
    }

    @Override
    public void deleteProvince(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Province p = this.getProvinceById(id);

        if (p != null) {

            Set<Landlord> landlords = p.getLandlordSet();
            if (landlords != null && !landlords.isEmpty()) {
                for (Landlord landlord : landlords) {
                    landlord.setProvinceId(null); // Ngắt liên kết Landlord và Province
                }
            }

            s.delete(p);
        }
    }

}
