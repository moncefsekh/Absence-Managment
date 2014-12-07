/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import java.util.List;

/**
 *
 * @author SSSB
 */
public interface SectionDAO {
    List getSectionBySpec (String specialite) throws DAOException ;
    
    int ajouterSection (String codeSection,String nomSection,int idSpecialite)throws DAOException;
    
    void modifierModule (int idSection ,String codeSection,String nomSection , int idSpecialite)throws DAOException;
}
