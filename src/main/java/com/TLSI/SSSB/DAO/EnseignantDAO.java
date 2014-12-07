/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Enseignant;
import java.util.List;

/**
 *
 * @author SSSB 
 */
public interface EnseignantDAO {
    List <Enseignant> getAllEnseignant () throws DAOException;
    List <Enseignant> getEnseignant (String specialite) throws DAOException;
    
    int ajouterEnseignant (String nom,String prenom,String dateNaissance,String grade) throws DAOException;

    List  getEnseignantActuel () throws DAOException ;
    
    List getModuleEnseignant (Integer idEnseignant) throws DAOException ;
    
    void modifierEnseignant(int idEnseignant,String nomEnseignant,String prenomEnseignant,String dateNaissance,String grade)throws DAOException;
    
    int envoyerEtatAvancement (String etatAvancement,int idSeance,int idDates )throws DAOException;
    
    Enseignant authEnseignant ( String user , String pw) throws DAOException ;
    
}
