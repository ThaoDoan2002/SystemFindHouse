/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.components;

import com.dtt.util.Email;
import org.springframework.stereotype.Service;

/**
 *
 * @author doant
 */
@Service
public class MailSevice {
    static final String from = "doanthithao20022003@gmail.com";
    static final String password = "your_gmail_password"; // Replace with your Gmail password or App Password

    public boolean sendEmail(String to, String subject, String content) {
        try {
            Email.sendEmail(to, subject, content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
