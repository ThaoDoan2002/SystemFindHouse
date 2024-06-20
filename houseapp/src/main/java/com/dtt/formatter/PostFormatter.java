/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.formatter;

import com.dtt.pojo.Post;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author doant
 */
public class PostFormatter implements Formatter<Post> {

    @Override
    public String print(Post post, Locale locale) {
        return String.valueOf(post.getId());
    }

    @Override
    public Post parse(String id, Locale locale) throws ParseException {
        Post p = new Post();
        p.setId(Integer.parseInt(id));

        return p;
    }

}
