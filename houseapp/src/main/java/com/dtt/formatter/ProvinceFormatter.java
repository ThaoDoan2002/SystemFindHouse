/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.formatter;

import com.dtt.pojo.Province;
import com.dtt.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author doant
 */
public class ProvinceFormatter implements Formatter<Province> {

    @Override
    public String print(Province province, Locale locale) {
        return String.valueOf(province.getId());
    }

    @Override
    public Province parse(String id, Locale locale) throws ParseException {
        Province p = new Province();
        p.setId(Integer.parseInt(id));

        return p;
    }

}
