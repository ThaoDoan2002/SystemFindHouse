/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.impl;

import com.dtt.pojo.Comment;
import com.dtt.pojo.Post;
import com.dtt.pojo.User;
import com.dtt.repositories.CommentRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Comment> getComments(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Comment.findAll");
        return q.getResultList();
    }

    @Override
    public int countComment() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Comment");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public void addOrUpdateComment(Comment cmt) {
        Session s = this.factory.getObject().getCurrentSession();
        if (cmt != null && cmt.getId() != null && cmt.getId() > 0) {
            cmt.setUpdatedAt(new Date());
            s.update(cmt);
        } else {
            s.save(cmt);
        }
    }

    @Override
    public Comment getCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Comment.class, id);
    }

    @Override
    public void deleteComment(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Comment cmt = this.getCommentById(id);
        s.delete(cmt);
    }

    @Override
    public List<Post> getPostsToComment(int userId) {
         Session s = this.factory.getObject().getCurrentSession();
         User u = s.get(User.class,userId);
         
         Query q = s.createQuery("SELECT p FROM Post p WHERE p.userId.role <> :role");
         q.setParameter("role", u.getRole());
         return q.getResultList();
    }

}
