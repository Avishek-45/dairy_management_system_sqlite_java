/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockdown_project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class javaconnect {
 

    Connection conn=null;
    public static Connection connector1(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c=DriverManager.getConnection("jdbc:sqlite:unknown_name.sqlite");
            //JOptionPane.showMessageDialog(null, "Connected");
            return c;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
            
        }
    }
    


