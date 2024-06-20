/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Typeimage;
import com.dtt.pojo.User;
import com.dtt.repositories.ImageProfileRepository;
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
public class ImageProfileRepositoryImpl implements ImageProfileRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Imageprofile> getImageProfiles(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Imageprofile> q = b.createQuery(Imageprofile.class);
        Root r = q.from(Imageprofile.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();
        String type = params.get("type");
        String user = params.get("user");

        if (r.get("typeId") != null && type != null && !type.isEmpty()) {
            Join<Imageprofile, Typeimage> typeJoin = r.join("typeId");
            if (type != null && !type.isEmpty()) {
                predicates.add(b.equal(typeJoin.get("id").as(Integer.class), Integer.parseInt(type)));
            }
        }

        if (r.get("userId") != null && user != null && !user.isEmpty()) {
            Join<Imageprofile, User> userJoin = r.join("userId");
            if (user != null && !user.isEmpty()) {
                predicates.add(b.equal(userJoin.get("id").as(Integer.class), Integer.parseInt(user)));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("imageProfile.PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);//lay so phan tu
        }
        return query.getResultList();
    }

    @Override
    public int countImageProfile() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Imageprofile");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateImageProfile(Imageprofile img) {
        Session s = this.factory.getObject().getCurrentSession();
        if (img != null && img.getId() != null && img.getId() > 0) {
            s.update(img);
        } else {
            s.save(img);
        }
    }

    @Override
    public Imageprofile getImageProfileById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Imageprofile.class, id);
    }

    @Override
    public void deleteImageProfile(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Imageprofile i = this.getImageProfileById(id);
        s.delete(i);
    }

    @Override
    public List<Typeimage> listTypes() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Typeimage.findAll");
        return q.getResultList();
    }

    @Override
    public List<User> listUsers() {
         Session s = this.factory.getObject().getCurrentSession();
          Query q = s.createNamedQuery("User.findByRole");
          q.setParameter("role", "ROLE_LANDLORD");
          return q.getResultList();
    }

}
