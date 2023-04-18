/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.tests;


import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.services.ServiceRegime;
import com.changemakers.atpeace.utils.MyConnexion;

import java.sql.SQLException;

/**
 *
 * @author FGH
 */
public class MainClass {
    
    public static void main(String[] args) {
        MyConnexion cn1 = MyConnexion.getInstance();
        MyConnexion cn2 = MyConnexion.getInstance();
        MyConnexion cn3 = MyConnexion.getInstance();
        MyConnexion cn4 = MyConnexion.getInstance();
        MyConnexion cn5 = MyConnexion.getInstance();
        
        System.out.println(cn1.hashCode());
        System.out.println(cn2.hashCode());
        System.out.println(cn3.hashCode());
        System.out.println(cn4.hashCode());
        System.out.println(cn5.hashCode());
        
        ServiceRegime sp = new ServiceRegime();
        

        Regime r = new Regime(6,"title1111333EEEEE","discrption1111113333333","list element11111333","imagerrrrrrrr","level11111333");
        
        try {
            sp.insertOne1(r);
            //sp.updateOne(r);
            //sp.deleteOne(10);
            sp.updateOne(r);
            System.out.println(sp.selectAll());
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
}
