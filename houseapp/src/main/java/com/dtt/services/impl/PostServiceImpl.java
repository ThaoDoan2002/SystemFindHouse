/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Post;
import com.dtt.repositories.PostRepository;
import com.dtt.services.PostService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> getPosts(Map<String, String> params) {
        return this.postRepo.getPosts(params);
    }

    @Override
    public int countPost() {
        return this.postRepo.countPost();
    }

    @Override
    public void addOrUpdatePost(Post p) {
        this.postRepo.addOrUpdatePost(p);
    }

    @Override
    public Post getPostById(int id) {
        return this.postRepo.getPostById(id);
    }

    @Override
    public void deletePost(int id) {
        this.postRepo.deletePost(id);
    }

    @Override
    public List<Post> getPosts() {
        return this.postRepo.getPosts();
    }

}
