/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Follow;
import com.dtt.pojo.User;
import com.dtt.repositories.FollowRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
public class FollowRepositoryImpl implements FollowRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Follow> getFollows(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Follow.findAll");
        return q.getResultList();
    }

    @Override
    public int countFollow() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Follow");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateFollow(Follow fl) {
        Session s = this.factory.getObject().getCurrentSession();
        if (fl != null && fl.getId() != null && fl.getId() > 0) {
            s.update(fl);
        } else {
            s.save(fl);
        }
    }

    @Override
    public Follow getFollowById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Follow.class, id);
    }

    @Override
    public void deleteFollow(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Follow fl = this.getFollowById(id);
        s.delete(fl);
    }

    @Override
    public List<User> getLandlords(int id) {
        List<User> landlords = new ArrayList<>();
        try {
            Session s = this.factory.getObject().getCurrentSession();
            User follower = s.get(User.class, id);

            // Extract the followed user IDs from the Follow set
            Set<Follow> followSet = follower.getFollowSet();
            List<Integer> followIds = followSet.stream()
                    .map(follow -> follow.getLandlordId().getId())
                    .collect(Collectors.toList());

            // Check if followIds is empty to avoid issues in the query
            if (followIds.isEmpty()) {
                followIds.add(-1); // Adding a dummy ID that doesn't exist
            }

            // Create the query with NOT IN clause
            Query q = s.createQuery(
                    "SELECT u FROM User u WHERE u.id NOT IN (:followIds) AND u.role = :role", User.class
            );
            q.setParameter("followIds", followIds);
            q.setParameter("role", "ROLE_LANDLORD");

            // Execute the query and return the results
            landlords = q.getResultList();
        } catch (Exception e) {
            // Handle exception (log it or rethrow it as appropriate)
            e.printStackTrace();
        }
        return landlords;
    }

    @Override
    public List<User> getFollowers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByRole");
        q.setParameter("role", "ROLE_TENANT");
        return q.getResultList();
    }
}
