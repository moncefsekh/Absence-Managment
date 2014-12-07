/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import com.TLSI.SSSB.Beans.Section;

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
public class SectionDAOImpl implements SectionDAO{
    
    private static final String REQ_GET_SECTION = "SELECT code_section , nom_section , annee_specialite ,idSection,idSpecialite \n" +
"FROM Section\n" +
"INNER JOIN Specialite on ( Specialite_idSpecialite = idSpecialite)\n" +
"WHERE code_specialite =? ";
    
    private static final String REQ_ADD_SECTION = "INSERT INTO Section (code_section,nom_section,Specialite_idSpecialite)\n" +
"VALUES (?,?,?)";
    
    private static final String REQ_ISEXIST = "SELECT idSection FROM Section "
            + " WHERE nom_section = ? and Specialite_idSpecialite = ? ";
    
    private static final String REQ_MODIFIER_SECTION= "UPDATE Section SET\n" +
"code_section = ? ,\n" +
"nom_section = ?,\n" +
"Specialite_idSpecialite = ?\n" +
"WHERE idSection = ?";
    
     private DAOFactory daoFactory;

    SectionDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List getSectionBySpec(String specialite) throws DAOException {
     Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         ArrayList<Section> sections = new ArrayList();
          try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_SECTION,specialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
              
               Section section = new Section();
               section.setCodeSection(resultSet.getString(1));
               section.setNomsection(resultSet.getString(2));
               section.setAnnne(resultSet.getString(3));
               section.setIdSection(resultSet.getInt(4));
               section.setIdSpecialite(resultSet.getInt(5));
               sections.add(section);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
          
        return sections;
        
          
    }

    @Override
    public int ajouterSection(String codeSection, String nomSection, int idSpecialite) throws DAOException {
 Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
        int IsExist = 0;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,
                   nomSection,idSpecialite);
              resultSet = preaperedStatment2.executeQuery();
              if(!resultSet.next()){
              IsExist = 1 ;
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ADD_SECTION,
                   codeSection,nomSection,idSpecialite);
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
    public void modifierModule(int idSection,String codeSection, String nomSection, int idSpecialite) throws DAOException {
      
     Connection connexion = null;
        PreparedStatement preaperedStatment = null;
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_SECTION,
                   codeSection,nomSection,idSpecialite,idSection);
               preaperedStatment.executeUpdate();
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    }
    
}
