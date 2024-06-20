/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Comment;
import com.dtt.pojo.Post;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doant
 */
public interface CommentRepository {

    List<Comment> getComments(Map<String, String> params);

    int countComment();

    void addOrUpdateComment(Comment cmt);

    Comment getCommentById(int id);

    void deleteComment(int id);
    
    List<Post> getPostsToComment(int userId);

}
