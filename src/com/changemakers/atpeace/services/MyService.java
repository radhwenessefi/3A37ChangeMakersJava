/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.changemakers.atpeace.services;

import java.util.List;

/**
 *
 * @author gille
 */
public interface MyService<T> {
    
    void Insert(T t);
    void Update(T t);
    void Delete(T t);
    void Delete(String email);
    List<T> Read();
    T SignIn(String email, String password);
    T Verifier(String email);
}
