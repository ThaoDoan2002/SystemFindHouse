/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.formatter;

import com.dtt.pojo.Typeimage;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author doant
 */
public class TypeImageFormatter implements Formatter<Typeimage> {

    @Override
    public String print(Typeimage type, Locale locale) {
        return String.valueOf(type.getId());
    }

    @Override
    public Typeimage parse(String id, Locale locale) throws ParseException {
        Typeimage t = new Typeimage();
        t.setId(Integer.parseInt(id));

        return t;
    }

}
