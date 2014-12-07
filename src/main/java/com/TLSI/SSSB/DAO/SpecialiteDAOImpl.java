/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Specialite;
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
 * @author SSSB
 */
public class SpecialiteDAOImpl implements SpecialiteDAO {
    private static final String REQ_GET_ANNEE= "SELECT idSpecialite,code_specialite,nom_specialite,annee_specialite  FROM Specialite ";
    private static final String REQ_GET_SPEC = "SELECT idSpecialite,code_specialite,nom_specialite,annee_specialite From Specialite WHERE annee_specialite = 'L2' ";    
    private static final String REQ_AJOUTER_SPECIALITE = "INSERT INTO Specialite (code_specialite,nom_specialite,annee_specialite) \n" +
"VALUES (?,?,'L2'),"
            + "(?,?,'L3'),"
            + " (?,?,'M1'),"
            + "(?,?,'M2')";
    
    private static final String REQ_ISEXIST = "SELECT idSpecialite FROM Specialite "
            + "WHERE nom_specialite = ? ";
    
    private static final String REQ_MODIFIER_SPECIALITE ="UPDATE Specialite SET\n" +
"code_specialite = ?,\n" +
"nom_specialite = ?,\n" +
"annee_specialite = ?\n" +
"WHERE idSpecialite = ?";

    private DAOFactory daoFactory;

    SpecialiteDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public int ajouterSpecialite(String codeSpecialite, String nomSpecialite) throws DAOException {
           Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
        int IsExist = 0;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,
                   nomSpecialite);
              resultSet = preaperedStatment2.executeQuery();
              if(!resultSet.next()){
              IsExist = 1 ;
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_AJOUTER_SPECIALITE,
                   codeSpecialite,nomSpecialite,codeSpecialite,nomSpecialite,codeSpecialite,nomSpecialite,codeSpecialite,nomSpecialite);
               preaperedStatment.executeUpdate();
              }
            
           

           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

        return IsExist; 
    }

    @Override
    public void modifierSpecialite(int idSpecailite, String codeSpecialite, String nomSpecialite, String anneeSpecialite) throws DAOException {
   
     Connection connexion = null;
        PreparedStatement preaperedStatment = null;
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_SPECIALITE,
                   codeSpecialite,nomSpecialite,anneeSpecialite,idSpecailite);
               preaperedStatment.executeUpdate();
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    
    }

    @Override
    public List  getSpecialite() throws DAOException {
     Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Specialite> specialites = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_SPEC);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Specialite specialite = new Specialite ();
                specialite.setCode_specialite(resultSet.getString(2));
                specialite.setNom_specialite(resultSet.getString(3));
                specialite.setId_Specialite(resultSet.getInt(1));
                specialite.setAnnee(resultSet.getString(4));
                specialites.add( specialite);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return specialites;
           
    }

    @Override
    public List  getAnnee() throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Specialite> specialites = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_ANNEE);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Specialite specialite = new Specialite ();
                specialite.setCode_specialite(resultSet.getString(2));
                specialite.setNom_specialite(resultSet.getString(3));
                specialite.setId_Specialite(resultSet.getInt(1));
                specialite.setAnnee(resultSet.getString(4));
                specialites.add( specialite);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return specialites;
              
    
    }
    
}
