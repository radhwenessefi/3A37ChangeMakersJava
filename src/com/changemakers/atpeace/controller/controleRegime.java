/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.services.ServiceRegime;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class controleRegime {

    public static boolean ControleTitre(Regime R) throws SQLException {

        ServiceRegime sp = new ServiceRegime();
        List<Regime> regimes = sp.selectAll();

        String str = (R.getTitle()).toLowerCase();
        for (Regime Rt : regimes) {
            if (Rt.getTitle().toLowerCase().equals(str.toLowerCase())) {
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
    public static boolean ControleTitreUpdate(Regime R) throws SQLException {

        

        String str = (R.getTitle()).toLowerCase();
       
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


    public static boolean ControleDesc(Regime R) {
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

    public static boolean Controleliste(Regime R) {
        String str = (R.getListe_alement()).toLowerCase();
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

    public static boolean Controlelevel(Regime R) {
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
      public static boolean ControleImage(Regime R) {
        String str = (R.getImage()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        
        return true;
    }
    public List AfficheToutRegime() throws SQLException {
             ServiceRegime sp = new ServiceRegime();
       List<Regime>regimes = sp.selectAll();
        return regimes;
	}
     public List AffichebyLevel(String level) throws SQLException {
             ServiceRegime sp = new ServiceRegime();
       List<Regime>regimes = sp.selectByLevel(level);
        return regimes;
	}
          public List Affichebyidregime(int id) throws SQLException {
             ServiceRegime sp = new ServiceRegime();
       List<Regime>regimes = sp.selectByIdRegime(id);
        return regimes;
	}
    public void SupprimerRegime(Regime r) throws SQLException {
          ServiceRegime sp = new ServiceRegime();
		if (r.getId() != 0)
			 sp.deleteOne(r.getId());
		
	}

}
