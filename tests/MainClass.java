/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;



import java.io.File;
import tools.DataSource;



/**
 *
 * @author FGH
 */
public class MainClass {
     private File file2;
    public static void main(String[] args) {
        DataSource.getInstance();
        TestAudio tAudio = new TestAudio();
        
       
    }
    
}
