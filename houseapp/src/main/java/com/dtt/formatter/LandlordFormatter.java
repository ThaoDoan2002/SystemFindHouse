/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.formatter;

import com.dtt.pojo.Landlord;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author doant
 */
public class LandlordFormatter implements Formatter<Landlord> {

    @Override
    public String print(Landlord landlord, Locale locale) {
        return String.valueOf(landlord.getId());
    }

    @Override
    public Landlord parse(String id, Locale locale) throws ParseException {
        Landlord l = new Landlord();
        l.setId(Integer.parseInt(id));

        return l;
    }

}
