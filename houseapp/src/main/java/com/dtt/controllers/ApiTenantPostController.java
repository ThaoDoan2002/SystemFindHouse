/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Post;
import com.dtt.pojo.Tenantpost;
import com.dtt.pojo.User;
import com.dtt.services.PostService;
import com.dtt.services.TenantPostService;
import com.dtt.services.UserService;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.awt.geom.Point2D;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author doant
 */
@RestController


public class ApiTenantPostController {

    @Autowired
    private TenantPostService tpostSvc;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postSvc;

    @DeleteMapping("/delete/tenant-posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "postId") int id) {
        this.tpostSvc.deleteTenantPost(id);
    }

//    @GetMapping("/tenantposts/")
//    @CrossOrigin
//    public ResponseEntity<List<Tenantpost>> list(@RequestParam Map<String, String> params) {
//        return new ResponseEntity<>(this.tpostSvc.getTenantPosts(params), HttpStatus.OK);
//    }

    @PostMapping(path = "/api/tenantpost/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestParam Map<String, String> params, Principal p) {
        User u = this.userService.getUserByUsername(p.getName());
        if (u != null) {
            // Create a new Post object
            Post post = new Post();

            // Set title and content from params map
            post.setTitle(params.get("title"));
            post.setContent(params.get("content"));

            // Set the user for the post
            post.setUserId(u); // Assuming you have a setUser method in Post entity

            // Add or update the post
            this.postSvc.addOrUpdatePost(post);

//
            // Create a new Tenantpost object and associate it with the Post
            Tenantpost tpost = new Tenantpost();
            tpost.setAddress(params.get("address"));
            tpost.setLatitude(new BigDecimal(params.get("latitude")));
            tpost.setLongitude(new BigDecimal(params.get("longitude")));
            tpost.setScope(new BigDecimal(params.get("scope")));
            tpost.setMaxPrice(new BigDecimal(params.get("maxPrice")));
            tpost.setMinPrice(new BigDecimal(params.get("minPrice")));
            tpost.setArea(new BigDecimal(params.get("area")));
            tpost.setMaxOccupants(new BigDecimal(params.get("maxoccupants")));
            
            tpost.setPostId(post); // Assuming you have a setPost method in Tenantpost entity

            // Add or update the tenant post
            this.tpostSvc.addOrUpdateTenantPost(tpost);
        } else {
            // Handle the case where user is null (optional based on your logic)

            throw new RuntimeException("User not found for username: " + p.getName());
        }

    }

    @GetMapping("/api/tenantposts/")
    @CrossOrigin
    public ResponseEntity<List<Tenantpost>> SearchPost(@RequestParam Map<String, String> params, Principal p) {
        User u = this.userService.getUserByUsername(p.getName());
        if (u != null) {
            // Chuyển đổi địa chỉ thành tọa độ kinh, vĩ độ
         
            double lat = Double.parseDouble(params.get("lat"));
            double lng = Double.parseDouble(params.get("lng"));

            // Tìm kiếm nhà trọ theo vị trí người dùng
            List<Tenantpost> posts = tpostSvc.getTPosts(lat, lng);

            // Xử lý kết quả và trả về
              return new ResponseEntity<>(posts, HttpStatus.OK);
        } else {
            // Handle the case where user is null (optional based on your logic)

            throw new RuntimeException("User not found for username: " + p.getName());
        }
    }

  
}
