/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Comment;
import com.dtt.pojo.Post;
import com.dtt.services.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author doant
 */
@RestController
public class ApiCommentController {

    @Autowired
    private CommentService cmtSvc;

    @DeleteMapping("/delete/comments/{cmtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "cmtId") int id) {
        this.cmtSvc.deleteComment(id);
    }

    @GetMapping("/comments/posts/{userId}")
    public ResponseEntity<List<Post>> listComments(@PathVariable(value = "userId") int id) {
        System.out.println(id);
        System.out.println(this.cmtSvc.getPostsToComment(id));
        return new ResponseEntity<>(this.cmtSvc.getPostsToComment(id), HttpStatus.OK);
    }
    
  
}
