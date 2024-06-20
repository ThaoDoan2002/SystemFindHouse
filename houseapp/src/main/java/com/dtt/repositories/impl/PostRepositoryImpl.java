/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Imageprofile;
import com.dtt.pojo.Post;
import com.dtt.pojo.User;
import com.dtt.repositories.PostRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Post> getPosts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Post> q = b.createQuery(Post.class);
        Root r = q.from(Post.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            Predicate titlePredicate = b.like(r.get("title"), String.format("%%%s%%", kw));
            Predicate contentPredicate = b.like(r.get("content"), String.format("%%%s%%", kw));

            // Tạo một mệnh đề 'OR' giữa 'title' và 'content'
            Predicate searchPredicate = b.or(titlePredicate, contentPredicate);

            // Thêm mệnh đề 'OR' vào list các điều kiện tìm kiếm
            predicates.add(searchPredicate);
        }

        String user = params.get("user");

        if (r.get("userId") != null && user != null && !user.isEmpty()) {
            Join<Post, User> userJoin = r.join("userId");
            if (user != null && !user.isEmpty()) {
                predicates.add(b.equal(userJoin.get("id").as(Integer.class), Integer.parseInt(user)));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("teantPosts.PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);//lay so phan tu
        }
        return query.getResultList();
    }

    @Override
    public int countPost() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Post");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdatePost(Post p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p != null && p.getId() != null && p.getId() > 0) {
            p.setUpdatedAt(new Date());
            s.update(p);
        } else {
            s.save(p);
        }
    }

    @Override
    public Post getPostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Post.class, id);
    }

    @Override
    public void deletePost(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Post p = this.getPostById(id);
        s.delete(p);
    }

    @Override
    public List<Post> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Post.findAll");
        return q.getResultList();
    }

    @Override
    public void addOrUpdatePostV2(Post p, BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
