/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.components.MailSevice;
import com.dtt.pojo.Follow;
import com.dtt.pojo.Landlord;
import com.dtt.pojo.Landlordpost;
import com.dtt.pojo.Post;
import com.dtt.pojo.Room;
import com.dtt.pojo.User;
import com.dtt.repositories.LandlordPostRepository;
import com.dtt.repositories.PostRepository;
import com.dtt.repositories.UserRepository;
import com.dtt.services.LandlordPostService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class LandlordPostServiceImpl implements LandlordPostService {

    @Autowired
    private LandlordPostRepository landlordRepo;

    @Autowired
    private PostRepository postRepo;
    
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MailSevice mailService;

    @Override
    public List<Landlordpost> getLandlordPosts(Map<String, String> params) {
        return this.landlordRepo.getLandlordPosts(params);
    }

    @Override
    public int countLandlordPost() {
        return this.landlordRepo.countLandlordPost();
    }

    @Override
    public void addOrUpdateLandlordPost(Landlordpost p) {
        if (p.getId() == null) {
            System.out.println(p.getPostId());
            Post post = this.postRepo.getPostById(p.getPostId().getId());
            User landlord = post.getUserId();
            List<User> followers = this.userRepo.getFollowers(landlord.getId());
            for (User f : followers) {
                mailService.sendEmail(f.getEmail(), "New Landlord Posted", "Bài đăng mới từ chủ trọ: " + landlord.getUsername());
            }
        }
        this.landlordRepo.addOrUpdateLandlordPost(p);
    }

    @Override
    public Landlordpost getLandlordPostById(int id) {
        return this.landlordRepo.getLandlordPostById(id);
    }

    @Override
    public void deleteLandlordPost(int id) {
        this.landlordRepo.deleteLandlordPost(id);
    }

    @Override
    public List<Post> getPosts() {
        return this.landlordRepo.getPosts();
    }

    @Override
    public List<Room> getRoomsByPostUser(int id) {
        return this.landlordRepo.getRoomsByPostUser(id);
    }

}
