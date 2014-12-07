/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SSSB
 */
public class BD_Connection {
  private static String url = "jdbc:mysql://localhost:3306/gsa";
  private static String user = "root";
  private static String passwd = "";
  private static Connection connect;
   
  public static Connection getInstance(){
    if(connect == null){
      try {
        connect = DriverManager.getConnection(url, user, passwd);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }      
    return connect;
  }   
    
}
