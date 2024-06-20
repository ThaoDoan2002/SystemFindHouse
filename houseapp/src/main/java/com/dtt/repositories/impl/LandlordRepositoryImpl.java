/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;
import com.dtt.repositories.LandlordRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class LandlordRepositoryImpl implements LandlordRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Landlord> getLandlords(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Landlord> q = b.createQuery(Landlord.class);
//        Root r = q.from(Landlord.class);
//        q.select(r);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        String kw = params.get("kw");
//        if (kw != null && !kw.isEmpty()) {
//            predicates.add(b.like(r.get("fullName"), String.format("%%%s%%", kw)));
//        }
//        String province = params.get("province");
////        String district = params.get("district");
//        String ward = params.get("ward");
//        if (r.get("provinceId") != null && province != null && !province.isEmpty()) {
////            Join<Landlord, Province> provinceJoin = r.join("provinceId");
//            if (province != null && !province.isEmpty()) {
////                predicates.add(b.equal(provinceJoin.get("id").as(Integer.class), Integer.parseInt(province)));
//            }
////            if (district != null && !district.isEmpty()) {
////                predicates.add(b.equal(r.get("districtId"), Integer.parseInt(district)));
//            }
//            if (ward != null && !ward.isEmpty()) {
//                predicates.add(b.equal(r.get("wardId"), Integer.parseInt(ward)));
//            }
//        }
//
//        q.where(predicates.toArray(Predicate[]::new));
//        q.orderBy(b.desc(r.get("id")));
//
//        Query query = s.createQuery(q);
//        System.out.println(query.getResultList());
//
//        String p = params.get("page");
//        if (p != null && !p.isEmpty()) {
//            int pageSize = Integer.parseInt(env.getProperty("imageProfile.PAGE_SIZE").toString());
//            int start = (Integer.parseInt(p) - 1) * pageSize;
//            query.setFirstResult(start);
//            query.setMaxResults(pageSize);//lay so phan tu
//        }
        Query q = s.createNamedQuery("Landlord.findAll");
        return q.getResultList();
    }

    @Override
    public void addOrUpdateLandlord(Landlord l) {
        Session s = this.factory.getObject().getCurrentSession();
        if (l != null && l.getId() != null) {
            s.update(l);
        } else {
            s.save(l);
        }
    }

    @Override
    public Landlord getLandlordById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        System.out.println(id);
        Query q = s.createNamedQuery("Landlord.findById");
        q.setParameter("id", id);

        System.out.println((Landlord) q.getSingleResult());
        return (Landlord) q.getSingleResult();
    }

    @Override
    public int countLandlord() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Landlord");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void deleteLandlord(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        // Lấy đối tượng Landlord từ cơ sở dữ liệu
        Landlord landlord = this.getLandlordById(id);

        if (landlord != null) {
            // Ngắt liên kết giữa Landlord và User
            User user = landlord.getUserId();
            if (user != null) {
                user.setLandlord(null);
                landlord.setUserId(null); // Đặt user của Landlord về null
                s.update(user); // Cập nhật đối tượng User trong cơ sở dữ liệu
            }

            // Xóa đối tượng Landlord
            s.delete(landlord);
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Session s = this.factory.getObject().getCurrentSession();

        // Sửa lại truy vấn HQL
        String hql = "SELECT u FROM User u LEFT JOIN Landlord l ON u.id = l.userId.id WHERE l.userId.id IS NULL AND u.role = :role";
        Query<User> query = s.createQuery(hql, User.class);
        query.setParameter("role", "ROLE_LANDLORD");

        users = query.getResultList();
        return users;
    }

}
