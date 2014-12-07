/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

/**
 *
 * @author SSSB
 * Exception levee lors des problemes 
 * liee a l'interaction avec la base de donnee
 */
public class DAOConfigurationException extends RuntimeException{
    
    //Constructeurs
    
    public DAOConfigurationException (String message){
        super(message);
    }
    
    public DAOConfigurationException (String message, Throwable cause){
        super(message, cause);
    }
    
    public DAOConfigurationException (Throwable cause){
        super(cause);
    }
    
}
