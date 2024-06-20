/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Comment;
import com.dtt.pojo.Post;
import com.dtt.repositories.CommentRepository;
import com.dtt.services.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository cmtRepo;

    @Override
    public List<Comment> getComments(Map<String, String> params) {
        return this.cmtRepo.getComments(params);
    }

    @Override
    public int countComment() {
        return this.cmtRepo.countComment();
    }

    @Override
    public void addOrUpdateComment(Comment cmt) {
        this.cmtRepo.addOrUpdateComment(cmt);
    }

    @Override
    public Comment getCommentById(int id) {
        return this.cmtRepo.getCommentById(id);
    }

    @Override
    public void deleteComment(int id) {
        this.cmtRepo.deleteComment(id);
    }

    @Override
    public List<Post> getPostsToComment(int userId) {
        return this.cmtRepo.getPostsToComment(userId);
    }

}
