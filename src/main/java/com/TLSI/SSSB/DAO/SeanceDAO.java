/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Seance;
import java.util.List;

/**
 *
 * @author SSSB
 */
public interface SeanceDAO {
    
    List  getSeanceJour (int id) throws DAOException;
    
    List getSeanceSemain (int id )throws DAOException;
    
    List getAllSeance ( Integer id ) throws DAOException;
    
    Seance getDatesSeance (Integer idSeance) throws DAOException ;
    
    List <Seance> get_spec_ann_sec (int id) throws DAOException ;
    
    List <Seance> getGroupeMenu (int idEnseignant,String code_specialite,String annee,String section)throws DAOException;
    
    List<Seance> getModuleMenu (int idEnseignant,String code_specialite,String annee,String section,String groupe)throws DAOException;
    
    List <Seance>getALlSeanceResp (String Specialite)throws DAOException;
    
    void ajouterSeance (int idModule,String type,int idEnseignant,String date )throws DAOException;
    
    void modifierSeance (int idSeance, int idModule,String type,int idEnseignant,String date,int idDate)throws DAOException;
    
    void changerSeance(int idDates,String dateChangement)throws DAOException;
    
    
}
