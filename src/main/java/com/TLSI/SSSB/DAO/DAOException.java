/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

/**
 *
 * @author SSSB
 * Excception levee lors d'un
 * probleme liee a la configuration
 * du DAO et du driver Mysql 
 */
public class DAOException extends RuntimeException {
    //Constructeurs 
    
    public DAOException (String message){
        super(message);
    }
    
    public DAOException (String message , Throwable cause){
        super(message, cause);
    }
    
    public DAOException (Throwable cause){
        super(cause);
    }
    
    
}
