/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Image;
import com.dtt.pojo.Landlordpost;
import com.dtt.pojo.Post;
import com.dtt.pojo.Room;
import com.dtt.pojo.Tenantpost;
import com.dtt.pojo.User;
import com.dtt.services.ImageService;
import com.dtt.services.LandlordPostService;
import com.dtt.services.PostService;
import com.dtt.services.RoomService;
import com.dtt.services.UserService;
import com.sun.org.apache.bcel.internal.generic.L2D;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ApiLandlordPostController {

    @Autowired
    private LandlordPostService lpostSvc;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postSvc;

    @Autowired
    private RoomService roomSvc;

    @Autowired
    private ImageService imgSvc;

    @DeleteMapping("/delete/landlord-posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "postId") int id) {
        this.lpostSvc.deleteLandlordPost(id);
    }

    @GetMapping("/landlord-posts/rooms/{postId}")
    public ResponseEntity<List<Room>> listRooms(@PathVariable(value = "postId") int id) {
        return new ResponseEntity<>(this.lpostSvc.getRoomsByPostUser(id), HttpStatus.OK);
    }

    @PostMapping(path = "/api/landlordpost_create/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestParam Map<String, String> params, Principal p, @RequestPart MultipartFile[] file) {
        User u = this.userService.getUserByUsername(p.getName());
        if (u != null) {
            System.out.println(file);
            // Create a new Post object
            Post post = new Post();

            // Set title and content from params map
            post.setTitle(params.get("title"));
            post.setContent(params.get("content"));
            // Set the user for the post
            post.setUserId(u);

            // Add or update the post
            this.postSvc.addOrUpdatePost(post);

            Room r = new Room();
            r.setName(params.get("name"));
            r.setUserId(u);
            r.setAddress(params.get("address"));
            r.setLatitude(new BigDecimal(params.get("latitude")));
            r.setLongitude(new BigDecimal(params.get("longitude")));
            r.setMaxOccupants(Integer.parseInt(params.get("maxoccupants")));
            r.setArea(new BigDecimal(params.get("area")));
            r.setPrice(new BigDecimal(params.get("price")));
            r.setUserId(u);
            this.roomSvc.addOrUpdateRoom(r);

            System.out.println(file);
            if (file.length > 0) {
                Set<Image> imgs = new HashSet<>();
                for (MultipartFile f : file) {
                    Image i = new Image();
                    i.setFile(f);
                    i.setRoomId(r);
                    this.imgSvc.addOrUpdateImage(i);
                    imgs.add(i);
                }
                r.setImageSet(imgs);
            }
            this.roomSvc.addOrUpdateRoom(r);

//
            // Create a new Tenantpost object and associate it with the Post
            Landlordpost lpost = new Landlordpost();
            lpost.setPostId(post);
            lpost.setRoomId(r);

            // Add or update the tenant post
            this.lpostSvc.addOrUpdateLandlordPost(lpost);
        } else {
            // Handle the case where user is null (optional based on your logic)

            throw new RuntimeException("User not found for username: " + p.getName());
        }
    }

    @GetMapping("/api/landlordposts/")
    @CrossOrigin
    public ResponseEntity<List<Landlordpost>> listPosts(@RequestParam Map<String, String> params, Principal p) {
        if (p.getName() != null) {
            List<Landlordpost> posts = this.lpostSvc.getLandlordPosts(params);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } else {
            // Handle the case where user is null (optional based on your logic)

            throw new RuntimeException("User not found for username: " + p.getName());
        }

    }

}
