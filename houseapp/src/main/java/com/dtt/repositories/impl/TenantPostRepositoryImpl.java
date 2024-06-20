/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Post;
import com.dtt.pojo.Tenantpost;
import com.dtt.repositories.TenantPostRepository;
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author doant
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class TenantPostRepositoryImpl implements TenantPostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Tenantpost> getTenantPosts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Tenantpost> q = b.createQuery(Tenantpost.class);
        Root r = q.from(Tenantpost.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("address"), String.format("%%%s%%", kw)));
        }

        String longi = params.get("longitude");
        if (longi != null && !longi.isEmpty()) {
            predicates.add(b.equal(r.get("longitude"), Integer.parseInt(longi)));
        }
        String lati = params.get("latitude");
        if (lati != null && !lati.isEmpty()) {
            predicates.add(b.equal(r.get("latitude"), Integer.parseInt(lati)));
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
    public int countTenantPost() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Tenantpost");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateTenantPost(Tenantpost p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p != null && p.getId() != null && p.getId() > 0) {
            s.update(p);
        } else {
            s.save(p);
        }
    }

    @Override
    public Tenantpost getTenantPostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Tenantpost.class, id);
    }

    @Override
    public void deleteTenantPost(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Tenantpost tp = this.getTenantPostById(id);

        if (tp != null) {
            // Ngắt liên kết giữa Landlord và User
            Post p = tp.getPostId();
            if (p != null) {
                p.setTenantpost(null);
                tp.setPostId(null); // Đặt user của Landlord về null
                s.update(p); // Cập nhật đối tượng User trong cơ sở dữ liệu
            }

            // Xóa đối tượng Landlord
            s.delete(tp);
        }
    }

    @Override
    public List<Post> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT p FROM Post p LEFT JOIN Tenantpost t ON p.id = t.postId.id WHERE t.postId.id IS NULL AND p.userId.role = :role";
        Query<Post> query = s.createQuery(hql, Post.class);
        query.setParameter("role", "ROLE_TENANT");

        return query.getResultList();
    }

    @Override
    public List<Tenantpost> getTPosts(double lat, double lng) {
        Session s = this.factory.getObject().getCurrentSession();
          // Gọi stored procedure bằng SQL Native Query
        String sql = "CALL GetTenantPostsWithinDistance(:lat, :lng, :distance)";
        Query query = s.createNativeQuery(sql, Tenantpost.class);
        query.setParameter("lat", lat);
        query.setParameter("lng", lng);
        query.setParameter("distance", 10);

        return query.getResultList();
    }
}
