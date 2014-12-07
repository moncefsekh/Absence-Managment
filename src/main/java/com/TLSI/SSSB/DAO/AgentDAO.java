/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import java.util.List;

/**
 *
 * @author Islam
 */
public interface AgentDAO {
    List getAgent ()throws DAOException ;
    
    void ajouterAgent (String nom , String prenom) throws DAOException ;
    
    void modifierAgent (int idAgent, String nom,String prenom ) throws DAOException ;
}
