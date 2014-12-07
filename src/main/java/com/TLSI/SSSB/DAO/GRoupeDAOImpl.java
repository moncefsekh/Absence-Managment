/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import com.TLSI.SSSB.Beans.Groupe;
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
public class GRoupeDAOImpl implements GroupeDAO{
    private static final String REQ_GET_GROUPES ="SELECT code_groupe , nom_groupe , annee_specialite, idGroupe,idSection,idSpecialite \n" +
"FROM Groupe\n" +
"INNER JOIN Section ON (Section_idSection = idSection )\n" +
"INNER JOIN Specialite on ( Specialite_idSpecialite = idSpecialite)\n" +
"WHERE code_specialite = ? ";
    
    private static final String REQ_ADD_GROUPE = "INSERT INTO Groupe (code_groupe,nom_groupe,Section_idSection)\n" +
"VALUES (?,?,?)";
    
    private static final String REQ_ISEXIST = "SELECT idGroupe from Groupe\n" +
"WHERE nom_groupe = ? and Section_idSection = ?";
    
    private static final String REQ_MODIFIER_GROUPE = "UPDATE Groupe SET\n" +
"code_groupe = ?,\n" +
"nom_groupe = ? , \n" +
"Section_idSection = ? \n" +
"WHERE idGroupe = ?";
    
 private DAOFactory daoFactory;

    GRoupeDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public List getGroupeByAnnee(String specialite) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         ArrayList<Groupe> groupes = new ArrayList();
          try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_GROUPES,specialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
              
               Groupe groupe = new Groupe();
               groupe.setCode_groupe(resultSet.getString(1));
               groupe.setNom_groupe(resultSet.getString(2));
               groupe.setAnnee(resultSet.getString(3));
               groupe.setId_Groupe(resultSet.getInt(4));
               groupe.setIdSection(resultSet.getInt(5));
               groupe.setIdSpecialite(resultSet.getInt(6));
               
               groupes.add(groupe);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
          
        return groupes;
        
        
         
    }

    @Override
    public int ajouterGroupe(String codeGroupe, String nomGroupe, int idSection) throws DAOException {
        Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
        int IsExist = 0;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,
                   nomGroupe,idSection);
              resultSet = preaperedStatment2.executeQuery();
              if(!resultSet.next()){
              IsExist = 1 ;
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ADD_GROUPE,
                   codeGroupe,nomGroupe,idSection);
               preaperedStatment.executeUpdate();
              }
            
           

           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

        return IsExist;
    }
    
    /*
    Methode pour les justifications des absences par l'agent 
    */

    @Override
    public void modfifierGroupe(int idGroupe, String codeGroupe, String nomGroupe, int idSection) throws DAOException {

     Connection connexion = null;
        PreparedStatement preaperedStatment = null;
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_GROUPE,
                   codeGroupe,nomGroupe,idSection,idGroupe);
               preaperedStatment.executeUpdate();
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    
    }

   
}
