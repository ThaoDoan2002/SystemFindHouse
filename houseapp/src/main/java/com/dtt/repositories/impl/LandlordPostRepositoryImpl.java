/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Landlordpost;
import com.dtt.pojo.Post;
import com.dtt.pojo.Room;
import com.dtt.pojo.User;
import com.dtt.repositories.LandlordPostRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
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
public class LandlordPostRepositoryImpl implements LandlordPostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Landlordpost> getLandlordPosts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Landlordpost.findAll");
        return q.getResultList();
    }

    @Override
    public int countLandlordPost() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Landlordpost");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateLandlordPost(Landlordpost p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p != null && p.getId() != null && p.getId() > 0) {
            s.update(p);
        } else {
            s.save(p);
        }
    }

    @Override
    public Landlordpost getLandlordPostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Landlordpost.class, id);
    }

    @Override
    public void deleteLandlordPost(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Landlordpost lp = this.getLandlordPostById(id);

        if (lp != null) {
            // Ngắt liên kết giữa Landlord và User
            Post p = lp.getPostId();
            Room r = lp.getRoomId();
            if (p != null && r != null) {
                p.setLandlordpost(null);
                r.setLandlordpost(null);
                lp.setPostId(null);
                lp.setRoomId(null);
                s.update(p);
            }

            // Xóa đối tượng Landlord
            s.delete(lp);
        }
    }

    @Override
    public List<Post> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT p FROM Post p LEFT JOIN Landlordpost l ON p.id = l.postId.id WHERE l.postId.id IS NULL AND p.userId.role = :role";
        Query<Post> query = s.createQuery(hql, Post.class);
        query.setParameter("role", "ROLE_LANDLORD");

        return query.getResultList();
    }

    @Override
    public List<Room> getRoomsByPostUser(int postId) {
        Session session = this.factory.getObject().getCurrentSession();
        Post p = session.get(Post.class, postId);
        

        Query query = session.createQuery("SELECT r FROM Room r WHERE r.userId=:user AND r.id NOT IN (SELECT lp.roomId.id FROM Landlordpost lp)");
        query.setParameter("user", p.getUserId());
        List<Room> rooms = query.getResultList();
        return rooms;
    }



}
