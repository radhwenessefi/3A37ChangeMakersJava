/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Rate;
import com.changemakers.atpeace.entites.Sport;
import com.changemakers.atpeace.services.ServiceRate;
import com.changemakers.atpeace.services.ServiceSport;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public class controlleRate {
    
     public List AfficheToutRate() throws SQLException {
             ServiceRate sp = new ServiceRate();
       List<Rate>rates = sp.selectAll();
        return rates;
	}
    
}
