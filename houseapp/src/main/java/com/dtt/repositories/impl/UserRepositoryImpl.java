/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Landlord;
import com.dtt.pojo.User;
import com.dtt.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author doant
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root r = q.from(User.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("username"), String.format("%%%s%%", kw)));
        }
        String role = params.get("role");
        if (role != null && !role.isEmpty()) {
            predicates.add(b.like(r.get("role"), String.format("%%%s%%", role)));
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("users.PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);//lay so phan tu
        }
        return query.getResultList();
    }

    @Override
    public int countUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM User");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        if (u != null && u.getId() != null && u.getId() > 0) {
            s.update(u);

        } else {
            s.save(u);

        }

    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserById(id);
        s.delete(u);
    }

    @Override
    public User getUserByUsername(String username) {

        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:un and isActive = :ac");
        q.setParameter("un", username);
        q.setParameter("ac", true);
        System.out.println(q.getSingleResult());
        return (User) q.getSingleResult();
    }

//    @Override
//    public List<Landlord> getLandlords() {
//        List<Landlord> landlords = new ArrayList<>();
//        Session s = this.factory.getObject().getCurrentSession();
//        String hql = "SELECT l FROM Landlord l LEFT JOIN User u ON l.id = u.landlordId.id WHERE u.landlordId.id IS NULL";
//        Query query = s.createQuery(hql, Landlord.class);
//        landlords = query.getResultList();
//
//        return landlords;
//    }
    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findAll");
        return q.getResultList();
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public List<User> getFollowers(int lid) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<User> q = s.createQuery("SELECT u FROM Follow f JOIN f.followerId u WHERE f.landlordId.id = :landlordId", User.class);
        q.setParameter("landlordId", lid);
        System.out.println(q.getResultList());
        return q.getResultList();
    }

}
