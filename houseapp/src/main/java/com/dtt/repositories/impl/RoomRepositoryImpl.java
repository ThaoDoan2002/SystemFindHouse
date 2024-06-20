/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Room;
import com.dtt.pojo.User;
import com.dtt.repositories.RoomRepository;
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
public class RoomRepositoryImpl implements RoomRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Room> getRooms(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Room> q = b.createQuery(Room.class);
        Root r = q.from(Room.class);
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

        String priceMin = params.get("priceMin");
        String priceMax = params.get("priceMax");
        if (priceMin != null && !priceMin.isEmpty() && priceMax != null && !priceMax.isEmpty()) {
            predicates.add(b.between(r.get("price"), Integer.parseInt(priceMin), Integer.parseInt(priceMax)));
        }
        String maxOccupants = params.get("maxOccupants");
        if (maxOccupants != null && !maxOccupants.isEmpty()) {
            predicates.add(b.equal(r.get("maxOccupants"), Integer.parseInt(maxOccupants)));
        }

        String user = params.get("user");
        if (r.get("userId") != null && user != null && !user.isEmpty()) {
            Join<Room, User> userJoin = r.join("userId");
            if (user != null && !user.isEmpty()) {
                predicates.add(b.equal(userJoin.get("id").as(Integer.class), Integer.parseInt(user)));
            }
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("rooms.PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);//lay so phan tu
        }
        return query.getResultList();
    }

    @Override
    public int countRoom() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Room");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateRoom(Room r) {
        Session s = this.factory.getObject().getCurrentSession();
        if (r != null && r.getId() != null && r.getId() > 0) {
            s.update(r);
        } else {
            s.save(r);
        }
    }

    @Override
    public Room getRoomById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Room.class, id);
    }

    @Override
    public void deleteRoom(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Room r = this.getRoomById(id);

        if (r != null) {

            s.delete(r);  // Xóa room
        }
    }

    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT u FROM User u  WHERE u.role = :role AND u.isActive = :active";
        Query<User> query = s.createQuery(hql, User.class);
        query.setParameter("role", "ROLE_LANDLORD");
        query.setParameter("active", true);
        return query.getResultList();
    }

    @Override
    public List<Room> getRooms() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Room.findAll");
        return q.getResultList();
    }

}
