/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Specialite;
import java.util.List;

/**
 *
 * @author SSSB
 */
public interface SpecialiteDAO {
    int ajouterSpecialite (String codeSpecialite,String nomSpecialite)throws DAOException;
    
    void modifierSpecialite(int idSpecailite,String codeSpecialite,String nomSpecialite,String anneeSpecialite)throws DAOException ;
    
    List  getSpecialite ()throws DAOException ;
    
    List  getAnnee () throws DAOException;
}
