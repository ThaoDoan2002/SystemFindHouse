/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.formatter;

import com.dtt.pojo.District;
import com.dtt.pojo.Province;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author doant
 */
public class DistrictFormatter implements Formatter<District> {



    @Override
    public District parse(String id, Locale locale) throws ParseException {
        District d = new District();
        d.setId(Integer.parseInt(id));

        return d;
    }

    @Override
    public String print(District district, Locale locale) {
        return String.valueOf(district.getId());
    }

}
