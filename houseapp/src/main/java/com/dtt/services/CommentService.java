package com.dtt.services;

import com.dtt.pojo.Comment;
import com.dtt.pojo.Post;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author doant
 */
public interface CommentService {

    List<Comment> getComments(Map<String, String> params);

    int countComment();

    void addOrUpdateComment(Comment cmt);

    Comment getCommentById(int id);

    void deleteComment(int id);

    List<Post> getPostsToComment(int userId);
}
