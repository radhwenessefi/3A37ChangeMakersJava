/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Favoris;
import com.changemakers.atpeace.services.ServiceFavoris;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gille
 */
public class controllerFavoris {
     public List AffichebyLevel(int id ) throws SQLException {
             ServiceFavoris sp = new ServiceFavoris();
       List<Favoris>regimesid = sp.ListFav(id);
        return regimesid;
	}
    
}
