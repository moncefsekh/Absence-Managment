/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Actualite;
import com.TLSI.SSSB.Beans.Etudiant;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Islam
 */
public class ActualiteDAOImpl implements ActualiteDAO{
private static final String REQ_ACTUALITE_JOUR = "SELECT code_module,\n" +
"TYPE , DATE_FORMAT( date_seance, '%d-%m-%Y' ) AS date_seance, DATE_FORMAT( date_seance, '%H:%i' ) AS heure_seance, DATE_FORMAT( date_changement_seance, '%d-%m-%Y' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance, '%H:%i' ) AS heure_changement_seance, etat_avancement\n" +
"FROM Specialite\n" +
"INNER JOIN Module ON ( idSpecialite = Specialite_idSpecialite )\n" +
"INNER JOIN Seance ON ( idModule = Module_idModule )\n" +
"INNER JOIN Dates ON ( idSeance = Seance_idSeance )\n" +
"WHERE code_specialite =? \n" +
"AND annee_specialite =? \n"+
"";

private static final String REQ_ACTUALITE_SEMAINE ="SELECT code_module,\n" +
"TYPE , DATE_FORMAT( date_seance, '%d-%m-%Y' ) AS date_seance, DATE_FORMAT( date_seance, '%H:%i' ) AS heure_seance, DATE_FORMAT( date_changement_seance, '%d-%m-%Y' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance, '%H:%i' ) AS heure_changement_seance, etat_avancement\n" +
"FROM Specialite\n" +
"INNER JOIN Module ON ( idSpecialite = Specialite_idSpecialite )\n" +
"INNER JOIN Seance ON ( idModule = Module_idModule )\n" +
"INNER JOIN Dates ON ( idSeance = Seance_idSeance )\n" +
"WHERE code_specialite =? \n" +
"AND annee_specialite =? \n" +
"AND WEEKOFYEAR(date_seance) = WEEKOFYEAR(NOW())";


    
      private DAOFactory daoFactory;

    ActualiteDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public List<Actualite> getActualiteJour(String specialite, String annne_specialite) {
        
         Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Actualite> actualites = new ArrayList();
        
        
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_ACTUALITE_JOUR,specialite,annne_specialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Actualite actualite = new Actualite ();
                actualite.setCode_module(resultSet.getString(1));
                actualite.setType(resultSet.getString(2));
                actualite.setDate_seance(resultSet.getString(3));
                actualite.setHeure_seance(resultSet.getString(4));
                actualite.setDate_changement(resultSet.getString(5));
                actualite.setHeure_changement(resultSet.getString(6));
                actualite.setEtat_avancement(resultSet.getString(7));
                
               
                
                actualites.add( actualite);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return actualites;
        
        
        
    }

    @Override
    public List<Actualite> getActualiteSemaine(String specialite, String anne_specialite) {
        
         Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Actualite> actualites = new ArrayList();
        
        
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_ACTUALITE_SEMAINE,specialite,anne_specialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Actualite actualite = new Actualite ();
                actualite.setCode_module(resultSet.getString(1));
                actualite.setType(resultSet.getString(2));
                actualite.setDate_seance(resultSet.getString(3));
                actualite.setHeure_seance(resultSet.getString(4));
                actualite.setDate_changement(resultSet.getString(5));
                actualite.setHeure_changement(resultSet.getString(6));
                actualite.setEtat_avancement(resultSet.getString(7));
                
               
                
                actualites.add( actualite);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return actualites;
        
        
        
    }
    
    
    
}
