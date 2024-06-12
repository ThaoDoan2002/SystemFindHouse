/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.formatter;


import com.dtt.pojo.Ward;



import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author doant
 */
public class WardsFormatter implements Formatter<Ward> {



    @Override
    public Ward parse(String id, Locale locale) throws ParseException {
        Ward w = new Ward();
        w.setId(Integer.parseInt(id));

        return w;
    }

    @Override
    public String print(Ward wards, Locale locale) {
        return String.valueOf(wards.getId());
    }

}
