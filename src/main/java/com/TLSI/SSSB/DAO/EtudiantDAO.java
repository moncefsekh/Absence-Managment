/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import com.TLSI.SSSB.Beans.Etudiant;

import com.TLSI.SSSB.Beans.InfoEtudiant;

import java.util.List;

/**
 *
 * @author SSSB
 */
public interface EtudiantDAO {
    
    List<Etudiant> getAllEtudiant (String specialite ,String annee ) throws DAOException;
    
    List<Etudiant> getEtudiantGroupe (String specialite,String anneespecialite,String section,String groupe, Integer idSeance);
    
    Etudiant getEtudiantById (int id) throws DAOException;
    
    List detailAbsenceEtudiant (int id) throws DAOException;
    
    InfoEtudiant getInfoEtudiant ( int id) throws DAOException ;
    
    Etudiant getNbrEtudiant (String specialite) throws DAOException;
     
    InfoEtudiant authEtudiant (String user,String pw) throws DAOException ;
    
    
}
