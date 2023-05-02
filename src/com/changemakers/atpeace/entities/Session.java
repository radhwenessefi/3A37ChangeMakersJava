/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.entities;

/**
 *
 * @author gille
 */
public class Session {
    
    private int id_user;
    private String username;
    private String user_role;

    public Session() {
    }

    public Session(int id_user, String username, String user_role) {
        this.id_user = id_user;
        this.username = username;
        this.user_role = user_role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
    
    
}
