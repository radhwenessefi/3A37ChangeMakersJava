/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Sport;
import com.changemakers.atpeace.services.ServiceSport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class controleSport {

    public static boolean ControleTitre(Sport R) throws SQLException {

        ServiceSport sp = new ServiceSport();
        List<Sport> sports = sp.selectAll();

        String str = (R.getTitre()).toLowerCase();
        for (Sport Rt : sports) {
            if (Rt.getTitre().toLowerCase().equals(str.toLowerCase())) {
                return false;
            }
        }
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }
    public static boolean ControleTitreUpdate(Sport R) throws SQLException {

        

        String str = (R.getTitre()).toLowerCase();
       
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }


    public static boolean ControleDesc(Sport R) {
        String str = (R.getDiscription()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    public static boolean ControleNiveaux(Sport R) {
        String str = (R.getNiveaux()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    public static boolean Controlelevel(Sport R) {
        String str = (R.getLevel()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }
      public static boolean ControleImage(Sport R) {
        String str = (R.getImage()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        
        return true;
    }
    public List AfficheToutSport() throws SQLException {
             ServiceSport sp = new ServiceSport();
       List<Sport>sports = sp.selectAll();
        return sports;
	}
       public List AffichebyLevel(String level) throws SQLException {
             ServiceSport sp = new ServiceSport();
       List<Sport>regimes = sp.selectByLevel(level);
        return regimes;
	}
    public void SupprimerSport(Sport r) throws SQLException {
          ServiceSport sp = new ServiceSport();
		if (r.getId() != 0)
			 sp.deleteOne(r.getId());
		
	}

}
