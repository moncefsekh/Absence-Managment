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
public interface GroupeDAO {
    
    List getGroupeByAnnee (String specialite) throws DAOException ;
    
    int ajouterGroupe (String codeGroupe,String nomGroupe,int idSection) throws DAOException;
    
    void modfifierGroupe(int idGroupe,String codeGroupe,String nomGroupe,int idSection)throws DAOException;
    
}
