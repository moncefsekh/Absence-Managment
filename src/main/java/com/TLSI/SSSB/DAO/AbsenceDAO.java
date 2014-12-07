/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Absence;
import com.TLSI.SSSB.Beans.Etudiant;
import com.TLSI.SSSB.Beans.Specialite;
import java.util.List;

/**
 *
 * @author SSSB
 */
public interface AbsenceDAO {
    
    List <Absence> getAbsence (int id) throws DAOException ;
    
    public List<Absence> get_etat_exclusion (int id) throws DAOException;
    
    void marquer (Integer idSeance , String date_absence , Integer etudiants) throws DAOException;
    
    void justifier (Etudiant etudiant) throws DAOException;
    
    int getAbsenceDay (Specialite specialite) throws DAOException;
    
    int send_reclamation (String raison) throws DAOException;
    
    int getAbsenceWeek (Specialite specialte) throws DAOException;
    
    List <Absence > getAbsenceWeek ( int id ) throws DAOException ;
    int getAbsenceMonth (Specialite specialite) throws DAOException;
    
    int getAbsenceYear (Specialite specialite)throws DAOException;
    
    List <Etudiant> getAbsents (Integer idSeance , Integer idDate) throws DAOException ;
    
   void supprimer (Integer idAbsence);
    
   Absence getNbrAbsenceBySpec (String specialite) throws DAOException ;
   
   List getNbrAbsencceSemaineByDate (String specialite) throws DAOException;
   
   List getExclusModule ( String specialite) throws DAOException;
    
    
}
